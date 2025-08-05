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
    
    public Vendedor(Integer idVendedor) {
        this.id = ("V-"+idVendedor);
        this.itens=new ArrayList<>();
    }
    
    public void publicarItem(Item item) {
        item.setVendedor(this);
        //item.gerarIdC(2);
        
    }
    
    /*
    public void responderOferta(Oferta oferta, boolean status) {
        
    }
    */
}
