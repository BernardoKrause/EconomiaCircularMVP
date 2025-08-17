/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package factory.dao;

import dao.ICondutaDAO;
import dao.IDenunciaDAO;
import dao.IInsigniaDAO;
import dao.IItemDAO;
import dao.IPerfilCompradorDAO;
import dao.IPerfilSolicitacaoDAO;
import dao.IPerfilVendedorDAO;
import dao.IUsuarioDAO;

/**
 *
 * @author berna
 */
public abstract class IDAOFactory {
    public abstract ICondutaDAO getCondutaDAO();
    public abstract IDenunciaDAO getDenunciaDAO();
    public abstract IInsigniaDAO getInsigniaDAO();
    public abstract IItemDAO getItemDAO();
    public abstract IPerfilCompradorDAO getPerfilCompradorDAO();
    public abstract IPerfilSolicitacaoDAO getPerfilSolicitacaoDAO();
    public abstract IPerfilVendedorDAO getPerfilVendedorDAO();
    public abstract IUsuarioDAO getUsuarioDAO();
    
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
