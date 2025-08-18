/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package dao;

import java.sql.SQLException;
import java.util.List;
import model.Reputacao;

/**
 *
 * @author berna
 */
public interface IReputacaoDAO {
    public void criar(Reputacao reputacao) throws SQLException;
    public List<Reputacao> buscaTodos() throws SQLException;
}
