/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package repository.database;

import factory.dao.IDAOFactory;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;
import model.Item;
import model.Vendedor;
import repository.IItemRepository;
import dao.IItemDAO;

/**
 *
 * @author caiof
 */
public class ItemRepository implements IItemRepository{
    private IItemDAO itemDAO;

    public ItemRepository(IDAOFactory daoFactory) throws SQLException {
        this.itemDAO = daoFactory.getItemDAO();
    }

    @Override
    public Optional<List<Item>> BuscarPorVendedor(Vendedor vendedor){
        return null;
    }

    @Override
    public Optional<Item> BuscarPorId(Integer id) throws SQLException{
        return itemDAO.buscaPorId(id);
    }

    @Override
    public Integer getQuantidadeItens() throws SQLException {
        return itemDAO.buscaTodos().size();
    }

    @Override
    public void salvarItem(Item item) throws SQLException{
        itemDAO.criar(item);
    }

    @Override
    public Optional<List<String>> getTiposItem() {
        //a fazer
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
}