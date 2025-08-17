package command.perfil;


import factory.repository.SeletorRepositoryFactory;
import java.sql.SQLException;
import model.Usuario;
import repository.IPerfilRepository;
import repository.IReputacaoRepository;
import service.PerfilService;
import service.PerfilVendedorService;

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
