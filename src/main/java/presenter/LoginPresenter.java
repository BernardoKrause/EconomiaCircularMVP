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
public class LoginPresenter implements IPresenter {
    
    private LoginView view;
    private UsuarioService usuarioService;
    
    public LoginPresenter(UsuarioService usuarioService) {
        this.usuarioService = usuarioService;
        
        view = new LoginView();
        
        view.setVisible(false);
        view.getBtnAcessar().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    autenticar();
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(view, ex);
                }
            }
        });
        
        view.getBtnCancelar().addActionListener(new ActionListener() {
           @Override
           public  void actionPerformed(ActionEvent e) {
               try {
                   cancelar();
               } catch (Exception ex) {
                   JOptionPane.showMessageDialog(view, ex);
               }
           }
        });
        
        view.getTxtEmail().setText("");
        view.getTxtSenha().setText("");
        view.setVisible(true);
    }
    
    public void autenticar() throws SQLException {
        String email = view.getTxtEmail().getText();
        String senha = view.getTxtSenha().getText();
        Usuario usuario = new Usuario(email, senha);
        
        usuarioService.autenticarUsuario(usuario);
        
        if (usuario.isAutenticado()) {
            try {
                GerenciadorTelas.getInstancia().getPresenter().entrarUsuario(usuario);
                view.dispose();
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(view, ex);
            }
        }
    }
    
    public void cancelar() {
        view.dispose();
    }

    @Override
    public JInternalFrame getView() {
        return view;
    }
}
