/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package factory.dao;

import dao.ICondutaDAO;
import dao.IDefeitoDAO;
import dao.IDenunciaDAO;
import dao.IItemDAO;
import dao.IMaterialDAO;
import dao.IOfertaDAO;
import dao.IPerfilDAO;
import dao.IReputacaoDAO;
import dao.ITransacaoDAO;
import dao.IUsuarioDAO;

/**
 *
 * @author berna
 */
public abstract class IDAOFactory {
    public abstract ICondutaDAO getCondutaDAO();
    public abstract IDefeitoDAO getDefeitoDAO();
    public abstract IDenunciaDAO getDenunciaDAO();
    public abstract IItemDAO getItemDAO();
    public abstract IMaterialDAO getMaterialDAO();
    public abstract IPerfilDAO getPerfilCompradorDAO();
    public abstract IPerfilDAO getPerfilVendedorDAO();
    public abstract IReputacaoDAO getReputacaoDAO();
    public abstract ITransacaoDAO getTransacaoDAO();
    public abstract IUsuarioDAO getUsuarioDAO();
    public abstract IOfertaDAO getOfertaDAO();
    
    public static IDAOFactory getDAOFactory(String sgbd) {
        if (sgbd.equalsIgnoreCase("SQLite")) {
            return new SQLiteDAOFactory();
        } else if (sgbd.equalsIgnoreCase("H2")) {
            return new H2DAOFactory();
        } else {
            throw new IllegalArgumentException("SGBD não suportado.");
        }
    }
}
