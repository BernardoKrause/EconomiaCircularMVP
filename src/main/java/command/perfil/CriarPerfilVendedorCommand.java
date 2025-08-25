package command.perfil;


import java.sql.SQLException;
import model.Usuario;
import repository.IPerfilRepository;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author caiof
 */
public class CriarPerfilVendedorCommand extends PerfilCommand{
          
    public CriarPerfilVendedorCommand(Usuario usuario, IPerfilRepository perfilRepository)  throws SQLException {
        super(usuario,perfilRepository);        
    }

    @Override
    public void executar() throws SQLException {
        service.criar(usuario);    
    }
}
