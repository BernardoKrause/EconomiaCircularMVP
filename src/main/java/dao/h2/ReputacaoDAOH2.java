/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao.h2;

import dao.IReputacaoDAO;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import model.Perfil;
import model.Reputacao;
import util.factory.connection.DatabaseConnectionFactory;

/**
 *
 * @author berna
 */
public class ReputacaoDAOH2 implements IReputacaoDAO {

    @Override
    public void criar(Reputacao reputacao) throws SQLException {
        String sql = "INSERT INTO reputacoes (estrelas, beneficioClimatico, nivel) "
                + "VALUES (?, ?, ?)";

        try (Connection conn = DatabaseConnectionFactory.getDatabaseConnectionFactory();
             PreparedStatement pstmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            pstmt.setDouble(1, reputacao.getEstrelas());
            pstmt.setDouble(2, reputacao.getBeneficio());
            pstmt.setString(3, reputacao.getNivel());
            pstmt.executeUpdate();

            // Recuperar o ID gerado
            try (ResultSet generatedKeys = pstmt.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    int idGerado = generatedKeys.getInt(1);
                    reputacao.setIdReputacao(idGerado);
                } else {
                    throw new SQLException("Falha ao criar reputação, nenhum ID obtido.");
                }
            }
        }
    }

    @Override
    public Optional<Reputacao> buscaPorPerfil(Perfil perfil) throws SQLException {
        String sql = """
                    SELECT r.* FROM reputacoes r
                    LEFT JOIN vendedores v ON v.idReputacao = r.idReputacao
                    LEFT JOIN compradores c ON c.idReputacao = r.idReputacao
                    WHERE v.sistemId = ? OR c.sistemId = ?
                    """;
        try (Connection conn = DatabaseConnectionFactory.getDatabaseConnectionFactory();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, perfil.getSistemId());
            pstmt.setString(2, perfil.getSistemId());

            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    Reputacao reputacao = new Reputacao(
                            rs.getDouble("estrelas"),
                            rs.getDouble("beneficioClimatico"),
                            rs.getString("nivel")
                    );
                    reputacao.setIdReputacao(rs.getInt("idReputacao"));

                    return Optional.of(reputacao);
                }
            }

        }
        return Optional.empty();
    }

    @Override
    public List<Reputacao> buscaTodos() throws SQLException {
        List<Reputacao> reputacoes = new ArrayList<>();
        String sql = "SELECT * FROM reputacoes";

        try (Connection conn = DatabaseConnectionFactory.getDatabaseConnectionFactory();
             PreparedStatement pstmt = conn.prepareStatement(sql);
             ResultSet rs = pstmt.executeQuery()) {

            while (rs.next()) {
                Reputacao reputacao = new Reputacao(
                        rs.getDouble("estrelas"),
                        rs.getDouble("beneficioClimatico"),
                        rs.getString("nivel")
                );
                reputacao.setIdReputacao(rs.getInt("idReputacao"));
                reputacoes.add(reputacao);
            }
        }
        return reputacoes;
    }

    @Override
    public void atualizar(Reputacao reputacao) throws SQLException {
        String sql = "UPDATE reputacoes SET estrelas = ?, beneficioClimatico = ?, nivel = ? "
                + "WHERE idReputacao = ?";
        try (Connection conn = DatabaseConnectionFactory.getDatabaseConnectionFactory();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setDouble(1, reputacao.getEstrelas());
            pstmt.setDouble(2, reputacao.getBeneficio());
            pstmt.setString(3, reputacao.getNivel());
            pstmt.setInt(4, reputacao.getId());
            pstmt.executeUpdate();

        }
    }

    @Override
    public void deletar(Integer id) throws SQLException {
        String sql = "DELETE FROM reputacoes WHERE idReputacao = ?";
        try (Connection conn = DatabaseConnectionFactory.getDatabaseConnectionFactory();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, id);
            pstmt.executeUpdate();
        }
    }

}