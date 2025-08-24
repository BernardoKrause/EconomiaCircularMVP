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
import presenter.GerenciadorTelas;;
import presenter.ItemPresenter;
import repository.teste.DefeitosTipoRepositoryTeste;
import service.CalcularGPWService;
import service.ItemService;
import service.PerfilService;
import service.PerfilVendedorService;
import service.SistemaDefeitosService;

/**
 *
 * @author caiof
 */
public abstract class ItemCommand implements ICommand{
    protected Perfil perfil;
    protected PerfilService service;
    protected JDesktopPane desktop;
    protected ItemPresenter presenter;
    protected ItemService itemService;
    
    public ItemCommand(Perfil perfil) throws SQLException {
        this.perfil = perfil;
        this.service = new PerfilVendedorService(
                SeletorRepositoryFactory.obterInstancia().criarReputacaoRepository(), 
                SeletorRepositoryFactory.obterInstancia().criarCondutaRepository(), 
                SeletorRepositoryFactory.obterInstancia().criarPerfilVendedorRepository(),
                SeletorRepositoryFactory.obterInstancia().criarUsuarioRepository());
        
        
        this.desktop = GerenciadorTelas.getInstancia().getDesktop();
        
        DefeitosTipoRepositoryTeste tiposDefeitosRepo = new DefeitosTipoRepositoryTeste();
        SistemaDefeitosService sysDefeito = new SistemaDefeitosService();
        CalcularGPWService sistemaGPW = new CalcularGPWService();
        this.itemService = new ItemService(sysDefeito, tiposDefeitosRepo, sistemaGPW);
        this.presenter=new ItemPresenter(itemService, perfil);

    }

    /**
     *
     * @throws SQLException
     */
    @Override
    public abstract void executar() throws SQLException;
}
