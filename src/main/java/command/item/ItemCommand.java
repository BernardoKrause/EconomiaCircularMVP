/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package command.item;

import command.ICommand;
import factory.repository.SeletorRepositoryFactory;
import java.sql.SQLException;
import javax.swing.JDesktopPane;
import model.Perfil;
import presenter.GerenciadorTelas;
import service.PerfilService;
import service.PerfilVendedorService;

/**
 *
 * @author caiof
 */
public abstract class ItemCommand implements ICommand{
    protected Perfil perfil;
    protected PerfilService service;
    protected JDesktopPane desktop;
    
    public ItemCommand(Perfil perfil) throws SQLException {
        this.perfil = perfil;
        this.service = new PerfilVendedorService(SeletorRepositoryFactory.obterInstancia().criarReputacaoRepository(), SeletorRepositoryFactory.obterInstancia().criarPerfilVendedorRepository());
        this.desktop = GerenciadorTelas.getInstancia().getDesktop();
    }

    /**
     *
     * @throws SQLException
     */
    @Override
    public abstract void executar() throws SQLException;
}
