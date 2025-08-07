/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package presenter;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
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
        this.perfilService = new PerfilService();
        
        view = new HomeView();
        
        view.setVisible(false);
        
        view.getBtnCriarPerfilComprador().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    perfilService.criarPerfilVendedor(usuario);
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(view, ex);
                }
            }
        });
        
        view.getBtnComprador().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    JOptionPane.showMessageDialog(view, "Exibindo tela Comprador");
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(view, ex);
                }
            }
        });
        
        view.getBtnVendedor().addActionListener(new ActionListener() {
           @Override
           public  void actionPerformed(ActionEvent e) {
               try {
                   JOptionPane.showMessageDialog(view, "Exibindo tela Vendedor");
               } catch (Exception ex) {
                   JOptionPane.showMessageDialog(view, ex);
               }
           }
        });
        
        view.setVisible(true);
    }
}
