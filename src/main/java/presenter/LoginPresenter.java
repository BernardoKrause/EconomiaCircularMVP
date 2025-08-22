/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package presenter;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import javax.swing.JInternalFrame;
import javax.swing.JOptionPane;
import model.Usuario;
import service.UsuarioService;
import view.usuario.LoginView;

/**
 *
 * @author berna
 */
public class LoginPresenter extends AbstractPresenter {

    private UsuarioService usuarioService;
    private LoginView loginView;
    
    public LoginPresenter(UsuarioService usuarioService) {
        this.usuarioService = usuarioService;

        this.loginView = new LoginView();

        loginView.setVisible(false);
        
        resetButtonActions(loginView.getBtnAcessar());
        loginView.getBtnAcessar().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    autenticar();
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(view, ex);
                }
            }
        });

        resetButtonActions(loginView.getBtnCancelar());
        loginView.getBtnCancelar().addActionListener(new ActionListener() {
           @Override
           public  void actionPerformed(ActionEvent e) {
               try {
                   cancelar();
               } catch (Exception ex) {
                   JOptionPane.showMessageDialog(view, ex);
               }
           }
        });

        loginView.getTxtEmail().setText("");
        loginView.getTxtSenha().setText("");
        loginView.setVisible(true);
        this.view = loginView;
    }
    
    public void autenticar() throws SQLException {
        String email = loginView.getTxtEmail().getText();
        String senha = loginView.getTxtSenha().getText();
        Usuario usuario = new Usuario(email, senha);
        
        usuarioService.autenticarUsuario(usuario);
        
        if (usuario.isAutenticado()) {
            try {
                GerenciadorTelas.getInstancia().getPresenter().entrarUsuario(usuario);
                GerenciadorTelas.getInstancia().removeTelaAberta("login");
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(loginView, ex);
            }
        }
    }
    
    public void cancelar() {
        GerenciadorTelas.getInstancia().removeTelaAberta("login");
    }

}
