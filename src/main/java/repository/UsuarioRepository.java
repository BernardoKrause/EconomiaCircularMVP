/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import model.Usuario;

/**
 *
 * @author berna
 */
public class UsuarioRepository {
    private List<Usuario> usuariosCadastrados;

    public UsuarioRepository() {
        usuariosCadastrados = new ArrayList<>();
        usuariosCadastrados.add(new Usuario("bk@gmail.com", "senha123"));
        usuariosCadastrados.add(new Usuario("caiofreire@gmail.com", "321senha"));
    }
    
    public Optional<Usuario> buscarPorEmail(String email) {
        for (Usuario usuariosCadastrado : usuariosCadastrados) {
            if (usuariosCadastrado.getEmail().equalsIgnoreCase(email)) {
                return Optional.of(usuariosCadastrado);
            }
        }
        throw new RuntimeException("Usuario com este e-mail não encontrado");
    }
    
    public int totalUsuarios() {
        return 0;
    }
    
    public void salvarUsuario(Usuario usuario) {
        this.usuariosCadastrados.add(usuario);
    }
}
