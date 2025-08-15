/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package state.home;

import command.item.AbrirCadastroItemCommand;
import command.perfil.CriarPerfilCompradorCommand;
import command.usuario.SairUsuarioCommand;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JOptionPane;
import model.Perfil;
import model.Usuario;
import model.Vendedor;
import presenter.HomePresenter;

/**
 *
 * @author caiof
 */
public class VendedorState extends HomePresenterState{
    private Vendedor vendedor;
    private Usuario usuario;
    
    public VendedorState(HomePresenter presenter, Vendedor vendedor) {
        super(presenter);
    
        this.vendedor=vendedor;
        this.usuario=vendedor.getUsuario();
        
        setVisibles();
        
        view.getMItemCriarPerfilComprador().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    criarPerfilComprador();
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(view, ex);
                }
            }
        });
        
        view.getMItemAcessarPerfilComprador().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    acessarPerfilComprador();
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(view, ex);
                }
            }
        });
        
        view.getMItemPublicarItem().addActionListener(new ActionListener() {
           @Override
           public  void actionPerformed(ActionEvent e) {
               try {
                    Perfil perfil = usuario.getPerfilVendedor().get();
                    new AbrirCadastroItemCommand(perfil).executar();
               } catch (Exception ex) {
                   JOptionPane.showMessageDialog(view, ex);
               }
           }
        });
        
    }
    
    @Override
    public void setVisibles(){
        
        view.getMenuUsuario().setText(usuario.getNome());
        view.getMenuVendedor().setVisible(true);
        view.getMenuComprador().setVisible(true);
        view.getMenuItem().setVisible(true);
        view.getMItemEntrarUsuario().setVisible(false);
        view.getMItemCadastrarUsuario().setVisible(false);
        view.getMItemSairUsuario().setVisible(true);
        view.getMItemAcessarPerfilVendedor().setVisible(false);
        view.getMItemCriarPerfilVendedor().setVisible(false);
        view.getMItemVerPerfilVendedor().setVisible(true);
        if (usuario.getPerfilComprador().isEmpty()) {
            view.getMItemAcessarPerfilComprador().setVisible(false);
            view.getMItemCriarPerfilComprador().setVisible(true);
        }
        else{
            view.getMItemAcessarPerfilComprador().setVisible(true);
            view.getMItemCriarPerfilComprador().setVisible(false);
        }
    }
    
    @Override
    public void sairUsuario(){
        new SairUsuarioCommand().executar();
    }
    
    @Override
    public void criarPerfilComprador(){
        new CriarPerfilCompradorCommand(usuario).executar();
        view.getMItemAcessarPerfilComprador().setVisible(true); 
        view.getMItemCriarPerfilVendedor().setVisible(false); 
        JOptionPane.showMessageDialog(view, "Solicitação enviada ao administrador");
    }
    
    @Override
    public void acessarPerfilComprador(){
        throw new RuntimeException("Não é possivel salvar estando nesse estado!");
    }
    
}
