/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao.h2;

import dao.ICondutaDAO;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import model.Conduta;
import model.Reputacao;
import util.factory.connection.DatabaseConnectionFactory;

/**
 *
 * @author berna
 */
public class CondutaDAOH2 implements ICondutaDAO {

    @Override
    public void criar(Reputacao reputacao, Conduta conduta) throws SQLException {
        String sql = "INSERT INTO condutas (tipoConduta, descricao, tipoPerfil, valorEstrelas, idReputacao)"
                   + "VALUES (?, ?, ?, ?, ?)";
        try (Connection conn = DatabaseConnectionFactory.getDatabaseConnectionFactory();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, conduta.getTipo());
            pstmt.setString(2, conduta.getDescricao());
            pstmt.setString(3, conduta.getTipoPerfil());
            pstmt.setDouble(4, conduta.getValorEstrela());
            pstmt.setInt(5, reputacao.getId());
            pstmt.executeUpdate();
        }
    }
    
    @Override
    public List<Conduta> buscaTodos() throws SQLException {
        List<Conduta> condutas = new ArrayList<>();
        String sql = """
                     SELECT * FROM condutas
                     """;

        try (Connection conn = DatabaseConnectionFactory.getDatabaseConnectionFactory();
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql)) {
        
            while (rs.next()) {
                condutas.add(new Conduta(
                    rs.getInt("idConduta"),
                    rs.getString("tipoConduta"),
                    rs.getString("descricao"),
                    rs.getString("tipoPerfil"),
                    rs.getDouble("valorEstrelas")
                ));
            }
        }
        return condutas;
    }

    @Override
    public List<Conduta> buscaPorTipo(Integer idReputacao, String tipo) throws SQLException {
        List<Conduta> condutas = new ArrayList<>();
        String sql = """
            SELECT *
            FROM condutas
            WHERE tipoConduta = ? AND idReputacao = ?
            """;

        try (Connection conn = DatabaseConnectionFactory.getDatabaseConnectionFactory();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, tipo);
            pstmt.setInt(2, idReputacao);

            try (ResultSet rs = pstmt.executeQuery()) {
                while (rs.next()) {
                    condutas.add(new Conduta(
                        rs.getInt("idConduta"),
                        rs.getString("tipoConduta"),
                        rs.getString("descricao"),
                        rs.getString("tipoPerfil"),
                        rs.getDouble("valorEstrelas")
                    ));
                }
                
                return condutas;
            }
        }
    }

    @Override
    public List<Conduta> buscaPorReputacao(Reputacao reputacao) throws SQLException {
        List<Conduta> condutas = new ArrayList<>();
        String sql = """
            SELECT *
            FROM condutas
            WHERE idReputacao = ?
            """;

        try (Connection conn = DatabaseConnectionFactory.getDatabaseConnectionFactory();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, reputacao.getId());

            try (ResultSet rs = pstmt.executeQuery()) {
                while (rs.next()) {
                    condutas.add(new Conduta(
                        rs.getInt("idConduta"),
                        rs.getString("tipoConduta"),
                        rs.getString("descricao"),
                        rs.getString("tipoPerfil"),
                        rs.getDouble("valorEstrelas")
                    ));
                }
                
                return condutas;
            }
        }
    }
}