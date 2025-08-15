package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import model.Item;
import model.Usuario;
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
        String sql = "INSERT INTO vendedores (sistemId, idReputacao) "
                   + "VALUES (?, ?)";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, vendedor.getId());
            pstmt.setInt(2, vendedor.getReputacao());
            pstmt.executeUpdate();
        }
    }

    @Override
    public List<Vendedor> buscaTodos() throws SQLException {
        List<Vendedor> vendedores = new ArrayList<>();
        String sql = "SELECT * FROM vendedores";
        try (Connection conn = DatabaseConnection.getConnection();
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                vendedores.add(new Vendedor(
                    rs.getString("sistemId")
                ));
            }

        }
        return vendedores;
    }

    @Override
    public void atualizar(Vendedor vendedor) throws SQLException {
        String sql = "UPDATE vendedores SET idReputacao = ?"
                   + "WHERE sistemId = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, vendedor.getReputacao());
            pstmt.setString(2, vendedor.getId());
            pstmt.executeUpdate();

        } 
    }

    @Override
    public void deletar(String id) throws SQLException {
        String sql = "DELETE FROM vendedores WHERE sistemId = ?";
        try (Connection conn = DatabaseConnection.getConnection();
            PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, id);
            pstmt.executeUpdate();
        }
    }
    
}
