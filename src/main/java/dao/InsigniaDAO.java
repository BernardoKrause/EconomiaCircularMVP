/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package dao;

import model.Insignia;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

/**
 *
 * @author berna
 */
public interface InsigniaDAO {
    public void criar(Insignia insignia) throws SQLException;
    public List<Insignia> buscaTodos() throws SQLException;
    public Optional<Insignia> buscaPorId(Integer id) throws SQLException;
    public void atualizar(Insignia insignia) throws SQLException;
    public void deletar(Integer idInsignia) throws SQLException;
}
