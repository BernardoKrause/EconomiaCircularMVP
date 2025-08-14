/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package command.item;

import command.ICommand;
import command.usuario.UsuarioCommand;
import javax.swing.JDesktopPane;
import model.Perfil;
import presenter.ItemPresenter;
import repository.ItemRepository;
import repository.PerfilRepository;
import repository.TiposDefeitoRepository;
import service.ItemService;
import service.PerfilService;
import service.SistemaDefeitosService;

/**
 *
 * @author caiof
 */
public class AbrirCadastroItemCommand extends ItemCommand{
    public AbrirCadastroItemCommand(Perfil perfil){
        super(perfil);
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
