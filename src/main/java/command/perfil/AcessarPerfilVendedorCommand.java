/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package command.perfil;

import java.sql.SQLException;
import model.Usuario;
import model.Vendedor;
import presenter.GerenciadorTelas;
import presenter.HomePresenter;

/**
 *
 * @author caiof
 */
public class AcessarPerfilVendedorCommand extends PerfilCommand{
    private HomePresenter presenter;

    public AcessarPerfilVendedorCommand(Usuario usuario) throws SQLException {
        super(usuario);
        this.presenter = GerenciadorTelas.getInstancia().getPresenter();
    }
    
    @Override
    public void executar() {
        presenter.acessarVendedor((Vendedor) usuario.getPerfilVendedor().get());
    }
    
}
