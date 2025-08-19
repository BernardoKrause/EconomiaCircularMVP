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
import model.Defeito;
import util.factory.connection.DatabaseConnectionFactory;

/**
 *
 * @author berna
 */
public class DefeitoDAOSQLite implements IDefeitoDAO {

    @Override
    public void criar(Defeito defeito) throws SQLException {
        String sql = "INSERT INTO defeitos (descricao, percentualDesconto)"
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
        String sql = """
                     SELECT * FROM defeitos
                     """;

        try (Connection conn = DatabaseConnectionFactory.getDatabaseConnectionFactory();
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql)) {
        
            while (rs.next()) {
                defeitos.add(new Defeito(
                    rs.getString("descricao"),
                    rs.getInt("percentualDesconto")
                ));
            }
        }
        return defeitos;
    }
    
}
