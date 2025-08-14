/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package command.usuario;

import command.ICommand;
import presenter.LoginPresenter;
import repository.UsuarioRepository;
import service.AutenticacaoService;

import javax.swing.*;
import presenter.GerenciadorTelas;

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
        UsuarioRepository usuarioRepository = new UsuarioRepository();
        AutenticacaoService autenticacaoService = new AutenticacaoService(usuarioRepository);
        LoginPresenter loginPresenter = new LoginPresenter(autenticacaoService);
        desktop.add(loginPresenter.getView());
        
    }
}