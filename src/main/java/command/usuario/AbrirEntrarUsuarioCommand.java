/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package command.usuario;

import presenter.LoginPresenter;
import repository.UsuarioRepository;

import javax.swing.*;
import presenter.GerenciadorTelas;
import service.UsuarioService;
import command.ICommand;

/**
 *
 * @author caiof
 */
public class AbrirEntrarUsuarioCommand implements ICommand {
    private JDesktopPane desktop;

    public AbrirEntrarUsuarioCommand() {
        this.desktop = GerenciadorTelas.getInstancia().getDesktop();
    }

    @Override
    public void executar() {
        UsuarioRepository usuarioRepository = UsuarioRepository.getInstancia();
        UsuarioService usuarioService = new UsuarioService(usuarioRepository);
        LoginPresenter loginPresenter = new LoginPresenter(usuarioService);
        desktop.add(loginPresenter.getView());
        
    }
}