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
    
    public ReputacaoRepository(IDAOFactory daoFactory) throws SQLException {
       this.reputacaoDAO = daoFactory.getReputacaoDAO();
    }
    
    @Override
    public void salvarReputacao(Reputacao reputacao) throws SQLException {
        reputacaoDAO.criar(reputacao);
    }

    // implementar
    @Override
    public Optional<Reputacao> getReputacao(Perfil perfil) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    public List<Reputacao> getTodasReputacoes() throws SQLException {
        return reputacaoDAO.buscaTodos();
    }
    
}
