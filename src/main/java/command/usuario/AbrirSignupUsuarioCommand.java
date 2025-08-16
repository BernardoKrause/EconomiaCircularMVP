/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package command.usuario;

import factory.repository.SeletorRepositoryFactory;
import java.sql.SQLException;
import repository.sqlite.UsuarioRepositorySqLite;
import presenter.SignupPresenter;
import repository.IUsuarioRepository;
import service.UsuarioService;

/**
 *
 * @author caiof
 */
public class AbrirSignupUsuarioCommand extends UsuarioCommand {
    
    public AbrirSignupUsuarioCommand(){
        super();
    }
    
    @Override
    public void executar() {
        IUsuarioRepository usuarioRepository = SeletorRepositoryFactory.obterInstancia().criarUsuarioRepository();
        UsuarioService usuarioService = new UsuarioService(usuarioRepository);
        SignupPresenter signupPresenter = new SignupPresenter(usuarioService);    
        desktop.add(signupPresenter.getView());
    }
}