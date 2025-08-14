/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package state.home;

import command.item.AbrirCadastroItemCommand;
import command.perfil.CriarPerfilCompradorCommand;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JOptionPane;
import model.Perfil;
import model.Usuario;
import presenter.HomePresenter;

/**
 *
 * @author caiof
 */
public class VendedorState extends HomePresenterState{
    private Usuario usuario;
    
    public VendedorState(HomePresenter presenter, Usuario usuario) {
        super(presenter);
    
        this.usuario=usuario;
        
        view.getMenuUsuario().setText(usuario.getNome());
        view.getMenuVendedor().setVisible(true);
        view.getMenuComprador().setVisible(true);
        view.getMenuItem().setVisible(true);
        view.getMItemEntrarUsuario().setVisible(false);
        view.getMItemCadastrarUsuario().setVisible(false);
        view.getMItemSairUsuario().setVisible(true);
        
        if (usuario.getPerfilComprador().isEmpty()) {
            view.getMItemAcessarPerfilComprador().setVisible(false);
            view.getMItemCriarPerfilComprador().setVisible(true);
            view.getMItemCriarPerfilComprador().addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    try {
                        new CriarPerfilCompradorCommand(usuario).executar();
                        view.getMItemAcessarPerfilComprador().setVisible(true);
                        JOptionPane.showMessageDialog(view, "Solicitação enviada ao administrador");
                    } catch (Exception ex) {
                        JOptionPane.showMessageDialog(view, ex);
                    }
                }
            });
        }
        else{
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
        
        view.getMItemAcessarPerfilVendedor().setVisible(false);
        view.getMItemCriarPerfilVendedor().setVisible(false);
        view.getMItemVerPerfilVendedor().setVisible(true);
        
        view.getMItemPublicarItem().addActionListener(new ActionListener() {
           @Override
           public  void actionPerformed(ActionEvent e) {
               try {
                    Perfil perfil = usuario.getPerfilVendedor().get();
                    new AbrirCadastroItemCommand(perfil).executar();
               } catch (Exception ex) {
                   JOptionPane.showMessageDialog(view, ex);
               }
           }
        });
        
    }
    
}
