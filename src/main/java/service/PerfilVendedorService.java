/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package service;

import java.sql.SQLException;
import model.Usuario;
import model.Vendedor;
import repository.ICondutaRepository;
import repository.IReputacaoRepository;
import repository.IPerfilRepository;

/**
 *
 * @author caiof
 */
public class PerfilVendedorService extends PerfilService{

    private IPerfilRepository perfilRepository;

    public PerfilVendedorService(IReputacaoRepository reputacaoRepository, IPerfilRepository vendedorRepository) {
        super(reputacaoRepository);
        this.perfilRepository=vendedorRepository;
    }
    
    
    @Override
    public void criar(Usuario usuario) {
        if (usuario == null) {
            throw new IllegalArgumentException("Usuario informado não pode ser nulo!");
        }
        
        try {
            Vendedor perfil = new Vendedor("V-"+usuario.getId());
            usuario.addPerfil(perfil);
            perfil.setUsuario(usuario);
            reputacaoRepository.salvarReputacao(perfil.getReputacao());
            perfilRepository.salvarPerfil(perfil);   
        } catch (SQLException ex) {
            throw new RuntimeException("Seu Perfil não pode ser criado!");
        }    
    }
}
