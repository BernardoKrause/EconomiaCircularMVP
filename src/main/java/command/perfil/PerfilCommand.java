/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package command.perfil;

import model.Usuario;
import repository.sqlite.PerfilVendedorRepositorySqLite;
import service.PerfilService;
import command.ICommand;
import java.sql.SQLException;

/**
 *
 * @author caiof
 */
public abstract class PerfilCommand implements ICommand{
    
    protected Usuario usuario;
    protected PerfilVendedorRepositorySqLite perfilVendedorRepository ;
    protected PerfilService service;
    
    public PerfilCommand(Usuario usuario) throws SQLException {
        super();
        this.usuario=usuario;
        this.perfilVendedorRepository = new PerfilVendedorRepositorySqLite();
        this.service = new PerfilService(perfilVendedorRepository);
    }
    
    public abstract void executar() throws SQLException;

}
