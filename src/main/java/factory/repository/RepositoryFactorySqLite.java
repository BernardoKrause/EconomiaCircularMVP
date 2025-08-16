/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package factory.repository;

import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import repository.IDefeitosTipoRepository;
import repository.IDenunciaRepository;
import repository.IItemRepository;
import repository.IPerfilSolicitacaoRepository;
import repository.IPerfilVendedorRepository;
import repository.IUsuarioRepository;
import repository.sqlite.DenunciaRepositorySqLite;
import repository.sqlite.ItemRepositorySqLite;
import repository.sqlite.PerfilSolicitacaoRepositorySqLite;
import repository.sqlite.PerfilVendedorRepositorySqLite;
import repository.sqlite.UsuarioRepositorySqLite;

/**
 *
 * @author caiof
 */
public class RepositoryFactorySqLite implements IRepositoryFactory{

    @Override
    public IUsuarioRepository criarUsuarioRepository() {
        try {
            return new UsuarioRepositorySqLite();
        } catch (SQLException ex) {
            Logger.getLogger(RepositoryFactorySqLite.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    @Override
    public IPerfilVendedorRepository criarPerfilVendedorRepository() {
        try {
            return new PerfilVendedorRepositorySqLite();
        } catch (SQLException ex) {
            Logger.getLogger(RepositoryFactorySqLite.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    @Override
    public IPerfilSolicitacaoRepository criarPerfilSolicitacaoRepository() {
        try {
            return new PerfilSolicitacaoRepositorySqLite();
        } catch (SQLException ex) {
            Logger.getLogger(RepositoryFactorySqLite.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    @Override
    public IItemRepository criarItemRepository() {
        return new ItemRepositorySqLite();
    }

    @Override
    public IDenunciaRepository criarDenunciaRepository() {
        try {
            return new DenunciaRepositorySqLite();
        } catch (SQLException ex) {
            Logger.getLogger(RepositoryFactorySqLite.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    @Override
    public IDefeitosTipoRepository criarDefeitosTipoRepository() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
//        return new DefeitosTipoRepositorySqLite();
    }
    
}
