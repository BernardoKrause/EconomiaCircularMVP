/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package factory.dao;

import dao.DenunciaDAOSQLite;
import dao.ICondutaDAO;
import dao.ItemDAOSQLite;
import dao.PerfilCompradorDAOSQLite;
import dao.PerfilSolicitacaoDAOSQLite;
import dao.PerfilVendedorDAOSQLite;
import dao.UsuarioDAOSQLite;
import dao.IDenunciaDAO;
import dao.IItemDAO;
import dao.IPerfilCompradorDAO;
import dao.IPerfilSolicitacaoDAO;
import dao.IPerfilVendedorDAO;
import dao.IUsuarioDAO;

/**
 *
 * @author berna
 */
public class SQLiteDAOFactory extends IDAOFactory {

    @Override
    public ICondutaDAO getCondutaDAO() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
    
    @Override
    public IItemDAO getItemDAO() {
        return new ItemDAOSQLite();
    }

    @Override
    public IDenunciaDAO getDenunciaDAO() {
        return new DenunciaDAOSQLite();
    }

    @Override
    public IPerfilCompradorDAO getPerfilCompradorDAO() {
        return new PerfilCompradorDAOSQLite();
    }

    @Override
    public IPerfilSolicitacaoDAO getPerfilSolicitacaoDAO() {
        return new PerfilSolicitacaoDAOSQLite();
    }

    @Override
    public IPerfilVendedorDAO getPerfilVendedorDAO() {
        return new PerfilVendedorDAOSQLite();
    }

    @Override
    public IUsuarioDAO getUsuarioDAO() {
        return new UsuarioDAOSQLite();
    }
    
}
