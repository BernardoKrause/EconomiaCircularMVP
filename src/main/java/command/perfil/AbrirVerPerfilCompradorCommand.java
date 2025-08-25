/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package command.perfil;

import java.sql.SQLException;
import java.util.Optional;
import javax.swing.JDesktopPane;
import model.Comprador;
import model.Perfil;
import presenter.CompradorPresenter;
import presenter.GerenciadorTelas;
import presenter.PerfilPresenter;
import repository.IPerfilRepository;

/**
 *
 * @author caiof
 */
public class AbrirVerPerfilCompradorCommand extends PerfilCommand{
    private JDesktopPane desktop;
    private Perfil perfil;
    
    public AbrirVerPerfilCompradorCommand(Comprador comprador, IPerfilRepository perfilRepository) throws SQLException{
        super(comprador.getUsuario(), perfilRepository,Optional.of("Comprador"));
        this.desktop = GerenciadorTelas.getInstancia().getDesktop();
        this.perfil=(Comprador)usuario.getPerfilComprador().get();
    }
    
    @Override
    public void executar() throws SQLException {
        PerfilPresenter compradorPresenter = new CompradorPresenter(perfil,service);
        compradorPresenter.verPerfil();
        GerenciadorTelas.getInstancia().addTelaAberta("Comprador", "VerPerfil", compradorPresenter);
        desktop.add(compradorPresenter.getView());
    }
    
}
