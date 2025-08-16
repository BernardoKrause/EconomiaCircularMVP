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
public class H2DAOFactory extends DAOFactory {
 
    @Override
    public ItemDAO getItemDAO() {
        return null;
    }

    @Override
    public DenunciaDAO getDenunciaDAO() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public InsigniaDAO getInsigniaDAO() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public PerfilCompradorDAO getPerfilCompradorDAO() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public PerfilSolicitacaoDAO getPerfilSolicitacaoDAO() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public PerfilVendedorDAO getPerfilVendedorDAO() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public UsuarioDAO getUsuarioDAO() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
    

    
}
