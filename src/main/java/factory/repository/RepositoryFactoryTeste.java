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
import repository.teste.DenunciaRepositoryTeste;
import repository.teste.ItemRepositoryTeste;
import repository.teste.PerfilSolicitacaoRepositoryTeste;
import repository.teste.PerfilVendedorRepositoryTeste;
import repository.teste.UsuarioRepositoryTeste;

/**
 *
 * @author caiof
 */
public class RepositoryFactoryTeste implements IRepositoryFactory{

    @Override
    public IUsuarioRepository criarUsuarioRepository() {
        return UsuarioRepositoryTeste.getintancia();
    }

    @Override
    public IPerfilVendedorRepository criarPerfilVendedorRepository() {
        try {
            return new PerfilVendedorRepositoryTeste();
        } catch (SQLException ex) {
            Logger.getLogger(RepositoryFactoryTeste.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    @Override
    public IPerfilSolicitacaoRepository criarPerfilSolicitacaoRepository() {
        try {
            return new PerfilSolicitacaoRepositoryTeste();
        } catch (SQLException ex) {
            Logger.getLogger(RepositoryFactoryTeste.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    @Override
    public IItemRepository criarItemRepository() {
        return new ItemRepositoryTeste();
    }

    @Override
    public IDenunciaRepository criarDenunciaRepository() {
        try {
            return new DenunciaRepositoryTeste();
        } catch (SQLException ex) {
            Logger.getLogger(RepositoryFactoryTeste.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    @Override
    public IDefeitosTipoRepository criarDefeitosTipoRepository() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
//        return new DefeitosTipoRepositoryTeste();
    }
    
}

