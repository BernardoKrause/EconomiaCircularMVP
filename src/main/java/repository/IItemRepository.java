/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package repository;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;
import model.Item;
import model.Material;
import model.Vendedor;

/**
 *
 * @author caiof
 */
public interface IItemRepository {
    
    public void adicionarItem(Item item) throws SQLException;
    
    public List<Item> buscarTodos() throws SQLException;

    public List<Item> buscarPorVendedor(Vendedor vendedor);
    
    public Optional<Item> buscarPorId(Integer id) throws SQLException;
    
    public Integer buscarQuantidadeItens() throws SQLException ;
    
    //Decisão de arquitetura de projeto
    public List<String> buscarTiposItem() throws SQLException;

    //Decisão de arquitetura de projeto
    public List<Material> buscarMaterialPorTipoItem(String tipo) throws SQLException;

    public Double buscarFatorEmissaoMaterial(String nomeMaterial) throws SQLException;
    
    public void atualizarItem(Item item) throws SQLException;
}
