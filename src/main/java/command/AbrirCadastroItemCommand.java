/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package command;

import command.usuario.UsuarioCommand;
import javax.swing.JDesktopPane;
import presenter.GerenciadorTelas;
import presenter.ItemPresenter;
import repository.ItemRepository;
import repository.TiposDefeitoRepository;
import service.ItemService;
import service.SistemaDefeitosService;

/**
 *
 * @author caiof
 */
public class AbrirCadastroItemCommand extends UsuarioCommand{
    
    public AbrirCadastroItemCommand(){
        super();
    }

    @Override
    public void executar() {
        ItemRepository itemRepo = new ItemRepository();
        TiposDefeitoRepository tiposDefeitosRepo = new TiposDefeitoRepository();
        SistemaDefeitosService sysDefeito = new SistemaDefeitosService();
        ItemService itemService = new ItemService(itemRepo, sysDefeito, tiposDefeitosRepo);
        ItemPresenter itemPresenter = new ItemPresenter(itemService);
        desktop.add(itemPresenter.getView());    }
    
}
