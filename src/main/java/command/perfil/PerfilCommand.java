/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package command.perfil;

import model.Usuario;
import repository.PerfilVendedorRepository;
import service.PerfilService;
import command.ICommand;
import java.sql.SQLException;

/**
 *
 * @author caiof
 */
public abstract class PerfilCommand implements ICommand{
    
    protected Usuario usuario;
    protected PerfilVendedorRepository perfilVendedorRepository ;
    protected PerfilService service;
    
    public PerfilCommand(Usuario usuario) throws SQLException {
        super();
        this.usuario=usuario;
        this.perfilVendedorRepository = new PerfilVendedorRepository();
        this.service = new PerfilService(perfilVendedorRepository);
    }
    
    public abstract void executar();

}
