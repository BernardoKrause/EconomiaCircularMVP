/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package service;

import factory.repository.SeletorRepositoryFactory;
import java.sql.SQLException;
import java.util.List;
import model.Item;
import model.Material;
import model.Oferta;
import model.Vendedor;
import repository.IDefeitosTipoRepository;
import repository.IItemRepository;

/**
 *
 * @author caiof
 */
public class ItemService {
    private final IItemRepository itemRepository;
    private final IDefeitosTipoRepository tiposDefeitoRepository;
    private final SistemaDefeitosService sistemaDefeitos;
    private final CalcularGPWService sistemaGPW;
    private OfertaService ofertaService;
    
    public ItemService(SistemaDefeitosService sistema, IDefeitosTipoRepository tiposDefeitosRepo, CalcularGPWService sistemaGPW, OfertaService ofertaService) {
        this.itemRepository = SeletorRepositoryFactory.obterInstancia().criarItemRepository();
        this.sistemaDefeitos = sistema;
        this.tiposDefeitoRepository = tiposDefeitosRepo;
        this.sistemaGPW=sistemaGPW;
        this.ofertaService=ofertaService;
    }
    
    public void criar(Item item, List<String> defeitos, Vendedor vendedor) throws SQLException {
        try {
            sistemaDefeitos.AplicarDefeitos(item, defeitos);
            sistemaGPW.calcularGPW(item);
            vendedor.publicarItem(item);
            item.gerarIdC();
            itemRepository.adicionarItem(item);
        } catch (SQLException ex) {
            throw new RuntimeException("Erro ao criar item " + ex);
        }
    }
    
    public void editar(Item item, List<String> defeitos, Vendedor vendedor) throws SQLException {
        try {
            sistemaDefeitos.AplicarDefeitos(item, defeitos);
            sistemaGPW.calcularGPW(item);
            vendedor.publicarItem(item);
            itemRepository.atualizarItem(item);
        } catch (SQLException ex) {
            throw new RuntimeException("Erro ao criar item " + ex);
        }
    }
    
    public void excluir(Item item){
        try{
            itemRepository.excluirItem(item);
        }catch(Exception ex){
            throw new RuntimeException("Erro ao tentar excluir item! "+ ex.getMessage());
        }
    }
    
    public List<Item> getItens() throws Exception{
        try {
            return itemRepository.buscarTodos();
        } catch (SQLException ex) {
            throw ex;
        }
        
    }
    
    public List<Item> getItensVendedor(Vendedor vendedor) throws SQLException {
        try {
            return itemRepository.buscarPorVendedor(vendedor);
        } catch (SQLException ex) {
            throw ex;
        }
        
    }
    
    public List<String> getListaTipos() throws SQLException{
        try {
            List<String> tipos = itemRepository.buscarTipos();
            if(tipos.isEmpty()){
                throw new IllegalArgumentException("Lista de Tipos está vazia!");
            }
            return tipos;
        } catch (SQLException ex) {
            throw ex;
        }
        
    }
    
    public List<String> getListaDefeitosExistentes(String tipo) throws SQLException{
        try {
            List<String> defeitos = tiposDefeitoRepository.buscarPorTipo(tipo);
            if(defeitos.isEmpty()){
                throw new IllegalArgumentException("Lista de Defeitos está vazia!");
            }   
            return defeitos;
        } catch (IllegalArgumentException | SQLException ex) {
            throw ex;
        }
    }
    
    public List<Material> getListaMateriaisComposicao() throws SQLException {
        try {
            List<Material> materiais = itemRepository.buscarMateriais();
            if(materiais.isEmpty()){
                throw new IllegalArgumentException("Lista de Tipos de material está vazia!");
            }
            return materiais;    
        } catch (IllegalArgumentException | SQLException ex) {
            throw ex;
        }
    }
    
    public Double getFatorMaterial(String nomeMaterial) throws SQLException {
        try {
            return itemRepository.buscarFatorEmissaoMaterial(nomeMaterial);            
        } catch (SQLException ex) {
            throw ex;
        }
    }
    
    public List<Oferta> getOfertasPorItem(Item item) throws SQLException {
        try {
            return ofertaService.getOfertasPorItem(item);
        } catch (SQLException ex) {
            throw ex;
        }
    }
}
