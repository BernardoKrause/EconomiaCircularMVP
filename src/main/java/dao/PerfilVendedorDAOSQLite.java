package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import model.Reputacao;
import model.Vendedor;
import util.DatabaseConnection;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author berna
 */
public class PerfilVendedorDAOSQLite implements PerfilVendedorDAO {
    
    @Override
    public void criar(Vendedor vendedor) throws SQLException {
        String sql = "INSERT INTO vendedores (idPerfilVendedor, idReputacao) "
                   + "VALUES (?, ?)";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, vendedor.getId());
            pstmt.setInt(2, vendedor.getReputacao().getId());
            pstmt.executeUpdate();
        }
    }
    
    @Override
    public Optional<Vendedor> buscaPorIdUsuario (Integer id) throws SQLException {
        String sql = """
                SELECT v.*, 
                    r.estrelas AS reputacao_estrelas,
                    r.beneficioClimatico AS reputacao_beneficio,
                    r.nivel AS reputacao_nivel
                    FROM vendedores v
                    LEFT JOIN reputacoes r ON c.idReputacao = r.idReputacao 
                    LEFT JOIN usuarios u ON u.idPerfilVendedor = c.idPerfilVendedor
                    WHERE u.idUsuario = ?""";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, id);

            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {                   
                    Reputacao reputacao = new Reputacao(
                        rs.getDouble("reputacao_estrelas"),
                        rs.getDouble("reputacao_beneficio"),
                        rs.getString("reputacao_nivel")
                    );
                    reputacao.setIdReputacao(rs.getInt("idReputacao"));
                    
                    return Optional.of(new Vendedor(
                        rs.getInt("idPerfilVendedor"),
                        rs.getString("sistemId"),
                        reputacao
                    ));
                }
            }

        }
        return Optional.empty();
    }
    
    @Override
    public List<Vendedor> buscaTodos() throws SQLException {
        List<Vendedor> vendedores = new ArrayList<>();
        String sql = """
            SELECT v.*, 
                   r.estrelas AS reputacao_estrelas,
                   r.beneficioClimatico AS reputacao_beneficio,
                   r.nivel AS reputacao_nivel
            FROM vendedores v
            LEFT JOIN reputacoes r ON c.idReputacao = r.idReputacao
            """;

        try (Connection conn = DatabaseConnection.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                Reputacao reputacao = new Reputacao(
                    rs.getDouble("reputacao_estrelas"),
                    rs.getDouble("reputacao_beneficio"),
                    rs.getString("reputacao_nivel")
                );
                reputacao.setIdReputacao(rs.getInt("idReputacao"));

                vendedores.add(new Vendedor(
                    rs.getInt("idPerfilVendedor"),
                    rs.getString("sistemId"),
                    reputacao
                ));
            }
        }
        return vendedores;
    }

    @Override
    public void deletar(Integer id) throws SQLException {
        String sql = "DELETE FROM vendedores WHERE idPerfilVendedor = ?";
        try (Connection conn = DatabaseConnection.getConnection();
            PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, id);
            pstmt.executeUpdate();
        }
    }
    
}
