/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package state.home;

import model.Usuario;
import presenter.HomePresenter;
import view.HomeView;

/**
 *
 * @author caiof
 */
public abstract class HomePresenterState {
    protected HomePresenter presenter;
    protected HomeView view;
    protected Usuario usuario;
    
    public HomePresenterState(HomePresenter presenter){
        this.presenter=presenter;
        view = presenter.getView();
        usuario=null;
    }
    
    public void entrarUsuario(){
        throw new RuntimeException("Não é possivel salvar estando nesse estado!");
    }
    
    public void cadastrarUsuario(){
        throw new RuntimeException("Não é possivel salvar estando nesse estado!");
    }
    
    public void sairUsuario(){
        throw new RuntimeException("Não é possivel salvar estando nesse estado!");
    }
    
    public void criarPerfilVendedor(){
        throw new RuntimeException("Não é possivel salvar estando nesse estado!");
    }
    
    public void acessarPerfilVendedor(){
        throw new RuntimeException("Não é possivel salvar estando nesse estado!");
    }
    
    public void criarPerfilComprador(){
        throw new RuntimeException("Não é possivel salvar estando nesse estado!");
    }
    
    public void acessarPerfilComprador(){
        throw new RuntimeException("Não é possivel salvar estando nesse estado!");
    }
    
    public void publicarItem(){
        throw new RuntimeException("Não é possivel salvar estando nesse estado!");
    }
    
    public Usuario getUsuario(){
        return usuario;
    }
    
    public void setUsuario(Usuario usuario){
        this.usuario=usuario;
    }
}
