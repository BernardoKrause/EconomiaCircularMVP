/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package state.home;

import command.item.AbrirCadastroItemCommand;
import command.item.AbrirItensPublicadosCommand;
import command.perfil.AbrirVerPerfilVendedorCommand;
import command.perfil.AcessarPerfilCompradorCommand;
import command.perfil.CriarPerfilCompradorCommand;
import command.perfil.CriarPerfilVendedorCommand;
import command.usuario.SairUsuarioCommand;
import factory.repository.SeletorRepositoryFactory;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.Optional;
import javax.swing.JOptionPane;
import model.Comprador;
import model.Item;
import model.Perfil;
import model.Usuario;
import model.Vendedor;
import presenter.HomePresenter;
import repository.IPerfilRepository;

/**
 *
 * @author caiof
 */
public class VendedorState extends HomePresenterState{
    private Vendedor vendedor;
    
    public VendedorState(HomePresenter presenter, Usuario usuarioAutenticado, Vendedor vendedor) throws SQLException {
        super(presenter, usuarioAutenticado);
        if(vendedor == null){
            this.vendedor = vendedor;
        }else{
            this.vendedor = (Vendedor)SeletorRepositoryFactory.obterInstancia().criarPerfilVendedorRepository().buscarPorIdUsuario(usuarioAutenticado.getId()).get();
        }

        setVisibles();
        
        verPerfilVendedor();
        
        resetMenuItemActions(view.getMItemVerPerfilVendedor());
        resetMenuItemActions(view.getMItemCriarPerfilComprador());
        resetMenuItemActions(view.getMItemAcessarPerfilComprador());
        resetMenuItemActions(view.getMIVerItem());
        resetMenuItemActions(view.getMItemSairUsuario());
        resetMenuItemActions(view.getMIItemPublicarItem());
        
        view.getMItemVerPerfilVendedor().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    verPerfilVendedor();
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(view, ex);
                }
            }
        });
        
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
        
        view.getMIItemPublicarItem().addActionListener(new ActionListener() {
           @Override
           public  void actionPerformed(ActionEvent e) {
               try {
                    publicarItem();
               } catch (Exception ex) {
                   JOptionPane.showMessageDialog(view, ex);
               }
           }
        });
        
        view.getMIVerItem().addActionListener(new ActionListener() {
           @Override
           public  void actionPerformed(ActionEvent e) {
               try {
                   verItens();
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
        view.getMItemVerPerfilComprador().setVisible(false);
        view.getMIItemPublicarItem().setVisible(true);
        view.getMItemCriarPerfilComprador().setVisible(true);
        view.getMItemAcessarPerfilComprador().setVisible(true);
    }
    
    @Override
    public void criarPerfilComprador() throws SQLException {
        IPerfilRepository perfilRepository = SeletorRepositoryFactory.obterInstancia().criarPerfilCompradorRepository();
        new CriarPerfilCompradorCommand(usuario,perfilRepository).executar();
        view.getMItemAcessarPerfilComprador().setVisible(true); 
        view.getMItemCriarPerfilComprador().setVisible(false); 
    }
    
    @Override
    public void acessarPerfilComprador() throws SQLException{
        IPerfilRepository perfilRepository = SeletorRepositoryFactory.obterInstancia().criarPerfilCompradorRepository();
        Comprador comprador= (Comprador)SeletorRepositoryFactory.obterInstancia().criarPerfilCompradorRepository().buscarPorIdUsuario(vendedor.getUsuario().getId()).get();        

        new AcessarPerfilCompradorCommand(usuario,perfilRepository, comprador).executar();
    }
    
    public void verPerfilVendedor() throws SQLException{
        IPerfilRepository perfilRepository = SeletorRepositoryFactory.obterInstancia().criarPerfilVendedorRepository();
        new AbrirVerPerfilVendedorCommand(vendedor,perfilRepository).executar();
    }
    
    @Override
    public void publicarItem() throws SQLException{
        Perfil perfil = usuario.getPerfilVendedor().get();
        Optional<Item> item = Optional.empty();
        new AbrirCadastroItemCommand(vendedor, item).executar();
    }
    
    @Override
    public void verItens() throws SQLException{
        Perfil perfil = usuario.getPerfilVendedor().get();
        new AbrirItensPublicadosCommand(vendedor).executar();
    }
}
