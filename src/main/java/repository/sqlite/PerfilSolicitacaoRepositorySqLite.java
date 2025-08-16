/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package repository.sqlite;

import dao.PerfilSolicitacaoDAO;
import dao.PerfilSolicitacaoDAOSQLite;
import java.sql.SQLException;
import java.util.List;
import model.SolicitacaoPerfil;
import repository.IPerfilSolicitacaoRepository;

/**
 *
 * @author berna
 */
public class PerfilSolicitacaoRepositorySqLite implements IPerfilSolicitacaoRepository{
    private final PerfilSolicitacaoDAO perfilSolicitacaoDAO;

    public PerfilSolicitacaoRepositorySqLite() throws SQLException {
        this.perfilSolicitacaoDAO = new PerfilSolicitacaoDAOSQLite();
    }

    @Override
    public void adicionaSolicitacao(Integer idUsuario) throws SQLException {
        perfilSolicitacaoDAO.criar(new SolicitacaoPerfil(idUsuario));
    }

    @Override
    public List<SolicitacaoPerfil> getTodasSolicitacoes() throws SQLException {
        return perfilSolicitacaoDAO.buscaTodos();
    }
    
    @Override
    public List<SolicitacaoPerfil> getTodasSolicitacoesEmAguardo() throws SQLException {
        return perfilSolicitacaoDAO.buscaEmAguardo();
    }

    @Override
    public void atualizaSolicitacao(SolicitacaoPerfil solicitacaoAnalisada,String status) throws SQLException {
        perfilSolicitacaoDAO.atualizar(new SolicitacaoPerfil(status));
    }
    
    @Override
    public void deletaSolicitacao(Integer id) throws SQLException {
        perfilSolicitacaoDAO.deletar(id);
    }
}
