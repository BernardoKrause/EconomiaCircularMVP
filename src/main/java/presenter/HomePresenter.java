/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package presenter;

import java.sql.SQLException;
import javax.swing.JDesktopPane;
import model.Comprador;
import model.Usuario;
import model.Vendedor;
import service.PerfilService;
import state.home.AutenticadoState;
import state.home.CompradorState;
import state.home.HomePresenterState;
import state.home.NaoAutenticadoState;
import state.home.VendedorState;
import view.HomeView;

/**
 *
 * @author berna
 */
public class HomePresenter {
    
    private HomeView view;
    private HomePresenterState estado;
    
    public HomePresenter() {
        view = new HomeView();
        estado = new NaoAutenticadoState(this);
    }
    
    public HomeView getView(){
        return view;
    }
    
    public JDesktopPane getDesktop(){
        return view.getDesktopPane();
    }
    
    public void entrarUsuario(Usuario usuarioAutenticado) throws SQLException{
        GerenciadorTelas.getInstancia().removeTelasTipo("Usuario");
        estado = new AutenticadoState(this, usuarioAutenticado); 
    }
    
    public void sairUsuario(){
        estado = new NaoAutenticadoState(this);
    }
    
    public void acessarVendedor(Usuario usuarioAutenticado, Vendedor vendedor) throws SQLException{
        GerenciadorTelas.getInstancia().removeTelasTipo("Comprador");
        GerenciadorTelas.getInstancia().removeTelasTipo("Item");
        estado = new VendedorState(this,usuarioAutenticado, vendedor);
    }
    
    public void acessarComprador(Usuario usuarioAutenticado, Comprador comprador) throws SQLException{
        GerenciadorTelas.getInstancia().removeTelasTipo("Vendedor");
        GerenciadorTelas.getInstancia().removeTelasTipo("Item");
        estado = new CompradorState(this,usuarioAutenticado, comprador);
    }

    public void setView(HomeView view){
        this.view = view;
    }
}
