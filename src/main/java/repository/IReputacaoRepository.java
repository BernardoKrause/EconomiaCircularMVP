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
    public void adicionarReputacao(Reputacao reputacao) throws SQLException;
    public Optional<Reputacao> buscarReputacao(Perfil perfil) throws SQLException;
    public List<Reputacao> buscarTodos() throws SQLException;
    public void atualizarReputacao(Reputacao reputacao) throws SQLException;
    public void deletarReputacao(Integer id) throws SQLException;
}
