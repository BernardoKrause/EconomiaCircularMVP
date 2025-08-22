/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package repository;

import java.sql.SQLException;
import java.util.List;
import model.SolicitacaoPerfil;

/**
 *
 * @author caiof
 */
public interface IPerfilSolicitacaoRepository {
    
    public void adicionarSolicitacao(Integer idUsuario) throws SQLException ;

    public List<SolicitacaoPerfil> buscarTodasSolicitacoes() throws SQLException ;
    
    public List<SolicitacaoPerfil> buscarTodasSolicitacoesEmAguardo() throws SQLException ;

    public void atualizarSolicitacao(SolicitacaoPerfil solicitacaoAnalisada, String status) throws SQLException ;
    
    public void deletarSolicitacao(Integer id) throws SQLException ;
}
