/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package service;

import java.sql.SQLException;
import java.util.Optional;
import model.Usuario;
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
    
    public void cadastrarUsuario(String nome, String email, String telefone, String senha) throws SQLException {
        boolean isAdmin = usuarioRepository.totalUsuarios() == 0 ? true : false;
        usuarioRepository.adicionaUsuario(nome, email, telefone, senha, isAdmin);
    }
    
    public void autenticarUsuario (Usuario usuario) throws SQLException {
        Optional<Usuario> optUsuario = usuarioRepository.getUsuarioPorEmail(usuario.getEmail());
        if (optUsuario.isPresent()) {
            if (optUsuario.get().getSenha() == usuario.getSenha()) {
            usuario.setAutenticado(true);
        }
        } else {
            throw new RuntimeException("Email e senha não correspondem à um usuário.");
        }
    }

}
