/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao.h2;

import dao.ITransacaoDAO;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import model.Comprador;
import model.Transacao;
import model.Vendedor;
import util.factory.connection.DatabaseConnectionFactory;

/**
 *
 * @author berna
 */
public class TransacaoDAOH2 implements ITransacaoDAO {

    @Override
    public void criar(Transacao transacao) throws SQLException {
        String sql = "INSERT INTO transacoes (idC, dataInicio, dataTermino, comentarioVendedor, comentarioComprador, idPerfilVendedor, idPerfilComprador) "
                   + "VALUES (?, ?, ?, ?, ?, ?, ?)";
        
        try (Connection conn = DatabaseConnectionFactory.getDatabaseConnectionFactory();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, transacao.getIdC());
            pstmt.setString(2, transacao.getDataInicio());
            pstmt.setString(3, transacao.getDataTermino());
            pstmt.setString(4, transacao.getComentarioVendedor());
            pstmt.setString(5, transacao.getComentarioComprador());
            System.out.println(transacao.getVendedor().getId());
            pstmt.setInt(6, transacao.getVendedor().getId());
            pstmt.setInt(7, transacao.getComprador().getId());
            
            pstmt.executeUpdate();
        }
    }

    @Override
    public Optional<Transacao> buscaPorId(Integer id) throws SQLException {
        String sql = "SELECT t.*, " +
                    "v.idPerfilVendedor, v.sistemId AS vendedor_sistemId, " +
                    "c.idPerfilComprador, c.sistemId AS comprador_sistemId " +
                    "FROM transacoes t " +
                    "LEFT JOIN vendedores v ON t.idPerfilVendedor = v.idPerfilVendedor " +
                    "LEFT JOIN compradores c ON t.idPerfilComprador = c.idPerfilComprador " +
                    "WHERE t.idTransacao = ?";
        
        try (Connection conn = DatabaseConnectionFactory.getDatabaseConnectionFactory();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, id);

            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    Vendedor vendedor = new Vendedor(
                        rs.getInt("idPerfilVendedor"),
                        rs.getString("vendedor_sistemId"),
                        null // Reputação pode ser carregada separadamente se necessário
                    );
                    
                    Comprador comprador = new Comprador(
                        rs.getInt("idPerfilComprador"),
                        rs.getString("comprador_sistemId"),
                        null // Reputação pode ser carregada separadamente se necessário
                    );
                    
                    Transacao transacao = new Transacao(
                        rs.getInt("idTransacao"),
                        rs.getString("idC"),
                        rs.getString("dataInicio"),
                        rs.getString("dataTermino"),
                        rs.getString("comentarioVendedor"),
                        rs.getString("comentarioComprador"),
                        vendedor,
                        comprador
                    );
                    
                    return Optional.of(transacao);
                }
            }
        }
        return Optional.empty();
    }

    @Override
    public List<Transacao> buscaTodos() throws SQLException {
        List<Transacao> transacoes = new ArrayList<>();
        String sql = "SELECT t.*, " +
                    "v.idPerfilVendedor, v.sistemId AS vendedor_sistemId, " +
                    "c.idPerfilComprador, c.sistemId AS comprador_sistemId " +
                    "FROM transacoes t " +
                    "LEFT JOIN vendedores v ON t.idPerfilVendedor = v.idPerfilVendedor " +
                    "LEFT JOIN compradores c ON t.idPerfilComprador = c.idPerfilComprador";
        
        try (Connection conn = DatabaseConnectionFactory.getDatabaseConnectionFactory();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                Vendedor vendedor = new Vendedor(
                    rs.getInt("idPerfilVendedor"),
                    rs.getString("vendedor_sistemId"),
                    null
                );
                
                Comprador comprador = new Comprador(
                    rs.getInt("idPerfilComprador"),
                    rs.getString("comprador_sistemId"),
                    null
                );
                
                Transacao transacao = new Transacao(
                    rs.getInt("idTransacao"),
                    rs.getString("idC"),
                    rs.getString("dataInicio"),
                    rs.getString("dataTermino"),
                    rs.getString("comentarioVendedor"),
                    rs.getString("comentarioComprador"),
                    vendedor,
                    comprador
                );
                
                transacoes.add(transacao);
            }
        }
        return transacoes;
    }

    @Override
    public void atualizar(Transacao transacao) throws SQLException {
        String sql = "UPDATE transacoes SET idC = ?, dataInicio = ?, dataTermino = ?, " +
                   "comentarioVendedor = ?, comentarioComprador = ?, " +
                   "idPerfilVendedor = ?, idPerfilComprador = ? " +
                   "WHERE idTransacao = ?";
        
        try (Connection conn = DatabaseConnectionFactory.getDatabaseConnectionFactory();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, transacao.getIdC());
            pstmt.setString(2, transacao.getDataInicio());
            pstmt.setString(3, transacao.getDataTermino());
            pstmt.setString(4, transacao.getComentarioVendedor());
            pstmt.setString(5, transacao.getComentarioComprador());
            pstmt.setInt(6, transacao.getVendedor().getId());
            pstmt.setInt(7, transacao.getComprador().getId());
            pstmt.setInt(8, transacao.getId());
            
            pstmt.executeUpdate();
        }
    }

    @Override
    public void deletar(Integer id) throws SQLException {
        String sql = "DELETE FROM transacoes WHERE idTransacao = ?";
        
        try (Connection conn = DatabaseConnectionFactory.getDatabaseConnectionFactory();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, id);
            pstmt.executeUpdate();
        }
    }
    
    @Override
    public List<Transacao> buscaPorVendedor(Integer idVendedor) throws SQLException {
        List<Transacao> transacoes = new ArrayList<>();
        String sql = "SELECT t.*, " +
                    "v.idPerfilVendedor, v.sistemId AS vendedor_sistemId, " +
                    "c.idPerfilComprador, c.sistemId AS comprador_sistemId " +
                    "FROM transacoes t " +
                    "LEFT JOIN vendedores v ON t.idPerfilVendedor = v.idPerfilVendedor " +
                    "LEFT JOIN compradores c ON t.idPerfilComprador = c.idPerfilComprador " +
                    "WHERE t.idPerfilVendedor = ?";
        
        try (Connection conn = DatabaseConnectionFactory.getDatabaseConnectionFactory();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, idVendedor);

            try (ResultSet rs = pstmt.executeQuery()) {
                while (rs.next()) {
                    Vendedor vendedor = new Vendedor(
                        rs.getInt("idPerfilVendedor"),
                        rs.getString("vendedor_sistemId"),
                        null
                    );
                    
                    Comprador comprador = new Comprador(
                        rs.getInt("idPerfilComprador"),
                        rs.getString("comprador_sistemId"),
                        null
                    );
                    
                    Transacao transacao = new Transacao(
                        rs.getInt("idTransacao"),
                        rs.getString("idC"),
                        rs.getString("dataInicio"),
                        rs.getString("dataTermino"),
                        rs.getString("comentarioVendedor"),
                        rs.getString("comentarioComprador"),
                        vendedor,
                        comprador
                    );
                    
                    transacoes.add(transacao);
                }
            }
        }
        return transacoes;
    }
    
    @Override
    public List<Transacao> buscaPorComprador(Integer idComprador) throws SQLException {
        List<Transacao> transacoes = new ArrayList<>();
        String sql = "SELECT t.*, " +
                    "v.idPerfilVendedor, v.sistemId AS vendedor_sistemId, " +
                    "c.idPerfilComprador, c.sistemId AS comprador_sistemId " +
                    "FROM transacoes t " +
                    "LEFT JOIN vendedores v ON t.idPerfilVendedor = v.idPerfilVendedor " +
                    "LEFT JOIN compradores c ON t.idPerfilComprador = c.idPerfilComprador " +
                    "WHERE t.idPerfilComprador = ?";
        
        try (Connection conn = DatabaseConnectionFactory.getDatabaseConnectionFactory();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, idComprador);

            try (ResultSet rs = pstmt.executeQuery()) {
                while (rs.next()) {
                    Vendedor vendedor = new Vendedor(
                        rs.getInt("idPerfilVendedor"),
                        rs.getString("vendedor_sistemId"),
                        null
                    );
                    
                    Comprador comprador = new Comprador(
                        rs.getInt("idPerfilComprador"),
                        rs.getString("comprador_sistemId"),
                        null
                    );
                    
                    Transacao transacao = new Transacao(
                        rs.getInt("idTransacao"),
                        rs.getString("idC"),
                        rs.getString("dataInicio"),
                        rs.getString("dataTermino"),
                        rs.getString("comentarioVendedor"),
                        rs.getString("comentarioComprador"),
                        vendedor,
                        comprador
                    );
                    
                    transacoes.add(transacao);
                }
            }
        }
        return transacoes;
    }
}