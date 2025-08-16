/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package repository.teste;

import dao.PerfilVendedorDAO;
import dao.PerfilVendedorDAOSQLite;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import model.Vendedor;
import repository.IPerfilVendedorRepository;

/**
 *
 * @author caiof
 */
public class PerfilVendedorRepositoryTeste implements IPerfilVendedorRepository{
    private List<Vendedor> VendedoresCriados;

    public PerfilVendedorRepositoryTeste() throws SQLException {
       this.VendedoresCriados = new ArrayList<>();
    }
    
    @Override
    public void adicionaPerfil(Vendedor vendedor) throws SQLException {
        VendedoresCriados.add(vendedor);
    }
    
    @Override
    public List<Vendedor> getTodosVendedores() throws SQLException {
        return VendedoresCriados;
    }
    
    @Override
    public void deletaPerfil(Integer id) throws SQLException {
        //nao tem
    }
    
}

