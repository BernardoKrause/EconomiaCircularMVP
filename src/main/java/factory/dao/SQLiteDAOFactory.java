/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package factory.dao;

import dao.sqlite.CondutaDAOSQLite;
import dao.sqlite.DefeitoDAOSQLite;
import dao.sqlite.DenunciaDAOSQLite;
import dao.ICondutaDAO;
import dao.IDefeitoDAO;
import dao.sqlite.ItemDAOSQLite;
import dao.sqlite.PerfilCompradorDAOSQLite;
import dao.sqlite.PerfilVendedorDAOSQLite;
import dao.sqlite.UsuarioDAOSQLite;
import dao.IDenunciaDAO;
import dao.IItemDAO;
import dao.IMaterialDAO;
import dao.IPerfilDAO;
import dao.IReputacaoDAO;
import dao.IUsuarioDAO;
import dao.sqlite.MaterialDAOSQLite;
import dao.sqlite.ReputacaoDAOSQLite;

/**
 *
 * @author berna
 */
public class SQLiteDAOFactory extends IDAOFactory {

    @Override
    public ICondutaDAO getCondutaDAO() {
        return new CondutaDAOSQLite();
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
    public IPerfilDAO getPerfilCompradorDAO() {
        return new PerfilCompradorDAOSQLite();
    }

    @Override
    public IPerfilDAO getPerfilVendedorDAO() {
        return new PerfilVendedorDAOSQLite();
    }

    @Override
    public IUsuarioDAO getUsuarioDAO() {
        return new UsuarioDAOSQLite();
    }

    @Override
    public IReputacaoDAO getReputacaoDAO() {
        return new ReputacaoDAOSQLite();
    }

    @Override
    public IDefeitoDAO getDefeitoDAO() {
        return new DefeitoDAOSQLite();
    }

    @Override
    public IMaterialDAO getMaterialDAO() {
        return new MaterialDAOSQLite();
    }
    
}
