/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package service;

import java.util.List;
import model.Item;
import model.Vendedor;
import repository.ItemRepository;

/**
 *
 * @author caiof
 */
public class ItemService {
    private ItemRepository repository;
    
    public void publicar(Item item, List<String> defeitos, Vendedor vendedor){
        SistemaDefeitosService.AplicarDefeitos(item, defeitos);
        vendedor.publicarItem(item);
        item.gerarIdC(repository.getQuantidadeItens());
        repository.salvarItem(item);
    }
}
