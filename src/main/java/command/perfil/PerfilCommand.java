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
    protected PerfilRepository repo = new PerfilRepository();
    protected PerfilService service = new PerfilService(repo);
    
    public PerfilCommand(Usuario usuario){
        super();
        this.usuario=usuario;
        this.repo = new PerfilRepository();
        this.service = new PerfilService(repo);
    }
    
    public abstract void executar();

}
