/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package dao;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;
import model.Material;

/**
 *
 * @author berna
 */
public interface IMaterialDAO {
    public Optional<Material> buscaPorId(Integer id) throws SQLException;
    public List<Material> buscaTodos() throws SQLException;
    public Optional<Double> buscaFatorEmissao(String nomeMaterial) throws SQLException;
    public List<Material> buscaPorTipoItem(String tipo) throws SQLException;
    public List<Material> buscaMateriaisBase() throws SQLException;
    public List<Material> buscaPorItem(Integer idItem) throws SQLException;
}
