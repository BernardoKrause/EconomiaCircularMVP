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
    private List<Item> itens;
  
    public Vendedor(Usuario usuario) {
        super(usuario);
        this.sistemId=("V-"+usuario.getId());
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
        for(Item i: itens){
            if(i.getIdC().equals(item.getIdC())){
                i=item;
                return;
            }
        }
        System.out.println(getId());
        item.setVendedor(this);
        itens.add(item);
    }
    
    /*
    public void responderOferta(Oferta oferta, boolean status) {
        
    }
    */
}
