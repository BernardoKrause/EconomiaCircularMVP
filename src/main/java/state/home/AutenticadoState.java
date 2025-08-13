/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package state.home;

import command.AbrirCadastroItemCommand;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JOptionPane;
import presenter.GerenciadorTelas;
import presenter.HomePresenter;
import service.PerfilService;

/**
 *
 * @author caiof
 */
public class AutenticadoState extends HomePresenterState{
    
    public AutenticadoState(HomePresenter presenter) {
        super(presenter);
        
        PerfilService perfilService = new PerfilService();
        
        view.setVisible(false);
        
        System.out.print(usuario.getId());
        if (usuario.getPerfilComprador().isEmpty()) {
            view.getMItemCriarPerfilComprador().addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    try {
                        perfilService.criarPerfilComprador(usuario);
                        JOptionPane.showMessageDialog(view, "Solicitação enviada ao administrador");
                    } catch (Exception ex) {
                        JOptionPane.showMessageDialog(view, ex);
                    }
                }
            });
        }else{
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
            view.getMItemCriarPerfilVendedor().addActionListener(new ActionListener() {
               @Override
               public  void actionPerformed(ActionEvent e) {
                   try {
                        perfilService.criarPerfilVendedor(usuario);
                        JOptionPane.showMessageDialog(view, "Solicitação enviada ao administrador");
                   } catch (Exception ex) {
                       JOptionPane.showMessageDialog(view, ex);
                   }
               }
            });
        } else{
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
        
        view.setVisible(true);
    }
    
    public void sairUsuario(){
        GerenciadorTelas.getInstancia().deslogar();
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
