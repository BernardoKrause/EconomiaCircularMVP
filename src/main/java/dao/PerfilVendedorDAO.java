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
public interface PerfilVendedorDAO {
    public void criar(Vendedor item) throws SQLException;
    public List<Vendedor> buscaTodos() throws SQLException;
    public void atualizar(Vendedor vendedor) throws SQLException;
    public void deletar(String id) throws SQLException;
}
