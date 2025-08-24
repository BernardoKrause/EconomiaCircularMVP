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
import dao.IMaterialDAO;
import model.Material;

/**
 *
 * @author caiof
 */
public class ItemRepository implements IItemRepository{
    private IItemDAO itemDAO;
    private IMaterialDAO materialDAO;

    public ItemRepository(IDAOFactory daoFactory) throws SQLException {
        this.itemDAO = daoFactory.getItemDAO();
        this.materialDAO = daoFactory.getMaterialDAO();
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
    public List<String> buscarTiposItem(String idC) throws SQLException {
        return itemDAO.buscaTipos(idC);
    }

    @Override
    public Double buscarFatorEmissaoMaterial(String nomeMaterial) throws SQLException {
        return materialDAO.buscaFatorEmissao(nomeMaterial).get();
    }

    @Override
    public List<Item> buscarTodos() throws SQLException {
        return itemDAO.buscaTodos();
    }
    
    @Override
    public List<Material> buscarMaterialPorTipoItem(String tipo) throws SQLException {
        return materialDAO.buscaPorTipoItem(tipo);
    }

    @Override
    public void atualizarItem(Item item) throws SQLException {
        itemDAO.atualizar(item);
    }

}