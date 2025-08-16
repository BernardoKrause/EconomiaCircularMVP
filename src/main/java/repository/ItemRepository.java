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
    
    public ItemRepository() {
        this.itemDAO = new ItemDAOSQLite();
    }
    
    public Optional<List<Item>> BuscarPorVendedor(Vendedor vendedor){
        return null;
    }
    
    public Optional<Item> BuscarPorIdC(Integer id) throws SQLException{
        return itemDAO.buscaPorId(id);
    }
    
    public Integer getQuantidadeItens() throws SQLException {
        return itemDAO.buscaTodos().size();
    }
    
    public void salvarItem(Item item) throws SQLException{
        itemDAO.criar(item);
    }
}
