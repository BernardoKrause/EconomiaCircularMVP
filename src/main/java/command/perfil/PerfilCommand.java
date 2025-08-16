/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package command.perfil;

import model.Usuario;
import service.PerfilService;
import command.ICommand;
import factory.repository.SeletorRepositoryFactory;
import java.sql.SQLException;
import repository.IPerfilVendedorRepository;

/**
 *
 * @author caiof
 */
public abstract class PerfilCommand implements ICommand{
    
    protected Usuario usuario;
    protected IPerfilVendedorRepository perfilVendedorRepository ;
    protected PerfilService service;
    
    public PerfilCommand(Usuario usuario) throws SQLException {
        super();
        this.usuario=usuario;
        this.perfilVendedorRepository = SeletorRepositoryFactory.obterInstancia().criarPerfilVendedorRepository();
        this.service = new PerfilService(perfilVendedorRepository);
    }
    
    public abstract void executar() throws SQLException;

}
