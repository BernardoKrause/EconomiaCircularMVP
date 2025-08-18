/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package repository.database;

import dao.IReputacaoDAO;
import factory.dao.IDAOFactory;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;
import model.Perfil;
import model.Reputacao;
import repository.IReputacaoRepository;

/**
 *
 * @author caiof
 */
public class ReputacaoRepository implements IReputacaoRepository{
    private final IReputacaoDAO reputacaoDAO;
    
    public ReputacaoRepository(IDAOFactory daoFactory) {
        reputacaoDAO = daoFactory.getReputacaoDAO();
    }
    
    @Override
    public void salvarReputacao(Reputacao reputacao) throws SQLException {
        reputacaoDAO.criar(reputacao);
    }
    
    @Override
    public Optional<Reputacao> getReputacao(Perfil perfil) throws SQLException {
        return reputacaoDAO.buscaPorPerfil(perfil);
    }

    @Override
    public Optional<List<Reputacao>> getTodos() throws SQLException {
        return reputacaoDAO.buscaTodos();
    }

    @Override
    public void atualizarReputacao(Reputacao reputacao) throws SQLException {
        reputacaoDAO.atualizar(reputacao);
    }

    @Override
    public void deletarReputacao(Integer id) throws SQLException {
        reputacaoDAO.deletar(id);
    }
}
