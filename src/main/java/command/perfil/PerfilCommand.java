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
import repository.ICondutaRepository;
import repository.IPerfilRepository;
import repository.IReputacaoRepository;
import repository.IUsuarioRepository;
import service.PerfilVendedorService;

/**
 *
 * @author caiof
 */
public abstract class PerfilCommand implements ICommand{
    
    protected Usuario usuario;
    protected PerfilService service;
    protected IReputacaoRepository reputacaoRepository;
    protected ICondutaRepository condutaRepository;
    protected IUsuarioRepository usuarioRepository;
    
    public PerfilCommand(Usuario usuario, IPerfilRepository perfilRepository) throws SQLException {
        this.usuarioRepository = SeletorRepositoryFactory.obterInstancia().criarUsuarioRepository();
        this.usuario=usuarioRepository.getUsuario(usuario.getId()).get();
        System.out.println("PerfilCOmmnad"+usuario.getId());
        this.reputacaoRepository = SeletorRepositoryFactory.obterInstancia().criarReputacaoRepository();
        this.condutaRepository = SeletorRepositoryFactory.obterInstancia().criarCondutaRepository();
        this.service = new PerfilVendedorService(reputacaoRepository,condutaRepository,perfilRepository,usuarioRepository);
    }
    
    public abstract void executar() throws SQLException;

}
