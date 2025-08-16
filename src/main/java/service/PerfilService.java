/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package service;

import java.sql.SQLException;
import model.Usuario;
import model.Vendedor;
import repository.PerfilVendedorRepository;

/**
 *
 * @author berna
 */
public class PerfilService {
    private PerfilVendedorRepository perfilRepository;
    
    public PerfilService(PerfilVendedorRepository perfilVendedorRepository){
        this.perfilRepository=perfilVendedorRepository;
    }
    
    public void criarPerfilVendedor(Usuario usuario) throws SQLException {
       if (usuario == null) throw new IllegalArgumentException("Usuario informado não pode ser nulo!");
        
        try {
            Vendedor perfil = new Vendedor("V-"+usuario.getId());
            usuario.addPerfil(perfil);
            perfil.setUsuario(usuario);
            perfilRepository.adicionaPerfil(perfil);   
        } catch (SQLException ex) {
            throw ex;
        }

    }
    
    public void criarPerfilComprador(Usuario usuario) {
        //if (usuario==null){
        //    throw new IllegalArgumentException("Usuario informado não pode ser nulo!");
        //}
        //perfil = new Comprador("C-"+usuario.getId());
        //usuario.addPerfil(perfil);
        
        //perfilRepository.salvarPerfil(perfil);
    }
}
