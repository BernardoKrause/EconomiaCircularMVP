/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package presenter;

import command.AbrirCadastroItemCommand;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JDesktopPane;
import javax.swing.JOptionPane;
import model.Usuario;
import service.PerfilService;
import view.HomeView;

/**
 *
 * @author berna
 */
public class HomePresenter {
    
    private HomeView view;
    private PerfilService perfilService;
    
    public HomePresenter(Usuario usuario) {
        if (usuario == null){
            throw new RuntimeException("Usuario não pode ser nulo!");
        }
    
        this.perfilService = new PerfilService();
        
        view = new HomeView();
        
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
    
    public JDesktopPane getDesktop(){
        return view.getDesktopPane();
    }
}
