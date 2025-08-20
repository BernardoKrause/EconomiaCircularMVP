/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package repository;

import dao.sqlite.PerfilCompradorDAOSQLite;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;
import model.Comprador;
import dao.IPerfilCompradorDAO;

/**
 *
 * @author berna
 */
public class PerfilCompradorRepository {
    private final IPerfilCompradorDAO perfilCompradorDAO;

    public PerfilCompradorRepository() throws SQLException {
       this.perfilCompradorDAO = new PerfilCompradorDAOSQLite();
    }
    
    public void adicionaPerfil(Comprador comprador) throws SQLException {
        perfilCompradorDAO.criar(comprador);
    }
    
    public Optional<Comprador> getPorIdUsuario(Integer id) throws SQLException {
        return perfilCompradorDAO.buscaPorIdUsuario(id);
    }
    
    public List<Comprador> getTodosCompradores() throws SQLException {
        return perfilCompradorDAO.buscaTodos();
    }
    
    public void deletaPerfil(Integer id) throws SQLException {
        perfilCompradorDAO.deletar(id);
    }
}
