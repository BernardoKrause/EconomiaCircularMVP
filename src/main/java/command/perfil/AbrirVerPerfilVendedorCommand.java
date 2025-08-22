/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package command.perfil;

import command.ICommand;
import java.sql.SQLException;
import javax.swing.JDesktopPane;
import model.Perfil;
import model.Usuario;
import model.Vendedor;
import presenter.GerenciadorTelas;
import presenter.PerfilPresenter;
import presenter.VendedorPresenter;
import repository.IPerfilRepository;

/**
 *
 * @author caiof
 */
public class AbrirVerPerfilVendedorCommand extends PerfilCommand{
    private JDesktopPane desktop;
    private Perfil perfil;
    
    public AbrirVerPerfilVendedorCommand(Vendedor vendedor, IPerfilRepository perfilRepository) throws SQLException{
        super(vendedor.getUsuario(), perfilRepository);
        this.desktop = GerenciadorTelas.getInstancia().getDesktop();
        this.perfil=(Vendedor)usuario.getPerfilVendedor().get();
    }
    
    @Override
    public void executar() throws SQLException {
        PerfilPresenter vendedorPresenter = new VendedorPresenter(perfil,service);
        vendedorPresenter.verPerfil();
        GerenciadorTelas.getInstancia().addTelaAberta("Vendedor", "VerPerfil", vendedorPresenter);
        desktop.add(vendedorPresenter.getView());
    }
    
}
