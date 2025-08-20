/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package state.home;

import java.sql.SQLException;
import presenter.HomePresenter;
import view.HomeView;

/**
 *
 * @author caiof
 */
public abstract class HomePresenterState {
    protected HomePresenter presenter;
    protected HomeView view;
    
    public HomePresenterState(HomePresenter presenter){
        this.presenter=presenter;
        view = presenter.getView();
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
    
    public abstract void setVisibles();
}
