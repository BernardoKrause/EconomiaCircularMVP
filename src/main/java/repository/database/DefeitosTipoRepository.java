/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package repository.database;

import dao.IDefeitoDAO;
import factory.dao.IDAOFactory;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;
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
    public Optional<List<String>> BuscarPorTipo(String tipoItem) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public Double getPercentualPorDefeito(String defeito) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public Optional<List<String>> getTiposItem() throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

}
