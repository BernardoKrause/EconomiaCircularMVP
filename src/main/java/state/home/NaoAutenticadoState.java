/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package state.home;

import command.usuario.AbrirCadastroUsuarioCommand;
import command.usuario.AbrirEntrarUsuarioCommand;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JOptionPane;
import presenter.HomePresenter;

/**
 *
 * @author caiof
 */
public class NaoAutenticadoState extends HomePresenterState{
    
    public NaoAutenticadoState(HomePresenter presenter) {
        super(presenter);
                
        view.getMenuUsuario().setVisible(true);
        view.getMenuUsuario().setText("Usuario");
        view.getMenuVendedor().setVisible(false);
        view.getMenuComprador().setVisible(false);
        view.getMenuItem().setVisible(false);
        view.getMItemEntrarUsuario().setVisible(true);
        view.getMItemCadastrarUsuario().setVisible(true);
        view.getMItemSairUsuario().setVisible(false);
        
        view.getMItemEntrarUsuario().addActionListener(
            new ActionListener(){
                @Override
                public void actionPerformed(ActionEvent e) {
                    try {
                        entrarUsuario();
                    } catch (Exception ex) {
                        JOptionPane.showMessageDialog(view, ex);
                    }
                }
        });
        
        view.getMItemCadastrarUsuario().addActionListener(
            new ActionListener(){
                @Override
                public void actionPerformed(ActionEvent e) {
                    try {
                        cadastrarUsuario();
                    } catch (Exception ex) {
                        JOptionPane.showMessageDialog(view, ex);
                    }
                }
        });
        
        view.setVisible(true);
    }

    @Override
    public void entrarUsuario() {
        new AbrirEntrarUsuarioCommand().executar();
    }


    @Override
    public void cadastrarUsuario(){
        new AbrirCadastroUsuarioCommand().executar();
    }
}
