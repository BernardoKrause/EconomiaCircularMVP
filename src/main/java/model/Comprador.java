/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author berna
 */
public class Comprador extends Perfil {
    
    public Comprador (Integer id) {
        super(id);
    }
    
    public Comprador (String sistemId) {
        super(sistemId);
    }
    
    public Comprador(Integer id, String sistemId) {
        super(id, sistemId);
    }
    
    public Comprador(Integer id, String sistemId, Reputacao reputacao) {
        super(id, sistemId, reputacao);
    }
    /*
    public void enviarOferta (Item item, Double valor) {
        
    }
    
    public void enviarReclamcao(Item item, String reclamacao) {
        
    }
    */
}
