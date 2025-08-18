/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package repository.database;

import dao.ICondutaDAO;
import factory.dao.IDAOFactory;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;
import model.Conduta;
import model.Reputacao;
import repository.ICondutaRepository;

/**
 *
 * @author caiof
 */
public class CondutaRepository implements ICondutaRepository{
    
    private ICondutaDAO condutaDAO;
    
    public CondutaRepository (IDAOFactory daoFactory) throws SQLException {
        this.condutaDAO = daoFactory.getCondutaDAO();
    }

    @Override
    public void salvarConduta(Reputacao reputacao, Conduta conduta) throws SQLException {
        condutaDAO.criar(reputacao, conduta);
    }

    @Override
    public Optional<List<Conduta>> getTodasCondutas(Reputacao reputacao) throws SQLException {
        return condutaDAO.buscaPorReputacao(reputacao);
    }
    
    @Override
    public Optional<List<Conduta>> getCondutasPorTipo(Reputacao reputacao, String tipo) throws SQLException {
        return condutaDAO.buscaPorTipo(reputacao.getId(), tipo);
    }

}
