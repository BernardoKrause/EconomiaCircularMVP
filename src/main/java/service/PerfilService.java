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
    
    public PerfilService(IReputacaoRepository reputacaoRepository,ICondutaRepository condutaRepository, IUsuarioRepository usuarioRepository){
        this.reputacaoRepository=reputacaoRepository;
        this.condutaRepository=condutaRepository;
        this.usuarioRepository=usuarioRepository;
    }
    
    public abstract void criar(Usuario usuario) throws SQLException;
    
    public void completarPerfil(Perfil perfil) throws SQLException{
        Reputacao reputacao = reputacaoRepository.getReputacao(perfil).get();
        reputacao.setCondutas(condutaRepository.getTodasCondutas(reputacao).get());
        
        perfil.setReputacao(reputacao);
    }
    
    public Optional<List<Conduta>> getListaCondutasTipo(Perfil perfil, String tipo) throws SQLException{
        Reputacao reputacao = reputacaoRepository.getReputacao(perfil).get();
        return condutaRepository.getCondutasPorTipo(reputacao, tipo);
    }
}
