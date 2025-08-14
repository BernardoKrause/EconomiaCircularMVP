/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package service;

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
        boolean isAdmin = usuarioRepository.totalUsuarios() == 0 ? true : false;
        usuarioRepository.adicionaUsuario(nome, email, telefone, senha, isAdmin);
    }
}
