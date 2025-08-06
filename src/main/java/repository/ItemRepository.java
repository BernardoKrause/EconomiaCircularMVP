/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import model.Item;
import model.Vendedor;

/**
 *
 * @author caiof
 */
public class ItemRepository {
    private List<Item> itensPublicados;
    
    public ItemRepository(){
        this.itensPublicados=new ArrayList<>();
    }
    
    public Optional<List<Item>> BuscarPorVendedor(Vendedor vendedor){
        if(vendedor == null){
            throw new RuntimeException("Vendedor não encontrado!");
        }
        else{
            List<Item> itensAchados = new ArrayList<>();
            for(Item item : itensPublicados){
                if (item.getVendedor() == vendedor){
                    itensAchados.add(item);
                }
            }
            if(itensAchados.isEmpty()){
                throw new RuntimeException("Esse Vendedor não publicou nenhum item!");
            }
            return Optional.of(itensAchados);
        }
    }
    
    public Optional<Item> BuscarPorIdC(String idC){
        for(Item item : itensPublicados){
            if(item.getIdC() == idC){
                return Optional.of(item);
            }
        }
        throw new RuntimeException("Item com esse ID-C não foi encontrado!");
    }
    
    public Integer getQuantidadeItens(){
        return itensPublicados.size();
    }
    
    public void salvarItem(Item item){
        this.itensPublicados.add(item);
    }
}
