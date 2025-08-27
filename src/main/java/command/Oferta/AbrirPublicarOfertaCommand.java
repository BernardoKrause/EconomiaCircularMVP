/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package command.Oferta;

import command.ICommand;
import command.item.AbrirItensPublicadosCommand;
import factory.repository.SeletorRepositoryFactory;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JDesktopPane;
import model.Comprador;
import model.Item;
import model.Perfil;
import presenter.GerenciadorTelas;
import presenter.OfertaPresenter;
import repository.teste.DefeitosTipoRepositoryTeste;
import service.CalcularGPWService;
import service.ItemService;
import service.OfertaService;
import service.PerfilService;
import service.PerfilVendedorService;
import service.SistemaDefeitosService;

/**
 *
 * @author caiof
 */
public class AbrirPublicarOfertaCommand implements ICommand{
    protected Perfil perfil;
    protected PerfilService service;
    protected JDesktopPane desktop;
    protected OfertaPresenter presenter;
    protected ItemService itemService;
    protected OfertaService ofertaService;
    private Item item;
    
    public AbrirPublicarOfertaCommand(Comprador perfil, Item item) throws SQLException {
        this.perfil = perfil;
        this.item=item;
        this.service = new PerfilVendedorService(
                SeletorRepositoryFactory.obterInstancia().criarReputacaoRepository(), 
                SeletorRepositoryFactory.obterInstancia().criarCondutaRepository(), 
                SeletorRepositoryFactory.obterInstancia().criarPerfilVendedorRepository(),
                SeletorRepositoryFactory.obterInstancia().criarUsuarioRepository());
        
        
        this.desktop = GerenciadorTelas.getInstancia().getDesktop();
        
        DefeitosTipoRepositoryTeste tiposDefeitosRepo = new DefeitosTipoRepositoryTeste();
        SistemaDefeitosService sysDefeito = new SistemaDefeitosService();
        CalcularGPWService sistemaGPW = new CalcularGPWService();
        this.ofertaService = new OfertaService();
        this.itemService = new ItemService(sysDefeito, tiposDefeitosRepo, sistemaGPW, ofertaService);
        this.presenter=new OfertaPresenter(item, perfil, ofertaService);

    }

    /**
     *
     * @throws SQLException
     */
    @Override
    public void executar() throws SQLException {
        String tipoTela="Comprador";
        
        try {
            presenter.createOferta(service);
        } catch (Exception ex) {
            Logger.getLogger(AbrirItensPublicadosCommand.class.getName()).log(Level.SEVERE, null, ex);
        }
        GerenciadorTelas.getInstancia().addTelaAberta(tipoTela, "PublicarOferta", presenter);
        desktop.add(presenter.getView());    
    }
}
