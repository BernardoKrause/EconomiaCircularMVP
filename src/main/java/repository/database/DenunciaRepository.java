/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package repository.database;

import factory.dao.IDAOFactory;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;
import model.Comprador;
import model.Denuncia;
import model.Vendedor;
import repository.IDenunciaRepository;
import dao.IDenunciaDAO;

/**
 *
 * @author berna
 */
public class DenunciaRepository implements IDenunciaRepository{
    private final IDenunciaDAO denunciaDAO;

    public DenunciaRepository(IDAOFactory daoFactory) throws SQLException {
        this.denunciaDAO = daoFactory.getDenunciaDAO();
    }

     @Override
    public void adicionarDenuncia(String idC, String descricao, String status, Comprador comprador, Vendedor vendedor) throws SQLException {
        denunciaDAO.criar(new Denuncia(idC, descricao, status, comprador, vendedor));
    }

     @Override
    public Optional<Denuncia> buscarDenuncia(Integer id) throws SQLException {
        return denunciaDAO.buscaPorId(id);
    }

     @Override
    public List<Denuncia> buscarTodasDenuncias() throws SQLException {
        return denunciaDAO.buscaTodos();
    }

     @Override
    public void atualizarDenuncia(String idC, String descricao, String status, Comprador comprador, Vendedor vendedor) throws SQLException {
        denunciaDAO.atualizar(new Denuncia(idC, descricao, status, comprador, vendedor));
    }
    
     @Override
    public void deletarDenuncia(Integer id) throws SQLException {
        denunciaDAO.deletar(id);
    }
}
