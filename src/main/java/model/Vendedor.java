/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import java.util.ArrayList;
import java.util.List;
import repository.ItemRepository;

/**
 *
 * @author berna
 */
public class Vendedor extends Perfil {
    
    private List<Item> itens;
  
    public Vendedor(String idVendedor) {
        super(id);
        this.itens=new ArrayList<>();
    }
    
    public void publicarItem(Item item) {
        if(item==null){
            throw new IllegalArgumentException("O item que esta tentando publicar não existe!");
        }
        item.setVendedor(this);
        itens.add(item);
    }
    
    /*
    public void responderOferta(Oferta oferta, boolean status) {
        
    }
    */
}
