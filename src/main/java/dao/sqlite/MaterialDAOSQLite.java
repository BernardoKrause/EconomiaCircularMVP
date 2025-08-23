/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao.sqlite;

import dao.IMaterialDAO;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import model.Material;
import util.factory.connection.DatabaseConnectionFactory;

/**
 *
 * @author berna
 */
public class MaterialDAOSQLite implements IMaterialDAO {

    @Override
    public Optional<Material> buscaPorId(Integer id) throws SQLException {
        String sql = "SELECT m.*, im.percentual FROM materiais m"
                    + "JOIN item_materiais im ON im.idMaterial = m.idMaterial"
                    + " WHERE m.idMaterial = ?";
        try (Connection conn = DatabaseConnectionFactory.getDatabaseConnectionFactory();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, id);

            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    return Optional.of(new Material(
                        rs.getInt("idMaterial"),
                        rs.getString("descricao"),
                        rs.getDouble("fatorEmissao"),
                        rs.getDouble("percentual")
                    ));
                }
            }

        }
        return Optional.empty();
    }

    @Override
    public List<Material> buscaTodos() throws SQLException {
        List<Material> materiais = new ArrayList<>();
        String sql = "SELECT m.*, im.percentual FROM materiais m"
                    + "JOIN item_materiais im ON im.idMaterial = m.idMaterial";
        try (Connection conn = DatabaseConnectionFactory.getDatabaseConnectionFactory();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {

                materiais.add(new Material(
                        rs.getInt("idMaterial"),
                        rs.getString("descricao"),
                        rs.getDouble("fatorEmissao"),
                        rs.getDouble("percentual")
                    ));
            }

        }
        return materiais;
    }
    
    @Override
    public Optional<Double> buscaFatorEmissao(String nomeMaterial) throws SQLException {
        String sql = "SELECT fatorEmissao FROM materiais m"
                    + " WHERE descricao = ?";
        try (Connection conn = DatabaseConnectionFactory.getDatabaseConnectionFactory();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, nomeMaterial);

            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    return Optional.of(rs.getDouble("fatorEmissao"));
                }
            }

        }
        return Optional.empty();
    }
    
    @Override
    public List<Material> buscaPorTipoItem(String tipo) throws SQLException {
        List<Material> materiais = new ArrayList<>();
        String sql = "SELECT m.*, im.percentual FROM materiais m "
                    + "JOIN item_materiais im ON im.idMaterial = m.idMaterial "
                    + "JOIN itens i ON i.idItem = im.idItem "
                    + "WHERE i.tipo = ?";

        try (Connection conn = DatabaseConnectionFactory.getDatabaseConnectionFactory();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, tipo);

            try (ResultSet rs = pstmt.executeQuery()) {
                while (rs.next()) {
                    materiais.add(new Material(
                        rs.getInt("idMaterial"),
                        rs.getString("descricao"),
                        rs.getDouble("fatorEmissao"),
                        rs.getDouble("percentual")
                    ));
                }
            }
        }
        return materiais;
    }
}
