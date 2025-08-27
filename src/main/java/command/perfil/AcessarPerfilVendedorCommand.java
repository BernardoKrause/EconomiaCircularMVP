/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package command.perfil;

import java.sql.SQLException;
import java.util.Optional;
import model.Usuario;
import model.Vendedor;
import presenter.GerenciadorTelas;
import presenter.HomePresenter;
import repository.IPerfilRepository;

/**
 *
 * @author caiof
 */
public class AcessarPerfilVendedorCommand extends PerfilCommand{
    private HomePresenter presenter;
    private Vendedor vendedor;
    
    public AcessarPerfilVendedorCommand(Usuario usuario, IPerfilRepository perfilRepository, Vendedor vendedor) throws SQLException {
        super(usuario,perfilRepository, Optional.of("Vendedor"));
        this.vendedor=vendedor;
        this.presenter = GerenciadorTelas.getInstancia().getPresenter();
    }
    
    @Override
    public void executar() throws SQLException {
        presenter.acessarVendedor(usuario, vendedor);
    }
    
}
