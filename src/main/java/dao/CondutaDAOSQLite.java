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
import model.Conduta;
import util.factory.connection.DatabaseConnectionFactory;

/**
 *
 * @author berna
 */
public class CondutaDAOSQLite implements ICondutaDAO {

    @Override
    public void criar(Conduta conduta) throws SQLException {
        String sql = "INSERT INTO condutas (tipoConduta, descricao, tipoPerfil, valorEstrelas)"
                   + "VALUES (?, ?, ?, ?)";
        try (Connection conn = DatabaseConnectionFactory.getDatabaseConnectionFactory();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, conduta.getTipo());
            pstmt.setString(2, conduta.getDescricao());
            pstmt.setString(3, conduta.getTipoPerfil());
            pstmt.setDouble(4, conduta.getValorEstrela());
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
    public Optional<Conduta> buscaPorTipo(String tipo) throws SQLException {
        String sql = """
            SELECT *
            FROM condutas
            WHERE tipoConduta = ?
            """;
        
        try (Connection conn = DatabaseConnectionFactory.getDatabaseConnectionFactory();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, tipo);

            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    
                    return Optional.of(new Conduta(
                        rs.getInt("idConduta"),
                        rs.getString("tipoConduta"),
                        rs.getString("descricao"),
                        rs.getString("tipoPerfil"),
                        rs.getDouble("valorEstrelas")
                    ));
                }
            }
        }
        return Optional.empty();
    }
    
}
