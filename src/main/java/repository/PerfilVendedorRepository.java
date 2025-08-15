/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package repository;

import dao.PerfilVendedorDAO;
import dao.PerfilVendedorDAOSQLite;
import java.sql.SQLException;
import model.Vendedor;

/**
 *
 * @author berna
 */
public class PerfilVendedorRepository {
    private final PerfilVendedorDAO perfilVendedorDAO;

    public PerfilVendedorRepository() throws SQLException {
       this.perfilVendedorDAO = new PerfilVendedorDAOSQLite();
    }
    
    public void salvarPerfil(Vendedor vendedor) throws SQLException {
        perfilVendedorDAO.criar(vendedor);
    }
}
