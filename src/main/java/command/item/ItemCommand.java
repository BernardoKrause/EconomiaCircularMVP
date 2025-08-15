/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package command.item;

import command.ICommand;
import java.sql.SQLException;
import javax.swing.JDesktopPane;
import model.Perfil;
import repository.PerfilVendedorRepository;
import service.PerfilService;

/**
 *
 * @author caiof
 */
public abstract class ItemCommand implements ICommand{
    protected Perfil perfil;
    protected PerfilService service;
    protected JDesktopPane desktop;
    
    public ItemCommand(Perfil perfil) throws SQLException {
        this.perfil=perfil;
        this.service = new PerfilService(new PerfilVendedorRepository());
    }

    @Override
    public abstract void executar();
}
