/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package service;

import factory.repository.SeletorRepositoryFactory;
import java.sql.SQLException;
import java.util.List;
import model.Item;
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
            System.out.print(item.toString());
            itemRepository.adicionarItem(item);
        } catch (SQLException ex) {
            ex.printStackTrace();
            throw ex;
        }
    }
    
    public List<Item> getItens(){
        return itemRepository.buscarTodos().get();
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
    
    public List<String> getListaMateriaisComposicao(){
        if(itemRepository.buscarTiposMaterial().isEmpty()){
            throw new IllegalArgumentException("Lista de Tipos de material está vazia!");
        }
        return itemRepository.buscarTiposMaterial();
    }
    
    public Double getFatorMaterial(String nomeMaterial){
        return itemRepository.buscarFatorEmissaoMaterial(nomeMaterial);
    }
}
