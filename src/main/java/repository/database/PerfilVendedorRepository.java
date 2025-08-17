/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package repository.database;

import dao.PerfilVendedorDAO;
import dao.PerfilVendedorDAOSQLite;
import factory.dao.IDAOFactory;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;
import model.Vendedor;
import repository.IPerfilVendedorRepository;

/**
 *
 * @author berna
 */
public class PerfilVendedorRepository implements IPerfilVendedorRepository{
    private final PerfilVendedorDAO perfilVendedorDAO;

    public PerfilVendedorRepository(IDAOFactory daoFactory) throws SQLException {
       this.perfilVendedorDAO = daoFactory.getPerfilVendedorDAO();
    }
    
    @Override
    public void adicionaPerfil(Vendedor vendedor) throws SQLException {
        perfilVendedorDAO.criar(vendedor);
    }
    
    public Optional<Vendedor> getPorIdUsuario(Integer id) throws SQLException {
        return perfilVendedorDAO.buscaPorIdUsuario(id);
    }
    
    public List<Vendedor> getTodosVendedores() throws SQLException {
        return perfilVendedorDAO.buscaTodos();
    }
    
    @Override
    public void deletaPerfil(Integer id) throws SQLException {
        perfilVendedorDAO.deletar(id);
    }
    
}
