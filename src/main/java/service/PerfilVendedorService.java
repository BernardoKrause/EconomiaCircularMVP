/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package service;

import java.sql.SQLException;
import model.Reputacao;
import model.Usuario;
import model.Vendedor;
import repository.ICondutaRepository;
import repository.IReputacaoRepository;
import repository.IPerfilRepository;
import repository.IUsuarioRepository;

/**
 *
 * @author caiof
 */
public class PerfilVendedorService extends PerfilService{

    private IPerfilRepository perfilRepository;

    public PerfilVendedorService(IReputacaoRepository reputacaoRepository, ICondutaRepository condutaRepository, IPerfilRepository vendedorRepository, IUsuarioRepository usuarioRepository) {
        super(reputacaoRepository, condutaRepository, usuarioRepository);
        this.perfilRepository=vendedorRepository;
    }
    
    
    @Override
    public void criar(Usuario usuario) {
        if (usuario == null) {
            throw new IllegalArgumentException("Usuario informado não pode ser nulo!");
        }
        
        try {
            Vendedor perfil = new Vendedor(usuario);
            usuario.addPerfil(perfil);
            
            usuarioRepository.atualizarUsuario(usuario);
            reputacaoRepository.adicionarReputacao(perfil.getReputacao());
            perfilRepository.adicionarPerfil(perfil);
        } catch (SQLException ex) {
            throw new RuntimeException("Seu Perfil não pode ser criado!");
        }    
    }
}
