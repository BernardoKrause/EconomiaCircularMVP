/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package command.usuario;

import java.sql.SQLException;
import repository.UsuarioRepository;
import presenter.SignupPresenter;
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
        try {
            UsuarioRepository usuarioRepository = new UsuarioRepository();
            UsuarioService usuarioService = new UsuarioService(usuarioRepository);
            SignupPresenter signupPresenter = new SignupPresenter(usuarioService);    
            desktop.add(signupPresenter.getView());
        } catch (SQLException ex) {
            System.getLogger(AbrirSignupUsuarioCommand.class.getName()).log(System.Logger.Level.ERROR, (String) null, ex);
        }
    }
}