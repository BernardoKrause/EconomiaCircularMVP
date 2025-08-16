package dao;

import java.sql.SQLException;
import java.util.List;
import model.Comprador;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */

/**
 *
 * @author berna
 */
public interface PerfilCompradorDAO {
    public void criar(Comprador comprador) throws SQLException;
    public List<Comprador> buscaTodos() throws SQLException;
    public void deletar(Integer id) throws SQLException;
}
