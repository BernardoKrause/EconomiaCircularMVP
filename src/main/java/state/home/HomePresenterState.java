/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package state.home;

import command.usuario.SairUsuarioCommand;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JButton;
import javax.swing.JMenuItem;
import model.Usuario;
import presenter.HomePresenter;
import view.HomeView;

/**
 *
 * @author caiof
 */
public abstract class HomePresenterState {
    protected HomeView view;
    protected Usuario usuario;
    
    public HomePresenterState(HomePresenter presenter){
        view = presenter.getView();
    }
    
    public HomePresenterState(HomePresenter presenter, Usuario usuario){
        this.usuario=usuario;
        view = presenter.getView();
    }
    
    public void entrarUsuario(){
        throw new RuntimeException("Não é possivel salvar estando nesse estado!");
    }
    
    public void cadastrarUsuario(){
        throw new RuntimeException("Não é possivel salvar estando nesse estado!");
    }
    
    public void sairUsuario(){
        try {
            new SairUsuarioCommand(usuario).executar();
        } catch (SQLException ex) {
            Logger.getLogger(AutenticadoState.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void criarPerfilVendedor() throws SQLException {
        throw new RuntimeException("Não é possivel salvar estando nesse estado!");
    }
    
    public void acessarPerfilVendedor() throws SQLException {
        throw new RuntimeException("Não é possivel salvar estando nesse estado!");
    }
    
    public void verPerfilVendedor() throws SQLException{
        throw new RuntimeException("Não é possivel salvar estando nesse estado!");
    }
    
    public void criarPerfilComprador() throws SQLException {
        throw new RuntimeException("Não é possivel salvar estando nesse estado!");
    }
    
    public void acessarPerfilComprador() throws SQLException {
        throw new RuntimeException("Não é possivel salvar estando nesse estado!");
    }
    
    public void publicarItem() throws SQLException {
        throw new RuntimeException("Não é possivel salvar estando nesse estado!");
    }
    
    public void verItens() throws SQLException {
        throw new RuntimeException("Não é possivel salvar estando nesse estado!");
    }
    
    protected void resetMenuItemActions(JMenuItem mi) {
        for(ActionListener al : mi.getActionListeners()) {
            mi.removeActionListener(al);
        }
    }
    
    public abstract void setVisibles();
}
