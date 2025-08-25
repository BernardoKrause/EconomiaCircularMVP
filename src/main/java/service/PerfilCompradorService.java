/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package service;

import java.sql.SQLException;
import model.Comprador;
import model.Reputacao;
import model.Usuario;
import repository.ICondutaRepository;
import repository.IPerfilRepository;
import repository.IReputacaoRepository;
import repository.IUsuarioRepository;

/**
 *
 * @author caiof
 */
public class PerfilCompradorService extends PerfilService{

    private IPerfilRepository perfilRepository;

    public PerfilCompradorService(IReputacaoRepository reputacaoRepository, ICondutaRepository condutaRepository, IPerfilRepository compradorRepository, IUsuarioRepository usuarioRepository) {
        super(reputacaoRepository, condutaRepository, usuarioRepository);
        this.perfilRepository=compradorRepository;
    }
    
    
    @Override
    public void criar(Usuario usuario) {
        if (usuario == null) {
            throw new IllegalArgumentException("Usuario informado não pode ser nulo!");
        }
        
        try {
            Comprador perfil = new Comprador(perfilRepository.buscarTodosPerfis().size()+1);
            usuario.addPerfil(perfil);
            perfil.setUsuario(usuario);
            
            Reputacao reputacao = new Reputacao(perfilRepository.buscarTodosPerfis().size()+1);
            perfil.setReputacao(reputacao);
            
            usuarioRepository.atualizarUsuario(usuario);
            reputacaoRepository.adicionarReputacao(perfil.getReputacao());
            perfilRepository.adicionarPerfil(perfil);   
        } catch (SQLException ex) {
            throw new RuntimeException("Seu Perfil não pode ser criado!");
        }    
    }
}
