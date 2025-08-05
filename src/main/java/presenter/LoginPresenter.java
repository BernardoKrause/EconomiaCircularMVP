/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package presenter;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JOptionPane;
import model.Usuario;
import service.AutenticacaoService;
import view.LoginView;

/**
 *
 * @author berna
 */
public class LoginPresenter {
    
    private LoginView view;
    private AutenticacaoService autenticacaoService;
    private Usuario usuario;
    
    public LoginPresenter(AutenticacaoService autenticacaoService) {
        this.autenticacaoService = autenticacaoService;
        
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
    
    public void autenticar() {
        String email = view.getTxtEmail().getText();
        String senha = view.getTxtSenha().getText();
        Usuario usuario = new Usuario(email, senha);
        
        autenticacaoService.autenticar(usuario);
        
        if (usuario.isAutenticado()) {
            try {
                HomePresenter homePresenter = new HomePresenter(usuario);
                view.dispose();
            } catch (Exception ex) {
                throw new RuntimeException(ex.getMessage());
            }
        }
    }
    
    public void cancelar() {
        view.dispose();
    }
}
