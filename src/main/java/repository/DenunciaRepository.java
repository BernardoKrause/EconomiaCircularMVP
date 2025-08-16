/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package repository;

import dao.DenunciaDAO;
import dao.DenunciaDAOSQLite;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;
import model.Comprador;
import model.Denuncia;
import model.Vendedor;

/**
 *
 * @author berna
 */
public class DenunciaRepository {
     private final DenunciaDAO denunciaDAO;

    public DenunciaRepository() throws SQLException {
        this.denunciaDAO = new DenunciaDAOSQLite();
    }

    public void adicionaDenuncia(String idC, String descricao, String status, Comprador comprador, Vendedor vendedor) throws SQLException {
        denunciaDAO.criar(new Denuncia(idC, descricao, status, comprador, vendedor));
    }

    public Optional<Denuncia> getDenuncia(Integer id) throws SQLException {
        return denunciaDAO.buscaPorId(id);
    }

    public List<Denuncia> getTodasDenuncias() throws SQLException {
        return denunciaDAO.buscaTodos();
    }

    public void atualizaDenuncia(String idC, String descricao, String status, Comprador comprador, Vendedor vendedor) throws SQLException {
        denunciaDAO.atualizar(new Denuncia(idC, descricao, status, comprador, vendedor));
    }
    
    public void deletaDenuncia(Integer id) throws SQLException {
        denunciaDAO.deletar(id);
    }
}
