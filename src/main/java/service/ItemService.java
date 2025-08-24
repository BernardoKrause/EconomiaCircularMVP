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
import model.Vendedor;
import repository.IItemRepository;
import repository.teste.DefeitosTipoRepositoryTeste;

/**
 *
 * @author caiof
 */
public class ItemService {
    private IItemRepository itemRepository;
    private DefeitosTipoRepositoryTeste tiposDefeitoRepository;
    private SistemaDefeitosService sistemaDefeitos;
    private CalcularGPWService sistemaGPW;
    
    public ItemService(SistemaDefeitosService sistema, DefeitosTipoRepositoryTeste tiposDefeitosRepo, CalcularGPWService sistemaGPW ) {
        this.itemRepository = SeletorRepositoryFactory.obterInstancia().criarItemRepository();
        
        this.sistemaDefeitos = sistema;
        this.tiposDefeitoRepository = tiposDefeitosRepo;
        this.sistemaGPW=sistemaGPW;
    }
    
    public void criar(Item item, List<String> defeitos, Vendedor vendedor) throws SQLException {
        try {
            sistemaDefeitos.AplicarDefeitos(item, defeitos);
            sistemaGPW.calcularGPW(item);
            vendedor.publicarItem(item);
            item.gerarIdC(itemRepository.buscarQuantidadeItens());
            itemRepository.adicionarItem(item);
        } catch (SQLException ex) {
            throw new RuntimeException("Erro ao criar item" + ex);
        }
    }
    
    public List<Item> getItens() throws Exception{
        try {
            return itemRepository.buscarTodos();
        } catch (Exception ex) {
            throw ex;
        }
        
    }
    
    public List<Item> getItensVendedor(Vendedor vendedor){
        return itemRepository.buscarPorVendedor(vendedor);
    }
    
    public List<String> getListaTiposItem() throws SQLException{
        if(itemRepository.buscarTiposItem().isEmpty()){
            throw new IllegalArgumentException("Lista de Tipos está vazia!");
        }
        return itemRepository.buscarTiposItem();
    }
    
    public List<String> getListaDefeitosExistentes(String tipo) throws SQLException{
        if(tiposDefeitoRepository.buscarPorTipo(tipo).isEmpty()){
            throw new IllegalArgumentException("Lista de Defeitos está vazia!");
        }   
        return tiposDefeitoRepository.buscarPorTipo(tipo);
    }
    
    public List<Material> getListaMateriaisComposicao(String tipo) throws Exception{
        try {
            if(itemRepository.buscarMaterialPorTipoItem(tipo).isEmpty()){
                throw new IllegalArgumentException("Lista de Tipos de material está vazia!");
            }
            return itemRepository.buscarMaterialPorTipoItem(tipo);    
        } catch (Exception ex) {
            throw ex;
        }
        
    }
    
    public Double getFatorMaterial(String nomeMaterial) throws Exception{
        try {
            return itemRepository.buscarFatorEmissaoMaterial(nomeMaterial);            
        } catch (Exception ex) {
            throw ex;
        }
    }
}
