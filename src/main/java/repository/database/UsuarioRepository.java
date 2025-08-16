/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package repository.database;

import dao.UsuarioDAOSQLite;
import dao.UsuarioDAO;
import factory.dao.DAOFactory;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;
import model.Usuario;
import repository.IUsuarioRepository;

/**
 *
 * @author berna
 */
public class UsuarioRepository implements IUsuarioRepository {
    private final UsuarioDAO usuarioDAO;

    public UsuarioRepository(DAOFactory daoFactory) throws SQLException {
        this.usuarioDAO = daoFactory.getUsuarioDAO();
    }

    @Override
    public void adicionaUsuario(String nome, String email, String telefone, String senha, boolean isAdmin) throws SQLException {
        usuarioDAO.criar(new Usuario(nome, email, telefone, senha, isAdmin));
    }

    @Override
    public Optional<Usuario> getUsuario(String id) throws SQLException {
        return usuarioDAO.buscaPorId(Integer.valueOf(id));
    }

    @Override
    public List<Usuario> getTodosUsuarios() throws SQLException {
        return usuarioDAO.buscaTodos();
    }

    @Override
    public void atualizaUsuario(String id, String nome, String email, String telefone, String senha) throws SQLException {
        usuarioDAO.atualizar(new Usuario(id, nome, email, telefone, senha));
    }
    
    @Override
    public void deletaUsuario(String id) throws SQLException {
        usuarioDAO.deletar(Integer.valueOf(id));
    }
    
    @Override
    public int totalUsuarios() throws SQLException {
        return getTodosUsuarios().size();
    }

    @Override
    public Optional<Usuario> getUsuarioPorEmail(String email) throws SQLException {
        return usuarioDAO.buscaPorEmail(email);

    }
    
}
