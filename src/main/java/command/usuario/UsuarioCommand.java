/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package command.usuario;

import command.ICommand;
import javax.swing.JDesktopPane;
import model.Usuario;
import presenter.GerenciadorTelas;

/**
 *
 * @author caiof
 */
public abstract class UsuarioCommand implements ICommand{
    protected JDesktopPane desktop;
    protected Usuario usuario;

    public UsuarioCommand() {
        this.desktop = GerenciadorTelas.getInstancia().getDesktop();
    }
    
    public UsuarioCommand(Usuario usuario) {
        this.desktop = GerenciadorTelas.getInstancia().getDesktop();
        this.usuario=usuario;
    }
    
}
