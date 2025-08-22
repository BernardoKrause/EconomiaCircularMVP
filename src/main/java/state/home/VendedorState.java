/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package state.home;

import command.item.AbrirCadastroItemCommand;
import command.item.AbrirItensPublicadosCommand;
import command.perfil.AbrirVerPerfilVendedorCommand;
import command.perfil.CriarPerfilCompradorCommand;
import command.usuario.SairUsuarioCommand;
import factory.repository.SeletorRepositoryFactory;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import javax.swing.JOptionPane;
import model.Perfil;
import model.Usuario;
import model.Vendedor;
import presenter.HomePresenter;
import repository.IPerfilRepository;

/**
 *
 * @author caiof
 */
public class VendedorState extends HomePresenterState{
    private Vendedor vendedor;
    
    public VendedorState(HomePresenter presenter, Usuario usuarioAutenticado) throws SQLException {
        super(presenter, usuarioAutenticado);
    
        this.vendedor= (Vendedor) usuario.getPerfilVendedor().get();
        
        setVisibles();
        
        verPerfilVendedor();
        
        view.getMItemVerPerfilVendedor().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    verPerfilVendedor();
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(view, ex);
                }
            }
        });
        
        view.getMItemCriarPerfilComprador().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    criarPerfilComprador();
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(view, ex);
                }
            }
        });
        
        view.getMItemAcessarPerfilComprador().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    acessarPerfilComprador();
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(view, ex);
                }
            }
        });
        
        view.getMIItemPublicarItem().addActionListener(new ActionListener() {
           @Override
           public  void actionPerformed(ActionEvent e) {
               try {
                    publicarItem();
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
        view.getMItemAcessarPerfilVendedor().setVisible(false);
        view.getMItemCriarPerfilVendedor().setVisible(false);
        view.getMItemVerPerfilVendedor().setVisible(true);
        if (usuario.getPerfilComprador().isEmpty()) {
            view.getMItemAcessarPerfilComprador().setVisible(false);
            view.getMItemCriarPerfilComprador().setVisible(true);
        }
        else{
            view.getMItemAcessarPerfilComprador().setVisible(true);
            view.getMItemCriarPerfilComprador().setVisible(false);
        }
    }
    
    
    @Override
    public void criarPerfilComprador() throws SQLException {
//        IPerfilRepository perfilRepository = SeletorRepositoryFactory.obterInstancia().criarPerfilCompradorRepository();
//        new CriarPerfilCompradorCommand(usuario,perfilRepository).executar();
//        view.getMItemAcessarPerfilComprador().setVisible(true); 
//        view.getMItemCriarPerfilComprador().setVisible(false); 
//        JOptionPane.showMessageDialog(view, "Solicitação enviada ao administrador");
    }
    
    @Override
    public void acessarPerfilComprador(){
//        IPerfilRepository perfilRepository = SeletorRepositoryFactory.obterInstancia().criarPerfilCompradorRepository();
//        new AcessarPerfilCompradorCommand(usuario,perfilRepository).executar();
    }
    
    public void verPerfilVendedor() throws SQLException{
        IPerfilRepository perfilRepository = SeletorRepositoryFactory.obterInstancia().criarPerfilVendedorRepository();
        new AbrirVerPerfilVendedorCommand(vendedor,perfilRepository).executar();
    }
    
    @Override
    public void publicarItem() throws SQLException{
        Perfil perfil = usuario.getPerfilVendedor().get();
        new AbrirCadastroItemCommand(perfil).executar();
    }
    
    @Override
    public void verItens() throws SQLException{
        Perfil perfil = usuario.getPerfilVendedor().get();
        new AbrirItensPublicadosCommand(perfil).executar();
    }
}
