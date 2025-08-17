package dao;


import model.SolicitacaoPerfil;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */

/**
 *
 * @author berna
 */
public interface IPerfilSolicitacaoDAO {
    public void criar(SolicitacaoPerfil solicitacaoPerfil) throws SQLException;
    public List<SolicitacaoPerfil> buscaTodos() throws SQLException;
    public List<SolicitacaoPerfil> buscaEmAguardo() throws SQLException ;
    public void atualizar(SolicitacaoPerfil solicitacaoPerfil) throws SQLException;
    public void deletar(Integer id) throws SQLException;
}
