/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package repository;

import dao.ItemDAOSQLite;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import model.Item;
import model.Vendedor;

/**
 *
 * @author caiof
 */
public class ItemRepository {
    private ItemDAOSQLite itemDAO;
    
    public ItemRepository()throws SQLException {
        try {
            this.itemDAO = new ItemDAOSQLite();
        } catch (SQLException ex) {
            ex.printStackTrace();
            throw ex;
        }
    }
    
    public Optional<List<Item>> BuscarPorVendedor(Vendedor vendedor){
        return null;
    }
    
    public Optional<Item> BuscarPorIdC(String idC) throws SQLException{
        return itemDAO.buscaPorIdC(Integer.valueOf(idC));
    }
    
    public Integer getQuantidadeItens() throws SQLException {
        return itemDAO.buscaTodos().size();
    }
    
    public void salvarItem(Item item) throws SQLException{
        itemDAO.criar(item);
    }
}
