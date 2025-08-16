package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import model.Comprador;
import util.DatabaseConnection;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author berna
 */
public class PerfilCompradorDAOSQLite implements PerfilCompradorDAO {
    
    @Override
    public void criar(Comprador comprador) throws SQLException {
        String sql = "INSERT INTO compradores (idPerfilComprador, idReputacao) "
                   + "VALUES (?, ?)";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, comprador.getId());
            pstmt.setInt(2, comprador.getReputacao().getId());
            pstmt.executeUpdate();
        }
    }

    @Override
    public List<Comprador> buscaTodos() throws SQLException {
        List<Comprador> compradores = new ArrayList<>();
        String sql = "SELECT * FROM compradores";
        try (Connection conn = DatabaseConnection.getConnection();
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                compradores.add(new Comprador(
                    rs.getInt("idPerfilComprador"),
                    rs.getString("sistemId")
                ));
            }

        }
        return compradores;
    }

    @Override
    public void deletar(Integer id) throws SQLException {
        String sql = "DELETE FROM compradores WHERE idPerfilComprador = ?";
        try (Connection conn = DatabaseConnection.getConnection();
            PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, id);
            pstmt.executeUpdate();
        }
    }
}
