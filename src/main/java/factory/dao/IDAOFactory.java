/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package factory.dao;

import dao.DenunciaDAO;
import dao.InsigniaDAO;
import dao.ItemDAO;
import dao.PerfilCompradorDAO;
import dao.PerfilSolicitacaoDAO;
import dao.PerfilVendedorDAO;
import dao.UsuarioDAO;

/**
 *
 * @author berna
 */
public abstract class IDAOFactory {
    public abstract DenunciaDAO getDenunciaDAO();
    public abstract InsigniaDAO getInsigniaDAO();
    public abstract ItemDAO getItemDAO();
    public abstract PerfilCompradorDAO getPerfilCompradorDAO();
    public abstract PerfilSolicitacaoDAO getPerfilSolicitacaoDAO();
    public abstract PerfilVendedorDAO getPerfilVendedorDAO();
    public abstract UsuarioDAO getUsuarioDAO();
    
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
