/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package command.perfil;

import java.sql.SQLException;
import java.util.Optional;
import model.Usuario;
import presenter.GerenciadorTelas;
import presenter.HomePresenter;
import repository.IPerfilRepository;

/**
 *
 * @author caiof
 */
public class AcessarPerfilCompradorCommand extends PerfilCommand{
    private HomePresenter presenter;

    public AcessarPerfilCompradorCommand(Usuario usuario, IPerfilRepository perfilRepository) throws SQLException {
        super(usuario,perfilRepository,Optional.of("Comprador"));
        this.presenter = GerenciadorTelas.getInstancia().getPresenter();
    }
    
    @Override
    public void executar() throws SQLException {
        presenter.acessarComprador(usuario);
    }
    
}
