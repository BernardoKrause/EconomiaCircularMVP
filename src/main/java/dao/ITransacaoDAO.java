package dao;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;
import model.Transacao;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */

/**
 *
 * @author berna
 */
public interface ITransacaoDAO {
    public void criar(Transacao transacao) throws SQLException;
    public Optional<Transacao> buscaPorId(Integer id) throws SQLException;
    public List<Transacao> buscaTodos() throws SQLException;
    public void atualizar(Transacao transacao) throws SQLException;
    public void deletar(Integer id) throws SQLException;
    public List<Transacao> buscaPorVendedor(Integer idVendedor) throws SQLException;
    public List<Transacao> buscaPorComprador(Integer idComprador) throws SQLException;
}
