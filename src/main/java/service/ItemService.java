/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package service;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import model.Item;
import model.Vendedor;
import repository.ItemRepository;
import repository.TiposDefeitoRepository;

/**
 *
 * @author caiof
 */
public class ItemService {
    private ItemRepository itemRepository;
    private TiposDefeitoRepository tiposDefeitoRepository;
    private SistemaDefeitosService sistemaDefeitos;
    
    public ItemService(SistemaDefeitosService sistema, TiposDefeitoRepository tiposDefeitosRepo) throws SQLException {
        try {
            this.itemRepository = new ItemRepository();
        } catch (SQLException ex) {
            ex.printStackTrace();
            throw ex;
        }
        
        this.sistemaDefeitos = sistema;
        this.tiposDefeitoRepository = tiposDefeitosRepo;
    }
    
    public void criar(Item item, List<String> defeitos, Vendedor vendedor) throws SQLException {
        try {
            sistemaDefeitos.AplicarDefeitos(item, defeitos);
            vendedor.publicarItem(item);
            item.gerarIdC(itemRepository.getQuantidadeItens());
            System.out.print(item.toString());
            itemRepository.salvarItem(item);
        } catch (SQLException ex) {
            ex.printStackTrace();
            throw ex;
        }
    }
    
    public List<String> getListaTiposItem(){
        if(tiposDefeitoRepository.getTipos().isEmpty()){
            throw new IllegalArgumentException("Lista de Tipos está vazia!");
        }
        return tiposDefeitoRepository.getTipos().get();
    }
    
    public List<String> getListaDefeitosExistentes(String tipo){
        if(tiposDefeitoRepository.getDefeitosTipo(tipo).isEmpty()){
            throw new IllegalArgumentException("Lista de Defeitos está vazia!");
        }   
        return tiposDefeitoRepository.getDefeitosTipo(tipo).get();
    }
    
    public List<String> getListaMateriaisComposicao(){
        //fazer aidna
        List<String> lista = new ArrayList<>();
        lista.add("pano");
        return lista;
    }
}
