/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package repository;

import java.sql.SQLException;
import java.util.List;
import model.Oferta;

/**
 *
 * @author caiof
 */
public interface IOfertaRepository {

    public void adicionarOferta(Oferta oferta) throws SQLException;
    public void atualizarOferta(Oferta oferta) throws SQLException;
    public List<Oferta> buscarTodas() throws SQLException;
    public List<Oferta> buscarPorItem(String idC) throws SQLException;
}
