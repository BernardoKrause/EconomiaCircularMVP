/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package repository;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;
import model.Item;
import model.Vendedor;

/**
 *
 * @author caiof
 */
public interface IItemRepository {
    
    public Optional<List<Item>> BuscarPorVendedor(Vendedor vendedor);
    
    public Optional<Item> BuscarPorIdC(String idC) throws SQLException;
    
    public Integer getQuantidadeItens() throws SQLException ;
    
    public void salvarItem(Item item) throws SQLException;
    
    //Decisão de arquitetura de projeto
    public Optional<List<String>> getTiposItem() throws SQLException;
}
