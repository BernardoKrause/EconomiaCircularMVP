/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package command.usuario;

import java.sql.SQLException;
import model.Usuario;
import presenter.GerenciadorTelas;

/**
 *
 * @author caiof
 */
public class SairUsuarioCommand extends UsuarioCommand{
    
    public SairUsuarioCommand(Usuario usuario) {
        super(usuario);
        
    }
    
    @Override
    public void executar() throws SQLException {
        GerenciadorTelas.getInstancia().sairAutenticado(usuario);
    }
    
}
