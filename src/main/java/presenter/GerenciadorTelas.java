/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package presenter;

import java.util.HashMap;
import java.util.Map;
import javax.swing.JDesktopPane;
import model.Usuario;

/**
 *
 * @author caiof
 */
public class GerenciadorTelas {
    private static GerenciadorTelas instancia;
    private HomePresenter home;
    private Map<String, IPresenter> telasAbertas;
    private Usuario usuarioAutenticado;
    
    private GerenciadorTelas(){
        telasAbertas = new HashMap<>();
    }
    
    public static GerenciadorTelas getInstancia(){
        if (instancia==null){
            instancia=new GerenciadorTelas();
        }
        return instancia;
    }
    
    public void inicializar(HomePresenter home){
        this.home=home;
        usuarioAutenticado=null;
    }
    
    public Usuario getUsuarioAutenticado(){
        return usuarioAutenticado;
    }
    
    public void logar(Usuario usuario){
        if(usuario==null){
            throw new IllegalArgumentException("Usuario não foi encontrado!");
        }
        usuarioAutenticado = usuario;
    }
    
    public JDesktopPane getDesktop(){
        return home.getDesktop();
    }

    public void deslogar() {
        this.usuarioAutenticado=null;
    }
    
}
