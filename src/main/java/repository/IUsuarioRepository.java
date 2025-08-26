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
    public void adicionarUsuario(String nome, String email, String telefone, String senha, boolean isAdmin) throws SQLException;

    public Optional<Usuario> buscarUsuario(Integer id) throws SQLException ;

    public List<Usuario> buscarTodosUsuarios() throws SQLException ;

    public void atualizarUsuario(Usuario usuario) throws SQLException;
    
    public void deletarUsuario(Integer id) throws SQLException ;
    
    public int totalUsuarios() throws SQLException;

    public Optional<Usuario> buscarUsuarioPorEmail(String email) throws SQLException;
}
