/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package repository.database;

import dao.IDefeitoDAO;
import factory.dao.IDAOFactory;
import java.sql.SQLException;
import java.util.List;
import repository.IDefeitosTipoRepository;

/**
 *
 * @author berna
 */
public class DefeitosTipoRepository implements IDefeitosTipoRepository {

    private IDefeitoDAO defeitoDAO;
    
    public DefeitosTipoRepository(IDAOFactory daoFactory) throws SQLException {
        this.defeitoDAO = daoFactory.getDefeitoDAO();
    }

    @Override
    public List<String> buscarPorTipo(String tipoItem) throws SQLException {
        return defeitoDAO.buscaPorTipo(tipoItem);
    }

    @Override
    public Double buscarPercentualPorDefeito(String defeito) throws SQLException {
        return defeitoDAO.buscaPercentualPorDefeito(defeito);
    }

    @Override
    public List<String> buscarTiposItem() throws SQLException {
        return defeitoDAO.buscarTiposItem();
    }

}
