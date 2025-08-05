/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package main;

import presenter.LoginPresenter;
import repository.UsuarioRepository;
import service.AutenticacaoService;

/**
 *
 * @author caiof
 */
public class EconomiaCircularMVP {

    public static void main(String[] args) {
        
        UsuarioRepository usuarioRepository = new UsuarioRepository();
        AutenticacaoService autenticacaoService = new AutenticacaoService(usuarioRepository);
        LoginPresenter loginPresenter = new LoginPresenter(autenticacaoService);
    }
}
