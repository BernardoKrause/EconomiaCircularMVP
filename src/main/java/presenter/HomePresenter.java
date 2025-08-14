/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package presenter;

import javax.swing.JDesktopPane;
import model.Usuario;
import repository.UsuarioRepository;
import service.PerfilService;
import state.home.AutenticadoState;
import state.home.HomePresenterState;
import state.home.NaoAutenticadoState;
import view.HomeView;

/**
 *
 * @author berna
 */
public class HomePresenter {
    
    private HomeView view;
    private HomePresenterState estado;
    private PerfilService perfilService;
    private Usuario usuario;
    
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
    
    public void entrarUsuario(Usuario usuario){
        this.usuario = usuario;
        estado = new AutenticadoState(this, usuario); 
    }
    
    public void sairUsuario(){
        estado = new NaoAutenticadoState(this);
        usuario=null;
    }
    
}
