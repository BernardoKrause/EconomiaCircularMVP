/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package presenter;

import factory.repository.SeletorRepositoryFactory;
import java.sql.SQLException;
import java.util.*;
import javax.swing.JDesktopPane;
import model.Usuario;
import view.HomeView;

/**
 *
 * @author caiof
 */
public class GerenciadorTelas {
    private static GerenciadorTelas instancia;
    private HomePresenter home;
    private Map<String, IPresenter> telasAbertas;
    private Map<String, Map<String, IPresenter>> telasTipo;
    
    private GerenciadorTelas(){
        telasAbertas = new HashMap<>();
        telasTipo=new HashMap<>();
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

    public void addTelaAberta(String telaTipo,String nomeTela, IPresenter presenter){

        telasAbertas.put(nomeTela,presenter);
        telasTipo.put(telaTipo, telasAbertas);
    };

    public void removeTelaAberta(String nomeTela){
        telasAbertas.get(nomeTela).getView().dispose();
        telasAbertas.remove(nomeTela);
    }

    public void removeTelasTipo(String nomeTipo){
        for(String nomeTela : telasTipo.get(nomeTipo).keySet()){
            removeTelaAberta(nomeTela);
        }

        telasTipo.get(nomeTipo).clear();
    }

    public void sairAutenticado(Usuario usuario) throws SQLException{
        usuario.setAutenticado(false);
        SeletorRepositoryFactory.obterInstancia().criarUsuarioRepository().atualizarUsuario(usuario);

        for(String tipoTela : telasTipo.keySet()){
            removeTelasTipo(tipoTela);
        }
        telasTipo.clear();

        home.getView().dispose();
        home.setView(new HomeView());

        home.sairUsuario();
    }
}
