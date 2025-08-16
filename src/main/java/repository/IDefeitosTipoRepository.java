/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package repository;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

/**
 *
 * @author berna
 */
public interface IDefeitosTipoRepository {
    public Optional<List<String>> BuscarPorTipo(String tipoItem) throws SQLException;

    public Double getPercentualPorDefeito(String defeito) throws SQLException;    
    
    //Decisão de arquitetura de projeto
    public Optional<List<String>> getTiposItem() throws SQLException;
}
