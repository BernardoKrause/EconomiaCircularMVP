/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package dao;

import model.Oferta;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

/**
 *
 * @author berna
 */
public interface IOfertaDAO {
    public void criar(Oferta oferta) throws SQLException;
    public List<Oferta> buscaTodos() throws SQLException;
    public List<Oferta> buscaPorComprador(Integer id) throws SQLException;
    public void atualizar(Oferta oferta) throws SQLException;
    public void deletar(Integer id) throws SQLException;
    public List<Oferta> buscaRecentes(int limite) throws SQLException;
}
