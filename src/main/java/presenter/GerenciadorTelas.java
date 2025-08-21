/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package presenter;

import java.util.HashMap;
import java.util.Map;
import javax.swing.JDesktopPane;

/**
 *
 * @author caiof
 */
public class GerenciadorTelas {
    private static GerenciadorTelas instancia;
    private HomePresenter home;
    private Map<String, IPresenter> telasAbertas;
    
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
    }
    
    public JDesktopPane getDesktop(){
        return home.getDesktop();
    }
    
    public HomePresenter getPresenter(){
        return home;
    }
}
