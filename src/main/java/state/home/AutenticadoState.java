/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package state.home;

import command.AbrirCadastroItemCommand;
import command.perfil.CriarPerfilVendedorCommand;
import command.usuario.SairUsuarioCommand;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JOptionPane;
import model.Usuario;
import presenter.GerenciadorTelas;
import presenter.HomePresenter;
import service.PerfilService;

/**
 *
 * @author caiof
 */
public class AutenticadoState extends HomePresenterState{
    private Usuario usuario;
    private PerfilService perfilService;
    
    public AutenticadoState(HomePresenter presenter, Usuario usuario) {
        super(presenter);
        
        this.usuario=usuario;
        
        view.getMenuUsuario().setText(usuario.getNome());
        view.getMenuVendedor().setVisible(true);
        view.getMenuComprador().setVisible(true);
        view.getMItemEntrarUsuario().setVisible(false);
        view.getMItemCadastrarUsuario().setVisible(false);
        view.getMItemSairUsuario().setVisible(true);
        
        if (usuario.getPerfilComprador().isEmpty()) {
            view.getMItemAcessarPerfilComprador().setVisible(false);
            view.getMItemCriarPerfilComprador().addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    try {
                        perfilService.criarPerfilComprador(usuario);
                        view.getMItemAcessarPerfilComprador().setVisible(true);
                        JOptionPane.showMessageDialog(view, "Solicitação enviada ao administrador");
                    } catch (Exception ex) {
                        JOptionPane.showMessageDialog(view, ex);
                    }
                }
            });
        }else{
            view.getMItemAcessarPerfilComprador().setVisible(true);
            view.getMItemAcessarPerfilComprador().addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    try {
//                        new AbrirTelaCompradorCommand().executar();  
                    } catch (Exception ex) {
                        JOptionPane.showMessageDialog(view, ex);
                    }
                }
            });
        }
        if (usuario.getPerfilVendedor().isEmpty()) {
            view.getMItemAcessarPerfilVendedor().setVisible(false);

            view.getMItemCriarPerfilVendedor().addActionListener(new ActionListener() {
               @Override
               public  void actionPerformed(ActionEvent e) {
                    try {
                        new CriarPerfilVendedorCommand(usuario).executar();
                        view.getMItemAcessarPerfilVendedor().setVisible(true);  
                        JOptionPane.showMessageDialog(view, "Solicitação enviada ao administrador");
                   } catch (Exception ex) {
                       JOptionPane.showMessageDialog(view, ex);
                   }
               }
            });
        } else{
            view.getMItemAcessarPerfilVendedor().setVisible(true);
            view.getMItemAcessarPerfilVendedor().addActionListener(new ActionListener() {
               @Override
               public  void actionPerformed(ActionEvent e) {
                    try {
                        // AbrirTelaVendedorCommand().executar(); 
                    } catch (Exception ex) {
                       JOptionPane.showMessageDialog(view, ex);
                   }
               }
            });
        }
        
        
        view.getMItemPublicarItem().addActionListener(new ActionListener() {
           @Override
           public  void actionPerformed(ActionEvent e) {
               try {
                   if (!usuario.getPerfilVendedor().isEmpty()) {
                        JOptionPane.showMessageDialog(view, "Você ainda não possui um perfil Vendedor");
                    } else {
                       new AbrirCadastroItemCommand().executar();
                    }
               } catch (Exception ex) {
                   JOptionPane.showMessageDialog(view, ex);
               }
           }
        });
        
        view.getMItemSairUsuario().addActionListener(new ActionListener() {
           @Override
           public  void actionPerformed(ActionEvent e) {
               try {
                    new SairUsuarioCommand().executar();
               } catch (Exception ex) {
                   JOptionPane.showMessageDialog(view, ex);
               }
           }
        });
        
        view.setVisible(true);
    }
    
    public void sairUsuario(){
        presenter.sairUsuario();
    }
    
    public void criarPerfilVendedor(){
        throw new RuntimeException("Não é possivel salvar estando nesse estado!");
    }
    
    public void acessarPerfilVendedor(){
        throw new RuntimeException("Não é possivel salvar estando nesse estado!");
    }
    
    public void criarPerfilComprador(){
        throw new RuntimeException("Não é possivel salvar estando nesse estado!");
    }
    
    public void acessarPerfilComprador(){
        throw new RuntimeException("Não é possivel salvar estando nesse estado!");
    }
}
