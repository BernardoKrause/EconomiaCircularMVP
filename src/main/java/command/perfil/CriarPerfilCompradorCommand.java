/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package command.perfil;

import factory.repository.SeletorRepositoryFactory;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Usuario;
import repository.IPerfilRepository;
import service.PerfilService;

/**
 *
 * @author caiof
 */
public class CriarPerfilCompradorCommand extends PerfilCommand{
    public CriarPerfilCompradorCommand(Usuario usuario,IPerfilRepository perfilRepository) throws SQLException {
        super(usuario,perfilRepository);
    }
    
    @Override
    public void executar() {
        try {
            service.criar(usuario);
        } catch (SQLException ex) {
            Logger.getLogger(CriarPerfilCompradorCommand.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}

