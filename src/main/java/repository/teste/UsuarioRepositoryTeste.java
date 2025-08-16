/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package repository.teste;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import model.Usuario;
import repository.IUsuarioRepository;

/**
 *
 * @author caiof
 */
public class UsuarioRepositoryTeste implements IUsuarioRepository{
    private List<Usuario> usuariosCadastrados;
    private static UsuarioRepositoryTeste instancia;
    
    private UsuarioRepositoryTeste(){
        usuariosCadastrados=new ArrayList<>();
    }
    
    public static UsuarioRepositoryTeste getintancia(){
        if(instancia==null){        
            instancia = new UsuarioRepositoryTeste();
        }     
        return instancia;
    }

    @Override
    public void adicionaUsuario(String nome, String email, String telefone, String senha, boolean isAdmin) throws SQLException {
        Usuario u = new Usuario(nome, email, telefone, senha);
        u.setAdmin(isAdmin);
        usuariosCadastrados.add(u);
    }

    @Override
    public Optional<Usuario> getUsuario(String id) throws SQLException {
        for(Usuario u : usuariosCadastrados){
            if(u.getId().equalsIgnoreCase(id)){
                return Optional.of(u);
            }
        }
        return Optional.empty();
    }

    @Override
    public List<Usuario> getTodosUsuarios() throws SQLException {
        return usuariosCadastrados;
    }

    @Override
    public void atualizaUsuario(String id, String nome, String email, String telefone, String senha) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void deletaUsuario(String id) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public int totalUsuarios() throws SQLException {
        return usuariosCadastrados.size();
    }

    @Override
    public Optional<Usuario> getUsuarioPorEmail(String email) throws SQLException {
        for(Usuario u : usuariosCadastrados){
            if(email.equals(u.getEmail())){
                return Optional.of(u);
            }
        }
        return Optional.empty();
    }
}
