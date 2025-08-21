/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author berna
 */
public class Vendedor extends Perfil {
    private final List<Item> itens;
  
    public Vendedor(int id) {
        super(id);
        setSystemId(id);
        this.itens=new ArrayList<>();
    }
    
    public Vendedor(Integer id, String sistemId) {
        super(id, sistemId);
        this.itens = null;
    }
    
    public Vendedor(Integer id, String sistemId, Reputacao reputacao) {
        super(id, sistemId, reputacao);
        this.itens = null;
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

    @Override
    void setSystemId(int id) {
        this.sistemId=("V-"+id);
    }
}
