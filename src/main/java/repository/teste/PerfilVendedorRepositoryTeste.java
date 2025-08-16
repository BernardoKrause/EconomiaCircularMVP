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
import java.util.Optional;
import java.util.logging.Level;
import java.util.logging.Logger;

import model.Reputacao;
import model.Vendedor;
import repository.IPerfilVendedorRepository;

/**
 *
 * @author caiof
 */
public class PerfilVendedorRepositoryTeste implements IPerfilVendedorRepository{
    private List<Vendedor> VendedoresCriados;
    private static PerfilVendedorRepositoryTeste instancia;

    private PerfilVendedorRepositoryTeste() throws SQLException {
       this.VendedoresCriados = new ArrayList<>();
    }

    public static PerfilVendedorRepositoryTeste getInstancia(){
        if (instancia == null) {
            try {
                instancia = new PerfilVendedorRepositoryTeste();
            } catch (SQLException ex) {
                Logger.getLogger(PerfilVendedorRepositoryTeste.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return instancia;
    }

    @Override
    public void adicionaPerfil(Vendedor vendedor) throws SQLException {
        VendedoresCriados.add(vendedor);
    }
    
    @Override
    public Optional<Vendedor> getPorIdUsuario(Integer id) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
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

