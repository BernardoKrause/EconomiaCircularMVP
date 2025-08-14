/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package command.usuario;

import repository.UsuarioRepository;
import presenter.SignupPresenter;
import service.UsuarioService;

/**
 *
 * @author caiof
 */
public class AbrirCadastroUsuarioCommand extends UsuarioCommand {
    
    public AbrirCadastroUsuarioCommand(){
        super();
    }
    
    @Override
    public void executar() {
        UsuarioRepository usuarioRepository = UsuarioRepository.getInstancia();
        UsuarioService usuarioService = new UsuarioService(usuarioRepository);
        SignupPresenter signupPresenter = new SignupPresenter(usuarioService);
        desktop.add(signupPresenter.getView());    
    }
}