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
    public List<String> buscarPorTipo(String tipoItem) throws SQLException;

    public Double buscarPercentualPorDefeito(String defeito) throws SQLException;    
    
    //Decisão de arquitetura de projeto
    public List<String> buscarTiposItem() throws SQLException;
}
