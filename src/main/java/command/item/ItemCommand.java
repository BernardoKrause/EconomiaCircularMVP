/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package command.item;

import command.ICommand;
import javax.swing.JDesktopPane;
import model.Perfil;
import repository.PerfilRepository;
import service.PerfilService;

/**
 *
 * @author caiof
 */
public abstract class ItemCommand implements ICommand{
    protected Perfil perfil;
    protected PerfilService service;
    protected JDesktopPane desktop;
    
    public ItemCommand(Perfil perfil){
        this.perfil=perfil;
        this.service = new PerfilService(new PerfilRepository());
    }

    @Override
    public abstract void executar();
}
