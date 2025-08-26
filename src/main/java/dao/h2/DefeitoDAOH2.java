package dao.h2;

import dao.IDefeitoDAO;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import util.factory.connection.DatabaseConnectionFactory;
import util.factory.connection.H2DatabaseConnectionFactory;

/**
 *
 * @author berna
 */
public class DefeitoDAOH2 implements IDefeitoDAO {

    @Override
    public List<String> buscaPorTipo(String tipoItem) throws SQLException {
        List<String> defeitos = new ArrayList<>();
        String sql = """
            SELECT d.descricao 
            FROM defeitos d
            JOIN tipos t ON d.idTipo = t.idTipo
            WHERE LOWER(t.descricao) = LOWER(?)
            ORDER BY d.descricao
        """;
        
        try (Connection conn = DatabaseConnectionFactory.getDatabaseConnectionFactory();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            
            pstmt.setString(1, tipoItem);
            ResultSet rs = pstmt.executeQuery();
            
            while (rs.next()) {
                defeitos.add(rs.getString("descricao"));
            }
        }
        
        return defeitos;
    }

    @Override
    public Double buscaPercentualPorDefeito(String defeito) throws SQLException {
        Double percentual = null;
        String sql = "SELECT percentualDesconto FROM defeitos WHERE descricao = ?";
        
        try (Connection conn = DatabaseConnectionFactory.getDatabaseConnectionFactory();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            
            pstmt.setString(1, defeito);
            ResultSet rs = pstmt.executeQuery();
            
            if (rs.next()) {
                percentual = rs.getDouble("percentualDesconto");
                // Verifica se é nulo no ResultSet
                if (rs.wasNull()) {
                    percentual = null;
                }
            }
        }
        
        return percentual;
    }

    @Override
    public List<String> buscarTiposItem() throws SQLException {
        List<String> tipos = new ArrayList<>();
        String sql = "SELECT descricao FROM tipos ORDER BY descricao";
        
        try (Connection conn = DatabaseConnectionFactory.getDatabaseConnectionFactory();
             PreparedStatement pstmt = conn.prepareStatement(sql);
             ResultSet rs = pstmt.executeQuery()) {
            
            while (rs.next()) {
                tipos.add(rs.getString("descricao"));
            }
        }
        
        return tipos;
    }
    
}