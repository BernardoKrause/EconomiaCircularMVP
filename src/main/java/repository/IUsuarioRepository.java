/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package repository;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;
import model.Usuario;

/**
 *
 * @author caiof
 */
public interface IUsuarioRepository {
    public void adicionaUsuario(String nome, String email, String telefone, String senha, boolean isAdmin) throws SQLException;

    public Optional<Usuario> getUsuario(String id) throws SQLException ;

    public List<Usuario> getTodosUsuarios() throws SQLException ;

    public void atualizaUsuario(Usuario usuario) throws SQLException;
    
    public void deletaUsuario(String id) throws SQLException ;
    
    public int totalUsuarios() throws SQLException;

    public Optional<Usuario> getUsuarioPorEmail(String email) throws SQLException;
}
