/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package command.perfil;

import java.sql.SQLException;
import java.util.Optional;
import model.Usuario;
import repository.IPerfilRepository;

/**
 *
 * @author caiof
 */
public class CriarPerfilCompradorCommand extends PerfilCommand{
    public CriarPerfilCompradorCommand(Usuario usuario,IPerfilRepository perfilRepository) throws SQLException {
        super(usuario,perfilRepository,Optional.of("Comprador"));
    }
    
    @Override
    public void executar() throws SQLException {
        service.criar(usuario);
    }
    
}

