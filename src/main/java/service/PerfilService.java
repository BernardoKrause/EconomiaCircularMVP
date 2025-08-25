/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package service;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;
import model.Conduta;
import model.Perfil;
import model.Reputacao;
import model.Usuario;
import repository.ICondutaRepository;
import repository.IReputacaoRepository;
import repository.IUsuarioRepository;

/**
 *
 * @author berna
 */
public abstract class PerfilService {
    protected IReputacaoRepository reputacaoRepository;
    protected ICondutaRepository condutaRepository;
    protected IUsuarioRepository usuarioRepository;
    protected SistemaReputacaoService sysReputacaoService;
    
    public PerfilService(IReputacaoRepository reputacaoRepository,ICondutaRepository condutaRepository, IUsuarioRepository usuarioRepository){
        this.reputacaoRepository=reputacaoRepository;
        this.condutaRepository=condutaRepository;
        this.usuarioRepository=usuarioRepository;
        this.sysReputacaoService=new SistemaReputacaoService(condutaRepository, reputacaoRepository);
    }
    
    public abstract void criar(Usuario usuario) throws SQLException;
    
    public void completarPerfil(Perfil perfil) throws SQLException{
        System.out.println("erri");
        Reputacao reputacao = reputacaoRepository.buscarReputacao(perfil).get();
        System.out.println("erri2");
        reputacao.setCondutas(condutaRepository.buscarTodasCondutas(reputacao));
        System.out.println("erri3");
        System.out.println(reputacao.getId());
        perfil.setReputacao(reputacao);
    }
    
    public List<Conduta> getListaCondutasTipo(Perfil perfil, String tipo) throws SQLException{
        Reputacao reputacao = reputacaoRepository.buscarReputacao(perfil).get();
        return condutaRepository.buscarCondutasPorTipo(reputacao, tipo);
    }
    
    public void atualizarReputacao(Perfil perfil) throws SQLException{
        sysReputacaoService.atualizarReputacao(perfil, Optional.empty());
    }
    
    public void atualizarReputacao(Perfil perfil, String nomeConduta) throws SQLException{
        sysReputacaoService.atualizarReputacao(perfil, Optional.of(nomeConduta));
    }
}
