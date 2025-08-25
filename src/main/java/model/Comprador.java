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
public class Comprador extends Perfil {
    private List<Oferta> ofertas;
    
    public Comprador (Usuario usuario) {
        super(usuario);
        this.sistemId=("C-"+usuario.getId());
        this.ofertas=new ArrayList<>();
    }
    
//    public Comprador (String sistemId) {
//        super(sistemId);
//    }
    
    public Comprador(Integer id, String sistemId) {
        super(id, sistemId);
    }
    
    public Comprador(Integer id, String sistemId, Reputacao reputacao) {
        super(id, sistemId, reputacao);
    }
    
    public void addOferta(Oferta oferta){
        ofertas.add(oferta);
    }

}
