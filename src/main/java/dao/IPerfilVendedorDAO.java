/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package dao;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;
import model.Vendedor;

/**
 *
 * @author berna
 */
public interface IPerfilVendedorDAO {
    public void criar(Vendedor vendedor) throws SQLException;
    public Optional<Vendedor> buscaPorIdUsuario (Integer id) throws SQLException;
    public List<Vendedor> buscaTodos() throws SQLException;
    public void deletar(Integer id) throws SQLException;
}
