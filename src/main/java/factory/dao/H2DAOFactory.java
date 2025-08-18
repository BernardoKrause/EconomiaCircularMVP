/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package factory.dao;

import dao.ICondutaDAO;
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
public class H2DAOFactory extends IDAOFactory {
    
    @Override
    public ICondutaDAO getCondutaDAO() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
    
    @Override
    public IItemDAO getItemDAO() {
        return null;
    }

    @Override
    public IDenunciaDAO getDenunciaDAO() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public IPerfilCompradorDAO getPerfilCompradorDAO() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public IPerfilSolicitacaoDAO getPerfilSolicitacaoDAO() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public IPerfilVendedorDAO getPerfilVendedorDAO() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public IUsuarioDAO getUsuarioDAO() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
}
