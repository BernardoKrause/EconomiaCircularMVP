/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package repository.database;

import dao.sqlite.PerfilSolicitacaoDAOSQLite;
import factory.dao.IDAOFactory;
import java.sql.SQLException;
import java.util.List;
import model.SolicitacaoPerfil;
import repository.IPerfilSolicitacaoRepository;
import dao.IPerfilSolicitacaoDAO;

/**
 *
 * @author berna
 */
public class PerfilSolicitacaoRepository implements IPerfilSolicitacaoRepository{
    private final IPerfilSolicitacaoDAO perfilSolicitacaoDAO;

    public PerfilSolicitacaoRepository(IDAOFactory daoFactory) throws SQLException {
        this.perfilSolicitacaoDAO = daoFactory.getPerfilSolicitacaoDAO();
    }

    @Override
    public void adicionarSolicitacao(Integer idUsuario) throws SQLException {
        perfilSolicitacaoDAO.criar(new SolicitacaoPerfil(idUsuario));
    }

    @Override
    public List<SolicitacaoPerfil> buscarTodasSolicitacoes() throws SQLException {
        return perfilSolicitacaoDAO.buscaTodos();
    }
    
    @Override
    public List<SolicitacaoPerfil> buscarTodasSolicitacoesEmAguardo() throws SQLException {
        return perfilSolicitacaoDAO.buscaEmAguardo();
    }

    @Override
    public void atualizarSolicitacao(SolicitacaoPerfil solicitacaoAnalisada,String status) throws SQLException {
        perfilSolicitacaoDAO.atualizar(new SolicitacaoPerfil(status));
    }
    
    @Override
    public void deletarSolicitacao(Integer id) throws SQLException {
        perfilSolicitacaoDAO.deletar(id);
    }
}
