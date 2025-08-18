package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import model.Conduta;
import model.Reputacao;
import util.factory.connection.DatabaseConnectionFactory;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author berna
 */
public class ReputacaoDAOSQLite implements IReputacaoDAO {

    @Override
    public void criar(Reputacao reputacao) throws SQLException {
        String sql = "INSERT INTO reputacoes (estrelas, beneficioClimatico, nivel, idConduta) "
                   + "VALUES (?, ?, ?, ?)";
        try (Connection conn = DatabaseConnectionFactory.getDatabaseConnectionFactory();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setDouble(1, reputacao.getEstrelas());
            pstmt.setDouble(2, reputacao.getBeneficio());
            pstmt.setString(3, reputacao.getNivel());
            pstmt.executeUpdate();
        }
    }

    @Override
    public List<Reputacao> buscaTodos() throws SQLException {
    Map<Integer, Reputacao> reputacoesMap = new HashMap<>();
    String sql = """
        SELECT r.idReputacao, r.estrelas, r.beneficioClimatico, r.nivel,
               c.idConduta, c.tipoConduta, c.descricao, c.tipoPerfil, c.valorEstrelas
        FROM reputacoes r
        LEFT JOIN condutas c ON c.idConduta = r.idConduta
        ORDER BY r.idReputacao
        """;

    try (Connection conn = DatabaseConnectionFactory.getDatabaseConnectionFactory();
         Statement stmt = conn.createStatement();
         ResultSet rs = stmt.executeQuery(sql)) {

        while (rs.next()) {
            int idReputacao = rs.getInt("idReputacao");
            
            Reputacao reputacao = reputacoesMap.get(idReputacao);
            if (reputacao == null) {
                reputacao = new Reputacao(
                    rs.getDouble("estrelas"),
                    rs.getDouble("beneficioClimatico"),
                    rs.getString("nivel"),
                    new ArrayList<>()
                );
                reputacoesMap.put(idReputacao, reputacao);
            }
            
            if (rs.getObject("idConduta") != null) {
                Conduta conduta = new Conduta(
                    rs.getString("tipoConduta"),
                    rs.getString("descricao"),
                    rs.getString("tipoPerfil"),
                    rs.getDouble("valorEstrelas")
                );
                reputacao.addConduta(conduta);
            }
        }
    }
    return new ArrayList<>(reputacoesMap.values());
}
    
}
