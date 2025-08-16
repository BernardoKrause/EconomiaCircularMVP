/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package command.item;

import java.sql.SQLException;
import model.Perfil;
import presenter.ItemPresenter;
import repository.sqlite.PerfilVendedorRepositorySqLite;
import repository.teste.DefeitosTipoRepositoryTeste;
import service.ItemService;
import service.SistemaDefeitosService;

/**
 *
 * @author caiof
 */
public class AbrirCadastroItemCommand extends ItemCommand{
    public AbrirCadastroItemCommand(Perfil perfil)throws SQLException {
        super(perfil);
    }

    /**
     *
     * @throws SQLException
     */
    @Override
    public void executar() throws SQLException {
        DefeitosTipoRepositoryTeste tiposDefeitosRepo = new DefeitosTipoRepositoryTeste();
        SistemaDefeitosService sysDefeito = new SistemaDefeitosService();
        ItemService itemService = new ItemService(sysDefeito, tiposDefeitosRepo);
        ItemPresenter itemPresenter = new ItemPresenter(itemService);
        desktop.add(itemPresenter.getView());    
    }
    
}
