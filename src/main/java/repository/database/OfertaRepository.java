/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package repository.database;

import dao.IOfertaDAO;
import factory.dao.IDAOFactory;
import java.sql.SQLException;
import java.util.List;
import model.Oferta;
import repository.IOfertaRepository;

/**
 *
 * @author berna
 */
public class OfertaRepository implements IOfertaRepository {

    private IOfertaDAO ofertaDAO;
    
    public OfertaRepository (IDAOFactory daoFactory) throws SQLException {
        this.ofertaDAO = daoFactory.getOfertaDAO();
    }
    
    @Override
    public void adicionarOferta(Oferta oferta) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void atualizarOferta(Oferta oferta) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public List<Oferta> buscarTodas() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public List<Oferta> buscarPorItem(String idC) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
    
}
