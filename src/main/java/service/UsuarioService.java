/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package service;

import factory.repository.SeletorRepositoryFactory;
import java.sql.SQLException;
import java.util.Optional;
import model.Usuario;
import repository.IUsuarioRepository;

/**
 *
 * @author berna
 */
public class UsuarioService {
    private final IUsuarioRepository usuarioRepository;
    
    public UsuarioService(IUsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }
    
    public void cadastrarUsuario(String nome, String email, String telefone, String senha) throws SQLException {
        boolean isAdmin = usuarioRepository.totalUsuarios() == 0;
        try {
            usuarioRepository.adicionarUsuario(nome, email, telefone, senha, isAdmin);
        } catch (SQLException ex) {
            throw ex;
        }
    }
    
    public void autenticarUsuario (Usuario usuario) throws SQLException {
        Optional<Usuario> optUsuario;
        try {
            optUsuario = usuarioRepository.buscarUsuarioPorEmail(usuario.getEmail());
        } catch (SQLException ex) {
            throw ex;
        }
        
        if (optUsuario.isPresent()) {
            if (optUsuario.get().getSenha().equals(usuario.getSenha())) {
                usuario.copy(optUsuario.get());
                usuario.setAutenticado(true);
            }
        } else {
            throw new RuntimeException("Email e senha não correspondem à um usuário.");
        }
    }
    
    public void completarUsuario(Usuario usuario) throws SQLException{
        usuario.addPerfil(SeletorRepositoryFactory.obterInstancia().criarPerfilVendedorRepository().buscarPorIdUsuario(usuario.getId()).get());
        usuario.addPerfil(SeletorRepositoryFactory.obterInstancia().criarPerfilCompradorRepository().buscarPorIdUsuario(usuario.getId()).get());
    }
}
