/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package repository;

import dao.PerfilSolicitacaoDAO;
import dao.PerfilSolicitacaoDAOSQLite;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;
import model.SolicitacaoPerfil;
import model.Usuario;

/**
 *
 * @author berna
 */
public class PerfilSolicitacaoRepository {
    private final PerfilSolicitacaoDAO perfilSolicitacaoDAO;

    public PerfilSolicitacaoRepository() throws SQLException {
        this.perfilSolicitacaoDAO = new PerfilSolicitacaoDAOSQLite();
    }

    public void adicionaSolicitacao(Integer idUsuario) throws SQLException {
        perfilSolicitacaoDAO.criar(new SolicitacaoPerfil(idUsuario));
    }

    public List<SolicitacaoPerfil> getTodasSolicitacoes() throws SQLException {
        return perfilSolicitacaoDAO.buscaTodos();
    }
    
    public List<SolicitacaoPerfil> getTodasSolicitacoesEmAguardo() throws SQLException {
        return perfilSolicitacaoDAO.buscaEmAguardo();
    }

    public void atualizaSolicitacao(String status) throws SQLException {
        perfilSolicitacaoDAO.atualizar(new SolicitacaoPerfil(status));
    }
    
    public void deletaSolicitacao(Integer id) throws SQLException {
        perfilSolicitacaoDAO.deletar(id);
    }
}
