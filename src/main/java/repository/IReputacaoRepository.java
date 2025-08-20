/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package repository;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;
import model.Perfil;
import model.Reputacao;

/**
 *
 * @author caiof
 */
public interface IReputacaoRepository {    
    public void salvarReputacao(Reputacao reputacao) throws SQLException;
    public Optional<Reputacao> getReputacao(Perfil perfil) throws SQLException;
    public Optional<List<Reputacao>> getTodos() throws SQLException;
    public void atualizarReputacao(Reputacao reputacao) throws SQLException;
    public void deletarReputacao(Integer id) throws SQLException;
}
