/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package repository.teste;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import model.Perfil;
import model.Reputacao;
import repository.IReputacaoRepository;

/**
 *
 * @author caiof
 */
public class ReputacaoRepositoryTeste implements IReputacaoRepository{
    private List<Reputacao> reputacoes;
    private static ReputacaoRepositoryTeste instancia;
    
    private ReputacaoRepositoryTeste(){
        reputacoes=new ArrayList<>();
    }
    
    public static ReputacaoRepositoryTeste getInstancia(){
        if(instancia==null){
            instancia= new ReputacaoRepositoryTeste();
        }
        return instancia;
    }

    @Override
    public void adicionarReputacao(Reputacao reputacao) {
        reputacoes.add(reputacao);
    }

    @Override
    public Optional<Reputacao> buscarReputacao(Perfil perfil) {
        for(Reputacao rep : reputacoes){
            if(perfil.getReputacao().getId().equals(rep.getId())){
                return Optional.of(rep);
            }
        }
        
        return Optional.empty();
    }

    @Override
    public List<Reputacao> buscarTodos() throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void atualizarReputacao(Reputacao reputacao) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void deletarReputacao(Integer id) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
}
