/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package repository.database;

import dao.sqlite.UsuarioDAOSQLite;
import factory.dao.IDAOFactory;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;
import model.Usuario;
import repository.IUsuarioRepository;
import dao.IUsuarioDAO;

/**
 *
 * @author berna
 */
public class UsuarioRepository implements IUsuarioRepository {
    private final IUsuarioDAO usuarioDAO;

    public UsuarioRepository(IDAOFactory daoFactory) throws SQLException {
        this.usuarioDAO = daoFactory.getUsuarioDAO();
    }

    @Override
    public void adicionarUsuario(String nome, String email, String telefone, String senha, boolean isAdmin) throws SQLException {
        usuarioDAO.criar(new Usuario(nome, email, telefone, senha, isAdmin));
    }

    @Override
    public Optional<Usuario> buscarUsuario(Integer id) throws SQLException {
        return usuarioDAO.buscaPorId(Integer.valueOf(id));
    }

    @Override
    public List<Usuario> buscarTodosUsuarios() throws SQLException {
        return usuarioDAO.buscaTodos();
    }

    public void atualizarUsuario(Usuario usuario) throws SQLException {
        usuarioDAO.atualizar(usuario);
    }
    
    @Override
    public void deletarUsuario(String id) throws SQLException {
        usuarioDAO.deletar(Integer.valueOf(id));
    }
    
    @Override
    public int totalUsuarios() throws SQLException {
        return buscarTodosUsuarios().size();
    }

    @Override
    public Optional<Usuario> buscarUsuarioPorEmail(String email) throws SQLException {
        return usuarioDAO.buscaPorEmail(email);

    }
    
}
