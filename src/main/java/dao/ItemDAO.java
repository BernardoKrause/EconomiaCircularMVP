package dao;

import java.sql.SQLException;
import java.util.List;
import model.Item;
import model.Vendedor;

 /*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */

/**
 *
 * @author berna
 */
public interface ItemDAO {
    public void criar(Item item) throws SQLException;
    public List<Item> buscaTodos() throws SQLException;
    public void atualizar(Item item) throws SQLException;
    public void deletar(Integer id) throws SQLException;
    public Integer getIdVendedor(Vendedor vendedor) throws SQLException;
}
