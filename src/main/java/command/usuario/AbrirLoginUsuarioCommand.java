/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package command.usuario;

import presenter.LoginPresenter;

import javax.swing.*;
import presenter.GerenciadorTelas;
import service.UsuarioService;
import command.ICommand;
import factory.repository.SeletorRepositoryFactory;
import java.sql.SQLException;
import repository.IUsuarioRepository;

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
        IUsuarioRepository usuarioRepository = SeletorRepositoryFactory.obterInstancia().criarUsuarioRepository();
        UsuarioService usuarioService = new UsuarioService(usuarioRepository);
        LoginPresenter loginPresenter = new LoginPresenter(usuarioService);
        desktop.add(loginPresenter.getView());
    }
}