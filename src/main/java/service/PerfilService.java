/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package service;

import model.Comprador;
import model.Perfil;
import model.Usuario;
import model.Vendedor;
import repository.PerfilRepository;
import repository.UsuarioRepository;

/**
 *
 * @author berna
 */
public class PerfilService {
    private UsuarioRepository usuarioRepository; 
    private PerfilRepository perfilRepository;
    
    public void criarPerfilVendedor(Usuario usuario) {
        Perfil perfil = new Vendedor("V-"+usuario.getId());
        usuario.addPerfil(perfil);
        
        perfilRepository.salvarPerfil(perfil);

    }
    
    public void criarPerfilComprador(Usuario usuario) {
        Perfil perfil = new Comprador("C-"+usuario.getId());
        usuario.addPerfil(perfil);
        
        perfilRepository.salvarPerfil(perfil);
    }
}
