package dao.h2;

import dao.IPerfilDAO;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import model.Comprador;
import model.Perfil;
import model.Reputacao;
import util.factory.connection.DatabaseConnectionFactory;

/**
 *
 * @author berna
 */
public class PerfilCompradorDAOH2 implements IPerfilDAO {

    @Override
    public void criar(Perfil comprador) throws SQLException {
        String sql = "INSERT INTO compradores (sistemId, idReputacao) "
                   + "VALUES (?, ?)";
        try (Connection conn = DatabaseConnectionFactory.getDatabaseConnectionFactory();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, comprador.getSistemId());
            pstmt.setInt(2, comprador.getReputacao().getId());
            pstmt.executeUpdate();
        }
    }

    @Override
    public Optional<Perfil> buscaPorIdUsuario (Integer id) throws SQLException {
        String sql = """
                SELECT c.*, 
                    r.estrelas AS reputacao_estrelas,
                    r.beneficioClimatico AS reputacao_beneficio,
                    r.nivel AS reputacao_nivel
                    FROM compradores c
                    LEFT JOIN reputacoes r ON c.idReputacao = r.idReputacao 
                    LEFT JOIN usuarios u ON u.idPerfilComprador = c.idPerfilComprador
                    WHERE u.idUsuario = ?""";
        try (Connection conn = DatabaseConnectionFactory.getDatabaseConnectionFactory();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, id);

            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {                   
                    Reputacao reputacao = new Reputacao(
                        rs.getDouble("reputacao_estrelas"),
                        rs.getDouble("reputacao_beneficio"),
                        rs.getString("reputacao_nivel")
                    );
                    reputacao.setIdReputacao(rs.getInt("idReputacao"));
                    
                    return Optional.of(new Comprador(
                        rs.getInt("idPerfilComprador"),
                        rs.getString("sistemId"),
                        reputacao
                    ));
                }
            }

        }
        return Optional.empty();
    }
    
    @Override
    public List<Perfil> buscaTodos() throws SQLException {
        List<Comprador> compradores = new ArrayList<>();
        String sql = """
            SELECT c.*, 
                   r.estrelas AS reputacao_estrelas,
                   r.beneficioClimatico AS reputacao_beneficio,
                   r.nivel AS reputacao_nivel
            FROM compradores c
            LEFT JOIN reputacoes r ON c.idReputacao = r.idReputacao
            """;

        try (Connection conn = DatabaseConnectionFactory.getDatabaseConnectionFactory();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                Reputacao reputacao = new Reputacao(
                    rs.getDouble("reputacao_estrelas"),
                    rs.getDouble("reputacao_beneficio"),
                    rs.getString("reputacao_nivel")
                );
                reputacao.setIdReputacao(rs.getInt("idReputacao"));

                compradores.add(new Comprador(
                    rs.getInt("idPerfilComprador"),
                    rs.getString("sistemId"),
                    reputacao
                ));
            }
        }
        return compradores.stream()
            .map(comprador -> (Perfil) comprador)
            .collect(Collectors.toList());
    }

    @Override
    public void atualizar(Perfil perfil) throws SQLException {
        String sql = "UPDATE compradores SET sistemId = ?, idReputacao = ?"
                   + "WHERE idPerfil = ?";
        try (Connection conn = DatabaseConnectionFactory.getDatabaseConnectionFactory();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, perfil.getSistemId());
            pstmt.setInt(2, perfil.getReputacao().getId());
            pstmt.setInt(3, perfil.getId());
            pstmt.executeUpdate();

        } 
    }
    
    @Override
    public void deletar(Integer id) throws SQLException {
        String sql = "DELETE FROM compradores WHERE idPerfilComprador = ?";
        try (Connection conn = DatabaseConnectionFactory.getDatabaseConnectionFactory();
            PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, id);
            pstmt.executeUpdate();
        }
    }
 
    @Override
    public Optional<Perfil> buscaPorId(Integer id) throws SQLException {
        String sql = """
            SELECT c.*, 
                   r.estrelas AS reputacao_estrelas,
                   r.beneficioClimatico AS reputacao_beneficio,
                   r.nivel AS reputacao_nivel
            FROM compradores c
            LEFT JOIN reputacoes r ON c.idReputacao = r.idReputacao
            WHERE c.idPerfilComprador = ?
            """;
        
        try (Connection conn = DatabaseConnectionFactory.getDatabaseConnectionFactory();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, id);

            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    Reputacao reputacao = new Reputacao(
                        rs.getDouble("reputacao_estrelas"),
                        rs.getDouble("reputacao_beneficio"),
                        rs.getString("reputacao_nivel")
                    );
                    reputacao.setIdReputacao(rs.getInt("idReputacao"));

                    Comprador comprador = new Comprador(
                        rs.getInt("idPerfilComprador"),
                        rs.getString("sistemId"),
                        reputacao
                    );
                    
                    return Optional.of(comprador);
                }
            }
        }
        return Optional.empty();
    }
}