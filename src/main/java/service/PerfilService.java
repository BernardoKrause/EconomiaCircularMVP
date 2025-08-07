/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package service;

import model.Usuario;
import model.Vendedor;

/**
 *
 * @author berna
 */
public class PerfilService {
        
    public void criarPerfilVendedor(Usuario usuario) {
        
        usuario.addPerfil(new Vendedor(""));
    }
    
    public void criarPerfilComprador(Usuario usuario) {
        
    }
}
