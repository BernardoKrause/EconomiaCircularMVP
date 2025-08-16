/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package repository.sqlite;

import dao.DenunciaDAO;
import dao.DenunciaDAOSQLite;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;
import model.Comprador;
import model.Denuncia;
import model.Vendedor;
import repository.IDenunciaRepository;

/**
 *
 * @author berna
 */
public class DenunciaRepositorySqLite implements IDenunciaRepository{
     private final DenunciaDAO denunciaDAO;

    public DenunciaRepositorySqLite() throws SQLException {
        this.denunciaDAO = new DenunciaDAOSQLite();
    }

     @Override
    public void adicionaDenuncia(String idC, String descricao, String status, Comprador comprador, Vendedor vendedor) throws SQLException {
        denunciaDAO.criar(new Denuncia(idC, descricao, status, comprador, vendedor));
    }

     @Override
    public Optional<Denuncia> getDenuncia(Integer id) throws SQLException {
        return denunciaDAO.buscaPorId(id);
    }

     @Override
    public List<Denuncia> getTodasDenuncias() throws SQLException {
        return denunciaDAO.buscaTodos();
    }

     @Override
    public void atualizaDenuncia(String idC, String descricao, String status, Comprador comprador, Vendedor vendedor) throws SQLException {
        denunciaDAO.atualizar(new Denuncia(idC, descricao, status, comprador, vendedor));
    }
    
     @Override
    public void deletaDenuncia(Integer id) throws SQLException {
        denunciaDAO.deletar(id);
    }
}
