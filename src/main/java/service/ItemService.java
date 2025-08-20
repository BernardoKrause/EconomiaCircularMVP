/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package service;

import factory.repository.SeletorRepositoryFactory;
import java.sql.SQLException;
import java.util.ArrayList;
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
    }
    
    public void criar(Item item, List<String> defeitos, Vendedor vendedor) throws SQLException {
        try {
            sistemaDefeitos.AplicarDefeitos(item, defeitos);
            sistemaGPW.calcularGPW(item);
            vendedor.publicarItem(item);
            item.gerarIdC(itemRepository.getQuantidadeItens());
            System.out.print(item.toString());
            itemRepository.salvarItem(item);
        } catch (SQLException ex) {
            ex.printStackTrace();
            throw ex;
        }
    }
    
    public List<Item> getItens(){
        return itemRepository.buscarTodos().get();
    }
    
    public List<String> getListaTiposItem() throws SQLException{
        if(itemRepository.getTiposItem().isEmpty()){
            throw new IllegalArgumentException("Lista de Tipos está vazia!");
        }
        return itemRepository.getTiposItem().get();
    }
    
    public List<String> getListaDefeitosExistentes(String tipo) throws SQLException{
        if(tiposDefeitoRepository.BuscarPorTipo(tipo).isEmpty()){
            throw new IllegalArgumentException("Lista de Defeitos está vazia!");
        }   
        return tiposDefeitoRepository.BuscarPorTipo(tipo).get();
    }
    
    public List<String> getListaMateriaisComposicao(){
        if(itemRepository.getTiposMaterial().isEmpty()){
            throw new IllegalArgumentException("Lista de Tipos de material está vazia!");
        }
        return itemRepository.getTiposMaterial().get();
    }
    
    public Double getFatorMaterial(String nomeMaterial){
        return itemRepository.getFatorEmissaoMaterial(nomeMaterial);
    }
}
