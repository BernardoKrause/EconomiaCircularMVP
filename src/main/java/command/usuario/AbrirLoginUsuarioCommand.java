/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package command.usuario;

import presenter.LoginPresenter;
import repository.sqlite.UsuarioRepositorySqLite;

import javax.swing.*;
import presenter.GerenciadorTelas;
import service.UsuarioService;
import command.ICommand;
import java.sql.SQLException;

/**
 *
 * @author caiof
 */
public class AbrirLoginUsuarioCommand implements ICommand {
    private JDesktopPane desktop;

    public AbrirLoginUsuarioCommand() {
        this.desktop = GerenciadorTelas.getInstancia().getDesktop();
    }

    @Override
    public void executar() {
        try {
            UsuarioRepositorySqLite usuarioRepository = new UsuarioRepositorySqLite();
            UsuarioService usuarioService = new UsuarioService(usuarioRepository);
            LoginPresenter loginPresenter = new LoginPresenter(usuarioService);
            desktop.add(loginPresenter.getView());
        } catch (SQLException ex) {
            System.getLogger(AbrirLoginUsuarioCommand.class.getName()).log(System.Logger.Level.ERROR, (String) null, ex);
        }
        
    }
}