package command.perfil;


import java.sql.SQLException;
import model.Usuario;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author caiof
 */
public class CriarPerfilVendedorCommand extends PerfilCommand{
    public CriarPerfilVendedorCommand(Usuario usuario)  throws SQLException {
        super(usuario);
        
        System.out.print(usuario.getPerfis().isEmpty());
    }

    @Override
    public void executar() throws SQLException {
        service.criarPerfilVendedor(usuario);    
    }
}
