/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package presenter;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JInternalFrame;
import javax.swing.JOptionPane;
import model.Usuario;
import service.UsuarioService;
import view.usuario.SignupView;

/**
 *
 * @author caiof
 */
public class SignupPresenter implements IPresenter{

    private SignupView view;
    private UsuarioService service;
    
    public SignupPresenter(UsuarioService service){
        this.service=service;
        view = new SignupView();
        
        view.setVisible(false);
    
        view.getBtnCadastrar().addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e){
                try{
                    cadastrar();
                }catch(Exception ex){
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
        
        view.setVisible(true);
    }
    
    @Override
    public JInternalFrame getView() {
        return view;
    }
    
    private void cadastrar(){
        String nome = view.getTxtNome().getText();
        String email = view.getTxtEmail().getText();
        String telefone = view.getTxtTelefone().getText();
        String senha = view.getTxtSenha().getText();
        
        try {
            service.cadastrarUsuario(nome, email, telefone, senha);
            JOptionPane.showMessageDialog(view, "Usuario cadastrado com sucesso!");
            view.dispose();
        } catch (Exception ex) {
            throw new RuntimeException(ex.getMessage());
        }
    }
    
    public void cancelar() {
        view.dispose();
    }
}
