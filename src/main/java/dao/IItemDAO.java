package dao;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;
import model.Item;

 /*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */

/**
 *
 * @author berna
 */
public interface IItemDAO {
    public void criar(Item item) throws SQLException;
    public List<Item> buscaTodos() throws SQLException;
    public Optional<Item> buscaPorId(Integer id) throws SQLException;
    public List<String> buscaTipos() throws SQLException;
    public List<Item> buscaPorVendedor(Integer idVendedor) throws SQLException;
    public void atualizar(Item item) throws SQLException;
    public void deletar(Integer id) throws SQLException;
    public Integer buscaIdTipo (String descricao) throws SQLException;
}
