/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package command.usuario;

import command.ICommand;
import javax.swing.JDesktopPane;
import presenter.GerenciadorTelas;

/**
 *
 * @author caiof
 */
public abstract class UsuarioCommand implements ICommand{
    protected JDesktopPane desktop;

    public UsuarioCommand() {
        this.desktop = GerenciadorTelas.getInstancia().getDesktop();
    }
    
}
