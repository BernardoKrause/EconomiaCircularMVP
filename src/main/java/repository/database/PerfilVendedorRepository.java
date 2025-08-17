/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package repository.database;

import dao.PerfilVendedorDAO;
import dao.PerfilVendedorDAOSQLite;
import factory.dao.IDAOFactory;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import model.Perfil;
import model.Vendedor;
import repository.IPerfilRepository;

/**
 *
 * @author berna
 */
public class PerfilVendedorRepository implements IPerfilRepository{
    private final PerfilVendedorDAO perfilVendedorDAO;

    public PerfilVendedorRepository(IDAOFactory daoFactory) throws SQLException {
       this.perfilVendedorDAO = daoFactory.getPerfilVendedorDAO();
    }
    
    @Override
    public void salvarPerfil(Perfil perfil) throws SQLException {
        perfilVendedorDAO.criar((Vendedor)perfil);
    }
    
    @Override
    public Optional<Perfil> getPorIdUsuario(Integer id) throws SQLException {
        Perfil perfilAchado = perfilVendedorDAO.buscaPorIdUsuario(id).get();
        return Optional.of(perfilAchado);
    }
    
    @Override
    public Optional<List<Perfil>> getTodosPerfils() throws SQLException {
        List<Perfil> perfilsAchados = new ArrayList<>();
        for (Perfil v:perfilVendedorDAO.buscaTodos()){
            perfilsAchados.add(v);
        }
        return Optional.of(perfilsAchados);
    }
    
    @Override
    public void deletaPerfil(Integer id) throws SQLException {
        perfilVendedorDAO.deletar(id);
    }
    
}
