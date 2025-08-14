/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package command.item;

import java.sql.SQLException;
import model.Perfil;
import presenter.ItemPresenter;
import repository.TiposDefeitoRepository;
import service.ItemService;
import service.SistemaDefeitosService;

/**
 *
 * @author caiof
 */
public class AbrirCadastroItemCommand extends ItemCommand{
    public AbrirCadastroItemCommand(Perfil perfil){
        super(perfil);
    }

    /**
     *
     * @throws SQLException
     */
    @Override
    public void executar() throws SQLException {
        TiposDefeitoRepository tiposDefeitosRepo = new TiposDefeitoRepository();
        SistemaDefeitosService sysDefeito = new SistemaDefeitosService();
        ItemService itemService = new ItemService(sysDefeito, tiposDefeitosRepo);
        ItemPresenter itemPresenter = new ItemPresenter(itemService);
        desktop.add(itemPresenter.getView());    
    }
    
}
