/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package presenter;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JOptionPane;
import model.Usuario;
import repository.ItemRepository;
import repository.TiposDefeitoRepository;
import service.ItemService;
import service.PerfilService;
import service.SistemaDefeitosService;
import view.CompradorView;
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
        
        view.getMItemCriarPerfilComprador().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    if (usuario.getPerfilComprador().isEmpty()) {
                        // ao inves de criar, vai solicitar um perfil
                        //perfilService.criarPerfilComprador(usuario);
                        JOptionPane.showMessageDialog(view, "Solicitação enviada ao administrador");
                    } else {
                        JOptionPane.showMessageDialog(view, "Você já possui um perfil Comprador");
                    }
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(view, ex);
                }
            }
        });
        
        view.getMItemAcessarPerfilComprador().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    if (!usuario.getPerfilComprador().isEmpty()) {
                        JOptionPane.showMessageDialog(view, "Você ainda não possui um perfil Comprador");
                    } else {
                        CompradorView perfilCompradorView = new CompradorView();
                        view.getDesktopPane().add(perfilCompradorView);
                        perfilCompradorView.setVisible(true);
                        JOptionPane.showMessageDialog(view, "Exibindo tela Comprador");   
                    }
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(view, ex);
                }
            }
        });
        
        view.getMItemCriarPerfilVendedor().addActionListener(new ActionListener() {
           @Override
           public  void actionPerformed(ActionEvent e) {
               try {
                   if (usuario.getPerfilVendedor().isEmpty()) {
                        // ao inves de criar, vai solicitar um perfil
                        //perfilService.criarPerfilVendedor(usuario);
                        JOptionPane.showMessageDialog(view, "Solicitação enviada ao administrador");
                    } else {
                        JOptionPane.showMessageDialog(view, "Você já possui um perfil Vendedor");
                    }
               } catch (Exception ex) {
                   JOptionPane.showMessageDialog(view, ex);
               }
           }
        });
        
        view.getMItemAcessarPerfilVendedor().addActionListener(new ActionListener() {
           @Override
           public  void actionPerformed(ActionEvent e) {
               try {
                   if (!usuario.getPerfilVendedor().isEmpty()) {
                        JOptionPane.showMessageDialog(view, "Você ainda não possui um perfil Vendedor");
                    } else {
                        // view.getDesktopPane().add(perfilCompradorView);
                        JOptionPane.showMessageDialog(view, "Exibindo tela Vendedor"); 
                    }
               } catch (Exception ex) {
                   JOptionPane.showMessageDialog(view, ex);
               }
           }
        });
        
        view.getMItemPublicarItem().addActionListener(new ActionListener() {
           @Override
           public  void actionPerformed(ActionEvent e) {
               try {
                   if (!usuario.getPerfilVendedor().isEmpty()) {
                        JOptionPane.showMessageDialog(view, "Você ainda não possui um perfil Vendedor");
                    } else {
                        ItemRepository itemRepo = new ItemRepository();
                        TiposDefeitoRepository tiposDefeitosRepo = new TiposDefeitoRepository();
                        SistemaDefeitosService sysDefeito = new SistemaDefeitosService();
                        ItemService itemService = new ItemService(itemRepo, sysDefeito, tiposDefeitosRepo);
                        ItemPresenter itemPresenter = new ItemPresenter(itemService);
                        //view.getDesktopPane().add(perfilCompradorView);

                        JOptionPane.showMessageDialog(view, "Exibindo tela Vendedor"); 
                    }
               } catch (Exception ex) {
                   JOptionPane.showMessageDialog(view, ex);
               }
           }
        });
        
        view.setVisible(true);
    }
}
