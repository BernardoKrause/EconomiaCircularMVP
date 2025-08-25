/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package presenter;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JInternalFrame;
import javax.swing.JOptionPane;

import service.UsuarioService;
import view.usuario.SignupView;

/**
 *
 * @author caiof
 */
public class SignupPresenter extends AbstractPresenter {

    private SignupView signupView;
    private UsuarioService service;
    
    public SignupPresenter(UsuarioService service){
        this.service=service;
        signupView = new SignupView();
        
        signupView.setVisible(false);
    
        resetButtonActions(signupView.getBtnCadastrar());
        signupView.getBtnCadastrar().addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e){
                try{
                    cadastrar();
                }catch(Exception ex){
                    JOptionPane.showMessageDialog(view, ex);
                }
            }
        });
        
        resetButtonActions(signupView.getBtnCancelar());
        signupView.getBtnCancelar().addActionListener(new ActionListener() {
           @Override
           public  void actionPerformed(ActionEvent e) {
               try {
                   cancelar();
               } catch (Exception ex) {
                   JOptionPane.showMessageDialog(view, ex);
               }
           }
        });
        
        signupView.setVisible(true);
        view=signupView;
    }
    
    private void cadastrar(){
        String nome = signupView.getTxtNome().getText();
        String email = signupView.getTxtEmail().getText();
        String telefone = signupView.getTxtTelefone().getText();
        String senha = signupView.getTxtSenha().getText();
        
        try {
            service.cadastrarUsuario(nome, email, telefone, senha);
            JOptionPane.showMessageDialog(signupView, "Usuario cadastrado com sucesso!");
            GerenciadorTelas.getInstancia().removeTelaAberta("signup");
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(view, ex);
        }
    }
    
    public void cancelar() {
        GerenciadorTelas.getInstancia().removeTelaAberta("signup");
    }
}
