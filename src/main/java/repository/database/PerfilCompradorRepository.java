/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package repository.database;

import dao.sqlite.PerfilCompradorDAOSQLite;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;
import model.Comprador;
import dao.IPerfilDAO;
import factory.dao.IDAOFactory;
import java.util.stream.Collectors;
import model.Perfil;
import repository.IPerfilRepository;

/**
 *
 * @author berna
 */
public class PerfilCompradorRepository implements IPerfilRepository {
    private final IPerfilDAO perfilCompradorDAO;
    
    public PerfilCompradorRepository(IDAOFactory daoFactory) throws SQLException {
       this.perfilCompradorDAO = daoFactory.getPerfilCompradorDAO();
    }
    
    public PerfilCompradorRepository() throws SQLException {
       this.perfilCompradorDAO = new PerfilCompradorDAOSQLite();
    }

    @Override
    public void adicionarPerfil(Perfil comprador) throws SQLException {
        perfilCompradorDAO.criar((Comprador)comprador);
    }
    
    @Override
    public Optional<Perfil> buscarPorIdUsuario(Integer id) throws SQLException {
        return perfilCompradorDAO.buscaPorIdUsuario(id);
    }
    
    @Override
    public List<Perfil> buscarTodosPerfis() throws SQLException {
        List<Perfil> compradores = perfilCompradorDAO.buscaTodos();
        return compradores.stream()
                .map(comprador -> (Comprador) comprador)
                .collect(Collectors.toList());
    }
    
    @Override
    public void atualizarPerfil(Perfil perfil) throws SQLException {
        perfilCompradorDAO.atualizar(perfil);
    }
    
    @Override
    public void deletarPerfil(Integer id) throws SQLException {
        perfilCompradorDAO.deletar(id);
    }

}
