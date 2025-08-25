/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package state.home;

import command.item.AbrirCadastroItemCommand;
import command.item.AbrirItensPublicadosCommand;
import command.perfil.AbrirVerPerfilCompradorCommand;
import command.perfil.AcessarPerfilVendedorCommand;
import command.perfil.CriarPerfilVendedorCommand;
import factory.repository.SeletorRepositoryFactory;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import javax.swing.JOptionPane;
import model.Comprador;
import model.Perfil;
import model.Usuario;
import presenter.HomePresenter;
import repository.IPerfilRepository;

/**
 *
 * @author caiof
 */
public class CompradorState extends HomePresenterState{
    private Comprador comprador;
    
    public CompradorState(HomePresenter presenter, Usuario usuarioAutenticado) throws SQLException {
        super(presenter, usuarioAutenticado);
    
        this.comprador= (Comprador) usuario.getPerfilComprador().get();
        
        setVisibles();
        
        verPerfilComprador();
        
        resetMenuItemActions(view.getMItemVerPerfilComprador());
        resetMenuItemActions(view.getMItemCriarPerfilVendedor());
        resetMenuItemActions(view.getMItemAcessarPerfilVendedor());
        resetMenuItemActions(view.getMIVerItem());
        resetMenuItemActions(view.getMItemSairUsuario());
        
        view.getMItemVerPerfilComprador().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    verPerfilComprador();
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(view, ex);
                }
            }
        });
        
        view.getMItemCriarPerfilVendedor().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    criarPerfilVendedor();
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(view, ex);
                }
            }
        });
        
        view.getMItemAcessarPerfilVendedor().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    acessarPerfilVendedor();
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(view, ex);
                }
            }
        });
        
        view.getMIVerItem().addActionListener(new ActionListener() {
           @Override
           public  void actionPerformed(ActionEvent e) {
               try {
                   verItens();
               } catch (Exception ex) {
                   JOptionPane.showMessageDialog(view, ex);
               }
           }
        });
        
    }
    
    @Override
    public void setVisibles(){        
        view.getMenuUsuario().setText(usuario.getNome());
        view.getMenuVendedor().setVisible(true);
        view.getMenuComprador().setVisible(true);
        view.getMenuItem().setVisible(true);
        view.getMItemEntrarUsuario().setVisible(false);
        view.getMItemCadastrarUsuario().setVisible(false);
        view.getMItemSairUsuario().setVisible(true);
        view.getMItemAcessarPerfilComprador().setVisible(false);
        view.getMItemCriarPerfilVendedor().setVisible(false);
        view.getMItemVerPerfilVendedor().setVisible(false);
        view.getMItemVerPerfilComprador().setVisible(true);
        view.getMIItemPublicarItem().setVisible(false);
        if (usuario.getPerfilVendedor().isEmpty()) {
            view.getMItemAcessarPerfilVendedor().setVisible(false);
            view.getMItemCriarPerfilVendedor().setVisible(true);
        }
        else{
            view.getMItemAcessarPerfilVendedor().setVisible(true);
            view.getMItemCriarPerfilVendedor().setVisible(false);
        }
    }
    
    @Override
    public void criarPerfilVendedor() throws SQLException {
        IPerfilRepository perfilRepository = SeletorRepositoryFactory.obterInstancia().criarPerfilVendedorRepository();
        new CriarPerfilVendedorCommand(usuario,perfilRepository).executar();
        view.getMItemAcessarPerfilVendedor().setVisible(true); 
        view.getMItemCriarPerfilVendedor().setVisible(false); 
    }
    
    @Override
    public void acessarPerfilVendedor() throws SQLException {
        IPerfilRepository perfilRepository = SeletorRepositoryFactory.obterInstancia().criarPerfilVendedorRepository();
        new AcessarPerfilVendedorCommand(usuario,perfilRepository).executar();
    }
    
    public void verPerfilComprador() throws SQLException{
        IPerfilRepository perfilRepository = SeletorRepositoryFactory.obterInstancia().criarPerfilVendedorRepository();
        new AbrirVerPerfilCompradorCommand(comprador,perfilRepository).executar();
    }
    
    @Override
    public void verItens() throws SQLException{
        Perfil perfil = usuario.getPerfilVendedor().get();
        new AbrirItensPublicadosCommand(perfil).executar();
    }
}

