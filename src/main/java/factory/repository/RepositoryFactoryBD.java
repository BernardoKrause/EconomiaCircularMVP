/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package factory.repository;

import factory.dao.IDAOFactory;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import repository.ICondutaRepository;
import repository.IDefeitosTipoRepository;
import repository.IDenunciaRepository;
import repository.IItemRepository;
import repository.IPerfilSolicitacaoRepository;
import repository.IPerfilRepository;
import repository.IReputacaoRepository;
import repository.IUsuarioRepository;
import repository.database.DefeitosTipoRepository;
import repository.database.DenunciaRepository;
import repository.database.ItemRepository;
import repository.database.PerfilSolicitacaoRepository;
import repository.database.PerfilVendedorRepository;
import repository.database.UsuarioRepository;

/**
 *
 * @author caiof
 */
public class RepositoryFactoryBD implements IRepositoryFactory{

    private IDAOFactory daoFactory;
    
    public RepositoryFactoryBD(String sgbd) {
        daoFactory = IDAOFactory.getDAOFactory(sgbd);
    }
    
    @Override
    public IUsuarioRepository criarUsuarioRepository() {
        try {
            return new UsuarioRepository(daoFactory);
        } catch (SQLException ex) {
            Logger.getLogger(RepositoryFactoryBD.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    @Override
    public IPerfilRepository criarPerfilVendedorRepository() {
        try {
            return new PerfilVendedorRepository(daoFactory);
        } catch (SQLException ex) {
            Logger.getLogger(RepositoryFactoryBD.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    @Override
    public IPerfilSolicitacaoRepository criarPerfilSolicitacaoRepository() {
        try {
            return new PerfilSolicitacaoRepository(daoFactory);
        } catch (SQLException ex) {
            Logger.getLogger(RepositoryFactoryBD.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    @Override
    public IItemRepository criarItemRepository() {
        try {
            return new ItemRepository(daoFactory);
        } catch (SQLException ex) {
            Logger.getLogger(RepositoryFactoryBD.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    @Override
    public IDenunciaRepository criarDenunciaRepository() {
        try {
            return new DenunciaRepository(daoFactory);
        } catch (SQLException ex) {
            Logger.getLogger(RepositoryFactoryBD.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    @Override
    public IDefeitosTipoRepository criarDefeitosTipoRepository() {
        try {
            return new DefeitosTipoRepository(daoFactory);
        } catch (SQLException ex) {
            Logger.getLogger(RepositoryFactoryBD.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    @Override
    public IReputacaoRepository criarReputacaoRepository() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public ICondutaRepository criarCondutarRepository() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
    
}
