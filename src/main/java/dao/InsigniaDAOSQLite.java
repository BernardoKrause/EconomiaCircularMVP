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
import model.Insignia;
import util.DatabaseConnection;

/**
 *
 * @author berna
 */
public class InsigniaDAOSQLite implements InsigniaDAO {

    @Override
    public void criar(Insignia insignia) throws SQLException {
        String sql = "INSERT INTO insignias (nome, estrelaBonus)"
                   + "VALUES (?, ?)";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, insignia.getNome());
            pstmt.setDouble(2, insignia.getEstrelaBonus());
            pstmt.executeUpdate();
        }
    }

    @Override
    public List<Insignia> buscaTodos() throws SQLException {
        List<Insignia> insignias = new ArrayList<>();
        String sql = """
            SELECT * FROM insignias
            """;

        try (Connection conn = DatabaseConnection.getConnection();
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql)) {
        
            while (rs.next()) {
                insignias.add(new Insignia(
                    rs.getString("nome"),
                    rs.getDouble("estrelaBonus")
                ));
            }
        }
        return insignias;
    }

    @Override
    public Optional<Insignia> buscaPorId(Integer id) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void atualizar(Insignia insignia) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void deletar(Integer idInsignia) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
    
}
