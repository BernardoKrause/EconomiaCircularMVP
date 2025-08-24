/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package repository;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;
import model.Conduta;
import model.Reputacao;

/**
 *
 * @author caiof
 */
public interface ICondutaRepository {
    public void adicionarConduta(Reputacao reputacao, Conduta conduta) throws SQLException;
    public List<Conduta> buscarCondutasPorTipo(Reputacao reputacao, String tipo) throws SQLException;
    public List<Conduta> buscarTodasCondutas(Reputacao reputacao) throws SQLException;
}
