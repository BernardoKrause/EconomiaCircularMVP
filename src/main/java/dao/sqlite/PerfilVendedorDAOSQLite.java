package dao.sqlite;

import dao.IPerfilDAO;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import model.Perfil;
import model.Reputacao;
import model.Vendedor;
import util.factory.connection.DatabaseConnectionFactory;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author berna
 */
public class PerfilVendedorDAOSQLite implements IPerfilDAO {
    
    @Override
    public void criar(Perfil vendedor) throws SQLException {
        String sql = "INSERT INTO vendedores (sistemId, idReputacao) "
                   + "VALUES (?, ?)";
        try (Connection conn = DatabaseConnectionFactory.getDatabaseConnectionFactory();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, vendedor.getSistemId());
            pstmt.setInt(2, vendedor.getReputacao().getId());
            pstmt.executeUpdate();
        }
    }
    
    @Override
    public Optional<Perfil> buscaPorIdUsuario (Integer id) throws SQLException {
        String sql = """
                SELECT v.*, 
                    r.estrelas AS reputacao_estrelas,
                    r.beneficioClimatico AS reputacao_beneficio,
                    r.nivel AS reputacao_nivel
                    FROM vendedores v
                    LEFT JOIN reputacoes r ON v.idReputacao = r.idReputacao 
                    LEFT JOIN usuarios u ON u.idPerfilVendedor = v.idPerfilVendedor
                    WHERE u.idUsuario = ?""";
        try (Connection conn = DatabaseConnectionFactory.getDatabaseConnectionFactory();
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
    public List<Perfil> buscaTodos() throws SQLException {
        List<Vendedor> vendedores = new ArrayList<>();
        String sql = """
            SELECT v.*, 
                   r.estrelas AS reputacao_estrelas,
                   r.beneficioClimatico AS reputacao_beneficio,
                   r.nivel AS reputacao_nivel
            FROM vendedores v
            LEFT JOIN reputacoes r ON v.idReputacao = r.idReputacao
            """;

        try (Connection conn = DatabaseConnectionFactory.getDatabaseConnectionFactory();
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
        return vendedores.stream()
            .map(vendedor -> (Perfil) vendedor)
            .collect(Collectors.toList());
    }
    
    @Override
    public void atualizar(Perfil perfil) throws SQLException {
        String sql = "UPDATE vendedores SET sistemId = ?, idReputacao = ?"
                   + "WHERE idPerfil = ?";
        try (Connection conn = DatabaseConnectionFactory.getDatabaseConnectionFactory();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, perfil.getSistemId());
            pstmt.setInt(2, perfil.getReputacao().getId());
            pstmt.setInt(3, perfil.getId());
            pstmt.executeUpdate();

        } 
    }

    @Override
    public void deletar(Integer id) throws SQLException {
        String sql = "DELETE FROM vendedores WHERE idPerfilVendedor = ?";
        try (Connection conn = DatabaseConnectionFactory.getDatabaseConnectionFactory();
            PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, id);
            pstmt.executeUpdate();
        }
    }
    
    @Override
    public Optional<Perfil> buscaPorId(Integer id) throws SQLException {
        String sql = """
            SELECT v.*, 
                   r.estrelas AS reputacao_estrelas,
                   r.beneficioClimatico AS reputacao_beneficio,
                   r.nivel AS reputacao_nivel
            FROM vendedores v
            LEFT JOIN reputacoes r ON v.idReputacao = r.idReputacao
            WHERE v.idPerfilVendedor = ?
            """;
        
        try (Connection conn = DatabaseConnectionFactory.getDatabaseConnectionFactory();
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

                    Vendedor vendedor = new Vendedor(
                        rs.getInt("idPerfilVendedor"),
                        rs.getString("sistemId"),
                        reputacao
                    );
                    
                    return Optional.of(vendedor);
                }
            }
        }
        return Optional.empty();
    }
    
}
