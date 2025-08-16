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
    
    public void adicionaSolicitacao(Integer idUsuario) throws SQLException ;

    public List<SolicitacaoPerfil> getTodasSolicitacoes() throws SQLException ;
    
    public List<SolicitacaoPerfil> getTodasSolicitacoesEmAguardo() throws SQLException ;

    public void atualizaSolicitacao(SolicitacaoPerfil solicitacaoAnalisada, String status) throws SQLException ;
    
    public void deletaSolicitacao(Integer id) throws SQLException ;
}
