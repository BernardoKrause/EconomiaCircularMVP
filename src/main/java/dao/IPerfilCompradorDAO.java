package dao;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;
import model.Comprador;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */

/**
 *
 * @author berna
 */
public interface IPerfilCompradorDAO {
    public void criar(Comprador comprador) throws SQLException;
    public Optional<Comprador> buscaPorIdUsuario (Integer id) throws SQLException;
    public List<Comprador> buscaTodos() throws SQLException;
    public void deletar(Integer id) throws SQLException;
}
