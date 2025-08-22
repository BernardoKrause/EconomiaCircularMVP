/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package command.perfil;

import java.sql.SQLException;
import model.Usuario;
import presenter.GerenciadorTelas;
import presenter.HomePresenter;
import repository.IPerfilRepository;

/**
 *
 * @author caiof
 */
public class AcessarPerfilVendedorCommand extends PerfilCommand{
    private HomePresenter presenter;

    public AcessarPerfilVendedorCommand(Usuario usuario, IPerfilRepository perfilRepository) throws SQLException {
        super(usuario,perfilRepository);
        this.presenter = GerenciadorTelas.getInstancia().getPresenter();
    }
    
    @Override
    public void executar() throws SQLException {
        presenter.acessarVendedor(usuario);
    }
    
}
