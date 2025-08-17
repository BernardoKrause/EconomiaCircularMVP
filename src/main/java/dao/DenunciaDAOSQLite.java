/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

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
import model.Vendedor;
import util.factory.connection.DatabaseConnectionFactory;

/**
 *
 * @author berna
 */
public class DenunciaDAOSQLite implements IDenunciaDAO {

    @Override
    public void criar(Denuncia denuncia) throws SQLException {
        String sql = "INSERT INTO denuncias (idC, descricao, status, idPerfilComprador, idPerfilVendedor)"
                   + "VALUES (?, ?, ?, ?, ?)";
        try (Connection conn = DatabaseConnectionFactory.getDatabaseConnectionFactory();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, denuncia.getIdC());
            pstmt.setString(2, denuncia.getDescricao());
            pstmt.setString(3, denuncia.getStatus());
            pstmt.setInt(4, denuncia.getComprador().getId());
            pstmt.setInt(5, denuncia.getVendedor().getId());
            pstmt.executeUpdate();
        }
    }

    @Override
    public List<Denuncia> buscaTodos() throws SQLException {
        List<Denuncia> denuncias = new ArrayList<>();
        String sql = """
            SELECT d.*, 
                   c.sistemId AS comprador_sistemId,
                   v.sistemId AS vendedor_sistemId
            FROM denuncias d
            LEFT JOIN compradores c ON d.idPerfilComprador = c.idPerfilComprador
            LEFT JOIN vendedores v ON d.idPerfilVendedor = v.idPerfilVendedor
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

                denuncias.add(new Denuncia(
                    rs.getInt("idDenuncia"),
                    rs.getString("idC"),
                    rs.getString("descricao"),
                    rs.getString("status"),
                    comprador,
                    vendedor
                ));
            }
        }
        return denuncias;
    }

    @Override
    public Optional<Denuncia> buscaPorId(Integer id) throws SQLException {
        String sql = """
            SELECT d.*, 
                   c.sistemId AS comprador_sistemId,
                   v.sistemId AS vendedor_sistemId
            FROM denuncias d
            LEFT JOIN compradores c ON d.idPerfilComprador = c.idPerfilComprador
            LEFT JOIN vendedores v ON d.idPerfilVendedor = v.idPerfilVendedor
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

                    Denuncia denuncia = new Denuncia(
                        rs.getInt("idDenuncia"),
                        rs.getString("idC"),
                        rs.getString("descricao"),
                        rs.getString("status"),
                        comprador,
                        vendedor
                    );
                    
                    return Optional.of(denuncia);
                }
            }
        }
        return Optional.empty();
    }

    @Override
    public void atualizar(Denuncia denuncia) throws SQLException {
        String sql = "UPDATE denuncias SET idC = ?, descricao = ?, status = ?, idPerfilComprador = ?, idPerfilVendedor = ? "
                   + "WHERE idDenuncia = ?";
        try (Connection conn = DatabaseConnectionFactory.getDatabaseConnectionFactory();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, denuncia.getIdC());
            pstmt.setString(2, denuncia.getDescricao());
            pstmt.setString(3, denuncia.getStatus());
            pstmt.setInt(4, denuncia.getComprador().getId());
            pstmt.setInt(5, denuncia.getVendedor().getId());
            pstmt.setInt(6, denuncia.getId());
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
