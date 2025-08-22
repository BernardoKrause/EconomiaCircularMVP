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
import model.Material;

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
    public void adicionarItem(Item item) throws SQLException{
        itemDAO.criar(item);
    }
    
    @Override
    public List<Item> buscarPorVendedor(Vendedor vendedor){
        return null;
    }

    @Override
    public Optional<Item> buscarPorId(Integer id) throws SQLException{
        return itemDAO.buscaPorId(id);
    }

    @Override
    public Integer buscarQuantidadeItens() throws SQLException {
        return itemDAO.buscaTodos().size();
    }

    @Override
    public List<String> buscarTiposItem() {
        //implementar
        // puxar da tabela item_tipos
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public Double buscarFatorEmissaoMaterial(String nomeMaterial) {
        //implementar
        // puxar da tabela materiais
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public Optional<List<Item>> buscarTodos() {
        //implementar
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void atualizarItem(Item item) throws SQLException {
        itemDAO.atualizar(item);
    }

    @Override
    public List<Material> buscarMaterialItem() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
}