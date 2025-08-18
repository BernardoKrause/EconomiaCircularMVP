/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package service;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import model.Conduta;
import model.Perfil;
import model.Reputacao;
import model.Usuario;
import model.Vendedor;
import repository.ICondutaRepository;
import repository.IReputacaoRepository;
import repository.database.PerfilVendedorRepository;
import repository.IPerfilRepository;

/**
 *
 * @author berna
 */
public abstract class PerfilService {
    protected IReputacaoRepository reputacaoRepository;
    protected ICondutaRepository condutaRepository;
    
    public PerfilService(IReputacaoRepository reputacaoRepository,ICondutaRepository condutaRepository){
        this.reputacaoRepository=reputacaoRepository;
        this.condutaRepository=condutaRepository;
    }
    
    public abstract void criar(Usuario usuario) throws SQLException;
    
    public void completarPerfil(Perfil perfil) throws SQLException{
        Reputacao reputacao = reputacaoRepository.getReputacao(perfil).get();
        reputacao.setCondutas(condutaRepository.getTotalCondutas(reputacao).get());
        
        perfil.setReputacao(reputacao);
    }
    
    public Optional<List<Conduta>> getListaCondutasTipo(Perfil perfil, String tipo) throws SQLException{
        Reputacao reputacao = reputacaoRepository.getReputacao(perfil).get();
        return condutaRepository.getCondutasPorTipo(reputacao, tipo);
    }
}
