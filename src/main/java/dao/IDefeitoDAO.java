/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package dao;

import java.sql.SQLException;
import java.util.List;
import model.Defeito;

/**
 *
 * @author berna
 */
public interface IDefeitoDAO {
    public void criar(Defeito defeito) throws SQLException;
    public List<Defeito> buscaTodos() throws SQLException;
    public List<Defeito> buscaDefeitosItem(Integer idItem) throws SQLException;
}
