/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package repository.sqlite;

import dao.PerfilVendedorDAO;
import dao.PerfilVendedorDAOSQLite;
import java.sql.SQLException;
import java.util.List;
import model.Vendedor;
import repository.IPerfilVendedorRepository;

/**
 *
 * @author berna
 */
public class PerfilVendedorRepositorySqLite implements IPerfilVendedorRepository{
    private final PerfilVendedorDAO perfilVendedorDAO;

    public PerfilVendedorRepositorySqLite() throws SQLException {
       this.perfilVendedorDAO = new PerfilVendedorDAOSQLite();
    }
    
    @Override
    public void adicionaPerfil(Vendedor vendedor) throws SQLException {
        perfilVendedorDAO.criar(vendedor);
    }
    
    @Override
    public List<Vendedor> getTodosVendedores() throws SQLException {
        return perfilVendedorDAO.buscaTodos();
    }
    
    @Override
    public void deletaPerfil(Integer id) throws SQLException {
        perfilVendedorDAO.deletar(id);
    }
    
}
