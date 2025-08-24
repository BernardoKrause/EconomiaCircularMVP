/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package repository.teste;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.SolicitacaoPerfil;
import repository.IPerfilSolicitacaoRepository;

/**
 *
 * @author caiof
 */
public class PerfilSolicitacaoRepositoryTeste implements IPerfilSolicitacaoRepository{
    private List<SolicitacaoPerfil> solicitacoes;
    private static PerfilSolicitacaoRepositoryTeste instancia;

    private PerfilSolicitacaoRepositoryTeste() throws SQLException {
        solicitacoes=new ArrayList<>();
    }
    
    public static PerfilSolicitacaoRepositoryTeste getInstancia() {
        if(instancia == null){
            try {
                instancia = new PerfilSolicitacaoRepositoryTeste();
            } catch (SQLException ex) {
                Logger.getLogger(PerfilSolicitacaoRepositoryTeste.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return instancia;
    }

    @Override
    public void adicionarSolicitacao(Integer idUsuario) throws SQLException {
        solicitacoes.add(new SolicitacaoPerfil("A", idUsuario));
    }

    @Override
    public List<SolicitacaoPerfil> buscarTodasSolicitacoes() throws SQLException {
        List<SolicitacaoPerfil> lista = null;
        for(SolicitacaoPerfil solicitacao : solicitacoes){
            lista.add(solicitacao);
        }
        return lista;
    }
    
    @Override
    public List<SolicitacaoPerfil> buscarTodasSolicitacoesEmAguardo() throws SQLException {
        List<SolicitacaoPerfil> lista = null;
        for(SolicitacaoPerfil solicitacao : solicitacoes){
            if(solicitacao.getStatus().equalsIgnoreCase("A")){
                lista.add(solicitacao);
            }
        }
        return lista;
    }

    @Override
    public void atualizarSolicitacao(SolicitacaoPerfil solicitacaoAnalisada, String status) throws SQLException {
        solicitacaoAnalisada.setStatus(status);
    }
    
    @Override
    public void deletarSolicitacao(Integer id) throws SQLException {
        //para fazer
    }
}
