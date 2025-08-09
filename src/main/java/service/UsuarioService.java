/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package service;

import model.Comprador;
import model.Usuario;
import model.Vendedor;
import repository.UsuarioRepository;

/**
 *
 * @author berna
 */
public class UsuarioService {
    private UsuarioRepository usuarioRepository;
    
    public UsuarioService(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }
    
    public void cadastrarUsuario(String nome, String email, String telefone, String senha) {
        long totalUsuarios = usuarioRepository.totalUsuarios();
        
        Usuario novoUsuario = new Usuario(nome, email, telefone, senha);
        
        if (totalUsuarios == 0){
            novoUsuario.setAdmin(true);
        }
        
        usuarioRepository.salvarUsuario(novoUsuario);
    }
    
    public void criarPerfilVendedor(Usuario usuario) {
        usuario.addPerfil(new Vendedor("V-"+usuario.getId()));
        usuarioRepository.salvarUsuario(usuario);

    }
}
