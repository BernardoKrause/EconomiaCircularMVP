/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package service;

import model.Comprador;
import model.Perfil;
import model.Usuario;
import model.Vendedor;
import repository.PerfilVendedorRepository;

/**
 *
 * @author berna
 */
public class PerfilService {
    private PerfilVendedorRepository perfilRepository;
    
    public PerfilService(PerfilVendedorRepository perfilRepository){
        this.perfilRepository=perfilRepository;
    }
    
    public void criarPerfilVendedor(Usuario usuario) {
        if (usuario==null){
            throw new IllegalArgumentException("Usuario informado não pode ser nulo!");
        }
        Perfil perfil = new Vendedor("V-"+usuario.getId());
        usuario.addPerfil(perfil);
        perfil.setUsuario(usuario);
        
        perfilRepository.salvarPerfil(perfil);

    }
    
    public void criarPerfilComprador(Usuario usuario) {
        if (usuario==null){
            throw new IllegalArgumentException("Usuario informado não pode ser nulo!");
        }
        Perfil perfil = new Comprador("C-"+usuario.getId());
        usuario.addPerfil(perfil);
        
        perfilRepository.salvarPerfil(perfil);
    }
}
