/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package command.usuario;

import presenter.GerenciadorTelas;

/**
 *
 * @author caiof
 */
public class SairUsuarioCommand extends UsuarioCommand{
    public SairUsuarioCommand() {
        super();
    }
    
    @Override
    public void executar() {
        GerenciadorTelas.getInstancia().getPresenter().sairUsuario();
    }
    
}
