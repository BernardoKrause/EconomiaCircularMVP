/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package repository.database;

import dao.PerfilSolicitacaoDAO;
import dao.PerfilSolicitacaoDAOSQLite;
import factory.dao.IDAOFactory;
import java.sql.SQLException;
import java.util.List;
import model.SolicitacaoPerfil;
import repository.IPerfilSolicitacaoRepository;

/**
 *
 * @author berna
 */
public class PerfilSolicitacaoRepository implements IPerfilSolicitacaoRepository{
    private final PerfilSolicitacaoDAO perfilSolicitacaoDAO;

    public PerfilSolicitacaoRepository(IDAOFactory daoFactory) throws SQLException {
        this.perfilSolicitacaoDAO = daoFactory.getPerfilSolicitacaoDAO();
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
