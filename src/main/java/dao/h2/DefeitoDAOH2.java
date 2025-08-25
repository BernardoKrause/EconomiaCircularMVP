/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao.h2;

import dao.IDefeitoDAO;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import model.Defeito;
import util.factory.connection.DatabaseConnectionFactory;

/**
 *
 * @author berna
 */
public class DefeitoDAOH2 implements IDefeitoDAO {

    @Override
    public void criar(Defeito defeito) throws SQLException {
        String sql = "INSERT INTO defeitos (descricao, percentualDesconto) "
                   + "VALUES (?, ?)";
        
        try (Connection conn = DatabaseConnectionFactory.getDatabaseConnectionFactory();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, defeito.getDescricao());
            pstmt.setInt(2, defeito.getPercentualDesconto());
            pstmt.executeUpdate();
        }
    }

    @Override
    public List<Defeito> buscaTodos() throws SQLException {
        List<Defeito> defeitos = new ArrayList<>();
        String sql = "SELECT d.*, di.valorDesconto FROM defeitos d " +
                     "JOIN item_defeitos di ON d.idDefeito = di.idDefeito";

        try (Connection conn = DatabaseConnectionFactory.getDatabaseConnectionFactory();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                defeitos.add(new Defeito(
                    rs.getInt("idDefeito"),
                    rs.getString("descricao"),
                    rs.getInt("percentualDesconto"),
                    rs.getDouble("valorDesconto")
                ));
            }
        }
        return defeitos;
    }

    @Override
    public List<Defeito> buscaDefeitosItem(Integer idItem) throws SQLException {
        List<Defeito> defeitos = new ArrayList<>();
        String sql = "SELECT d.*, di.valorDesconto FROM defeitos d " +
                     "JOIN item_defeitos di ON d.idDefeito = di.idDefeito " +
                     "WHERE di.idItem = ?";

        try (Connection conn = DatabaseConnectionFactory.getDatabaseConnectionFactory();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, idItem);

            try (ResultSet rs = pstmt.executeQuery()) {
                while (rs.next()) {
                    defeitos.add(new Defeito(
                        rs.getInt("idDefeito"),
                        rs.getString("descricao"),
                        rs.getInt("percentualDesconto"),
                        rs.getDouble("valorDesconto")
                    ));
                }
            }
        }
        return defeitos;
    }
}