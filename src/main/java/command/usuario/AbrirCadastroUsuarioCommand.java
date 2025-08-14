/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package command.usuario;

import command.ICommand;
import repository.UsuarioRepository;

import javax.swing.*;
import presenter.GerenciadorTelas;

/**
 *
 * @author caiof
 */
public class AbrirCadastroUsuarioCommand implements ICommand {
    private JDesktopPane desktop;

    public AbrirCadastroUsuarioCommand() {
        this.desktop = GerenciadorTelas.getInstancia().getDesktop();
    }

    @Override
    public void executar() {
        UsuarioRepository usuarioRepository = new UsuarioRepository();
        //SignupPresenter signupPresenter = new SignupPresenter(usuarioRepository);

       // desktop.add(signupPresenter.getView());
    }
}