/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package factory.dao;

import dao.DenunciaDAO;
import dao.DenunciaDAOSQLite;
import dao.InsigniaDAO;
import dao.InsigniaDAOSQLite;
import dao.ItemDAO;
import dao.ItemDAOSQLite;
import dao.PerfilCompradorDAO;
import dao.PerfilCompradorDAOSQLite;
import dao.PerfilSolicitacaoDAO;
import dao.PerfilSolicitacaoDAOSQLite;
import dao.PerfilVendedorDAO;
import dao.PerfilVendedorDAOSQLite;
import dao.UsuarioDAO;
import dao.UsuarioDAOSQLite;

/**
 *
 * @author berna
 */
public class SQLiteDAOFactory extends DAOFactory {

    @Override
    public ItemDAO getItemDAO() {
        return new ItemDAOSQLite();
    }

    @Override
    public DenunciaDAO getDenunciaDAO() {
        return new DenunciaDAOSQLite();
    }

    @Override
    public InsigniaDAO getInsigniaDAO() {
        return new InsigniaDAOSQLite();
    }

    @Override
    public PerfilCompradorDAO getPerfilCompradorDAO() {
        return new PerfilCompradorDAOSQLite();
    }

    @Override
    public PerfilSolicitacaoDAO getPerfilSolicitacaoDAO() {
        return new PerfilSolicitacaoDAOSQLite();
    }

    @Override
    public PerfilVendedorDAO getPerfilVendedorDAO() {
        return new PerfilVendedorDAOSQLite();
    }

    @Override
    public UsuarioDAO getUsuarioDAO() {
        return new UsuarioDAOSQLite();
    }
    
}
