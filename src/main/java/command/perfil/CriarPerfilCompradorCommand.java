/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package command.perfil;

import java.sql.SQLException;
import model.Usuario;

/**
 *
 * @author caiof
 */
public class CriarPerfilCompradorCommand extends PerfilCommand{
    public CriarPerfilCompradorCommand(Usuario usuario) throws SQLException {
        super(usuario);
    }
    
    @Override
    public void executar() {
        service.criarPerfilComprador(usuario);
    }
    
}

