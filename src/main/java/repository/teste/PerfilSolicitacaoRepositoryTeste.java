/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package repository.teste;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import model.SolicitacaoPerfil;
import repository.IPerfilSolicitacaoRepository;

/**
 *
 * @author caiof
 */
public class PerfilSolicitacaoRepositoryTeste implements IPerfilSolicitacaoRepository{
    private List<SolicitacaoPerfil> solicitacoes;

    public PerfilSolicitacaoRepositoryTeste() throws SQLException {
        solicitacoes=new ArrayList<>();
    }

    @Override
    public void adicionaSolicitacao(Integer idUsuario) throws SQLException {
        solicitacoes.add(new SolicitacaoPerfil("A", idUsuario));
    }

    @Override
    public List<SolicitacaoPerfil> getTodasSolicitacoes() throws SQLException {
        List<SolicitacaoPerfil> lista = null;
        for(SolicitacaoPerfil solicitacao : solicitacoes){
            lista.add(solicitacao);
        }
        return lista;
    }
    
    @Override
    public List<SolicitacaoPerfil> getTodasSolicitacoesEmAguardo() throws SQLException {
        List<SolicitacaoPerfil> lista = null;
        for(SolicitacaoPerfil solicitacao : solicitacoes){
            if(solicitacao.getStatus().equalsIgnoreCase("A")){
                lista.add(solicitacao);
            }
        }
        return lista;
    }

    @Override
    public void atualizaSolicitacao(SolicitacaoPerfil solicitacaoAnalisada, String status) throws SQLException {
        solicitacaoAnalisada.setStatus(status);
    }
    
    @Override
    public void deletaSolicitacao(Integer id) throws SQLException {
        //para fazer
    }
}
