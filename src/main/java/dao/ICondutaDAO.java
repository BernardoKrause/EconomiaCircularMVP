package dao;


import java.sql.SQLException;
import java.util.List;
import java.util.Optional;
import model.Conduta;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */

/**
 *
 * @author berna
 */
public interface ICondutaDAO {
    public void criar(Conduta conduta) throws SQLException;
    public List<Conduta> buscaTodos() throws SQLException;
    public Optional<Conduta> buscaPorTipo(String tipo) throws SQLException;
}
