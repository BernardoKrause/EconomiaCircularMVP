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
        Usuario u1 = new Usuario("1", "1", "1");
        u1.setAdmin(true);
        u1.setId(totalUsuarios());
        usuariosCadastrados.add(u1);
        Usuario u2 = new Usuario("2", "2", "2");
        u2.setId(totalUsuarios());
        usuariosCadastrados.add(u2);
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
        return this.usuariosCadastrados.size();
    }
    
    public void salvarUsuario(Usuario usuario) {
        if(totalUsuarios()==0){
            usuario.setAdmin(true);
        }
        usuario.setId(totalUsuarios());
        this.usuariosCadastrados.add(usuario);
    }
}
