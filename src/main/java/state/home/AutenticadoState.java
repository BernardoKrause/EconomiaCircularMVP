/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package state.home;

import command.perfil.AcessarPerfilVendedorCommand;
import command.perfil.CriarPerfilCompradorCommand;
import command.perfil.CriarPerfilVendedorCommand;
import command.usuario.SairUsuarioCommand;
import factory.repository.SeletorRepositoryFactory;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import javax.swing.JOptionPane;
import model.Usuario;
import presenter.HomePresenter;
import repository.IPerfilRepository;

/**
 *
 * @author caiof
 */
public class AutenticadoState extends HomePresenterState{
    private Usuario usuario;
    
    public AutenticadoState(HomePresenter presenter, Usuario usuario) {
        super(presenter);
        
        this.usuario=usuario;
        
        setVisibles();
            
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
        
        view.getMItemCriarPerfilVendedor().addActionListener(new ActionListener() {
           @Override
           public  void actionPerformed(ActionEvent e) {
                try {
                    criarPerfilVendedor();
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(view, ex);
                }
            }
        });
         
        view.getMItemAcessarPerfilVendedor().addActionListener(new ActionListener() {
            @Override
            public  void actionPerformed(ActionEvent e) {
                try {
                    acessarPerfilVendedor(); 
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(view, ex);
                }
            }
        });
        
        
        view.getMItemSairUsuario().addActionListener(new ActionListener() {
           @Override
           public  void actionPerformed(ActionEvent e) {
               try {
                    sairUsuario();
               } catch (Exception ex) {
                   JOptionPane.showMessageDialog(view, ex);
               }
           }
        });
        
        view.setVisible(true);
    }
    
    @Override
    public void sairUsuario(){
        new SairUsuarioCommand().executar();
    }
    
    @Override
    public void criarPerfilVendedor() throws SQLException {
        IPerfilRepository perfilRepository = SeletorRepositoryFactory.obterInstancia().criarPerfilVendedorRepository();
        new CriarPerfilVendedorCommand(usuario,perfilRepository).executar();
        view.getMItemAcessarPerfilVendedor().setVisible(true); 
        view.getMItemCriarPerfilVendedor().setVisible(false); 
        JOptionPane.showMessageDialog(view, "Solicitação enviada ao administrador");
    }
    
    @Override
    public void acessarPerfilVendedor() throws SQLException {
        IPerfilRepository perfilRepository = SeletorRepositoryFactory.obterInstancia().criarPerfilVendedorRepository();
        new AcessarPerfilVendedorCommand(usuario,perfilRepository).executar();
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
    
    @Override
    public void setVisibles(){
        view.getMenuUsuario().setText(usuario.getNome());
        view.getMenuVendedor().setVisible(true);
        view.getMenuComprador().setVisible(true);
        view.getMenuItem().setVisible(false);
        view.getMItemEntrarUsuario().setVisible(false);
        view.getMItemCadastrarUsuario().setVisible(false);
        view.getMItemSairUsuario().setVisible(true);
        view.getMItemVerPerfilVendedor().setVisible(false);
        view.getMItemVerPerfilComprador().setVisible(false);
        
        if (usuario.getPerfilComprador().isEmpty()) {
            view.getMItemAcessarPerfilComprador().setVisible(false);
            view.getMItemCriarPerfilComprador().setVisible(true);
        }
        else{
            view.getMItemAcessarPerfilComprador().setVisible(true);
            view.getMItemCriarPerfilComprador().setVisible(false);
        }
        
        if (usuario.getPerfilVendedor().isEmpty()) {
            view.getMItemAcessarPerfilVendedor().setVisible(false);
            view.getMItemCriarPerfilVendedor().setVisible(true);
        }
        else{
            view.getMItemAcessarPerfilVendedor().setVisible(true);
            view.getMItemCriarPerfilVendedor().setVisible(false);
        }
    }
}
