/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao.h2;

import dao.IDenunciaDAO;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import model.Comprador;
import model.Denuncia;
import model.Transacao;
import model.Vendedor;
import util.factory.connection.DatabaseConnectionFactory;

/**
 *
 * @author berna
 */
public class DenunciaDAOH2 implements IDenunciaDAO {

    @Override
    public void criar(Denuncia denuncia) throws SQLException {
        String sql = "INSERT INTO denuncias (idC, descricao, status, idPerfilComprador, idPerfilVendedor, idTransacao)"
                   + "VALUES (?, ?, ?, ?, ?, ?)";
        try (Connection conn = DatabaseConnectionFactory.getDatabaseConnectionFactory();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, denuncia.getIdC());
            pstmt.setString(2, denuncia.getDescricao());
            pstmt.setString(3, denuncia.getStatus());
            pstmt.setInt(4, denuncia.getComprador().getId());
            pstmt.setInt(5, denuncia.getVendedor().getId());
            pstmt.setInt(6, denuncia.getTransacao().getId());
            
            pstmt.executeUpdate();
        }
    }

    @Override
    public List<Denuncia> buscaTodos() throws SQLException {
        List<Denuncia> denuncias = new ArrayList<>();
        String sql = """
            SELECT d.*, 
                   c.sistemId AS comprador_sistemId,
                   v.sistemId AS vendedor_sistemId,
                   t.*
            FROM denuncias d
            LEFT JOIN compradores c ON d.idPerfilComprador = c.idPerfilComprador
            LEFT JOIN vendedores v ON d.idPerfilVendedor = v.idPerfilVendedor
            LEFT JOIN transacoes t ON d.idTransacao = t.idTransacao
            """;

        try (Connection conn = DatabaseConnectionFactory.getDatabaseConnectionFactory();
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql)) {
        
            while (rs.next()) {
                Comprador comprador = new Comprador(
                    rs.getInt("idPerfilComprador"),
                    rs.getString("comprador_sistemId")
                );

                Vendedor vendedor = new Vendedor(
                    rs.getInt("idPerfilVendedor"),
                    rs.getString("vendedor_sistemId")
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

                Denuncia denuncia = new Denuncia(
                    rs.getInt("idDenuncia"),
                    rs.getString("idC"),
                    rs.getString("descricao"),
                    rs.getString("status"),
                    comprador,
                    vendedor,
                    transacao
                );
                
                denuncias.add(denuncia);
            }
        }
        return denuncias;
    }

    @Override
    public Optional<Denuncia> buscaPorId(Integer id) throws SQLException {
        String sql = """
            SELECT d.*, 
                   c.sistemId AS comprador_sistemId,
                   v.sistemId AS vendedor_sistemId,
                   t.*
            FROM denuncias d
            LEFT JOIN compradores c ON d.idPerfilComprador = c.idPerfilComprador
            LEFT JOIN vendedores v ON d.idPerfilVendedor = v.idPerfilVendedor
            LEFT JOIN transacoes t ON d.idTransacao = t.idTransacao
            WHERE d.idDenuncia = ?
            """;
        
        try (Connection conn = DatabaseConnectionFactory.getDatabaseConnectionFactory();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, id);

            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    Comprador comprador = new Comprador(
                    rs.getInt("idPerfilComprador"),
                    rs.getString("comprador_sistemId")
                    );

                    Vendedor vendedor = new Vendedor(
                        rs.getInt("idPerfilVendedor"),
                        rs.getString("vendedor_sistemId")
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

                    Denuncia denuncia = new Denuncia(
                        rs.getInt("idDenuncia"),
                        rs.getString("idC"),
                        rs.getString("descricao"),
                        rs.getString("status"),
                        comprador,
                        vendedor,
                        transacao
                    );
                    
                    return Optional.of(denuncia);
                }
            }
        }
        return Optional.empty();
    }

    @Override
    public void atualizar(Denuncia denuncia) throws SQLException {
        String sql = "UPDATE denuncias SET idC = ?, descricao = ?, status = ?, "
                   + "idPerfilComprador = ?, idPerfilVendedor = ?, idTransacao = ? "
                   + "WHERE idDenuncia = ?";
        try (Connection conn = DatabaseConnectionFactory.getDatabaseConnectionFactory();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, denuncia.getIdC());
            pstmt.setString(2, denuncia.getDescricao());
            pstmt.setString(3, denuncia.getStatus());
            pstmt.setInt(4, denuncia.getComprador().getId());
            pstmt.setInt(5, denuncia.getVendedor().getId());
            pstmt.setInt(6, denuncia.getTransacao().getId());
            pstmt.setInt(7, denuncia.getId());
            
            pstmt.executeUpdate();
        } 
    }

    @Override
    public void deletar(Integer id) throws SQLException {
       String sql = "DELETE FROM denuncias WHERE idDenuncia = ?";
       try (Connection conn = DatabaseConnectionFactory.getDatabaseConnectionFactory();
           PreparedStatement pstmt = conn.prepareStatement(sql)) {

           pstmt.setInt(1, id);
           pstmt.executeUpdate();
       }
    }
}