package dao;


import java.sql.SQLException;
import java.util.List;
import model.Conduta;
import model.Reputacao;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */

/**
 *
 * @author berna
 */
public interface ICondutaDAO {
    public void criar(Reputacao reputacao, Conduta conduta) throws SQLException;
    public List<Conduta> buscaTodos() throws SQLException;
    public List<Conduta> buscaPorTipo(Integer idReputacao, String tipo) throws SQLException;
    public List<Conduta> buscaPorReputacao(Reputacao reputacao) throws SQLException;
}
