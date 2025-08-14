/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package repository;

import dao.UsuarioDAOSQLite;
import dao.UsuarioDAO;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;
import model.Usuario;

/**
 *
 * @author berna
 */
public class UsuarioRepository {
    private final UsuarioDAO usuarioDAO;

    public UsuarioRepository() throws SQLException {
       this.usuarioDAO = new UsuarioDAOSQLite();
    }

    public void adicionaUsuario(String nome, String email, String telefone, String senha, boolean isAdmin) throws SQLException {
        usuarioDAO.criar(new Usuario(nome, email, telefone, senha, isAdmin));
    }

    public Usuario getUsuario(String id) throws SQLException {
        return usuarioDAO.buscaPorId(Integer.parseInt(id));
    }

    public List<Usuario> getTodosUsuarios() throws SQLException {
        return usuarioDAO.buscaTodos();
    }

    public void atualizaUsuario(String id, String nome, String email, String telefone, String senha) throws SQLException {
        usuarioDAO.atualizar(new Usuario(id, nome, email, telefone, senha));
    }
    
    public void deletaUsuario(String id) throws SQLException {
        usuarioDAO.deletar(Integer.parseInt(id));
    }
    
    public int totalUsuarios() throws SQLException {
        return getTodosUsuarios().size();
    }

    public Optional<Usuario> getUsuarioPorEmail(String email) throws SQLException {
        return usuarioDAO.buscaPorEmail(email);

    }
    
}
