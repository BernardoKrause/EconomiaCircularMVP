/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
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
    
    public ItemService(ItemRepository itemRepo, SistemaDefeitosService sistema, TiposDefeitoRepository tiposDefeitosRepo){
        this.itemRepository = itemRepo;
        this.sistemaDefeitos = sistema;
        this.tiposDefeitoRepository = tiposDefeitosRepo;
    }
    
    public void criar(Item item, List<String> defeitos, Vendedor vendedor){
        sistemaDefeitos.AplicarDefeitos(item, defeitos);
        vendedor.publicarItem(item);
        item.gerarIdC(itemRepository.getQuantidadeItens());
        itemRepository.salvarItem(item);
        System.out.print(item);
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
