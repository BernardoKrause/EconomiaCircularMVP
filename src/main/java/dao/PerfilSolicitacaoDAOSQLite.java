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
import model.SolicitacaoPerfil;
import util.factory.connection.DatabaseConnectionFactory;

/**
 *
 * @author berna
 */
public class PerfilSolicitacaoDAOSQLite implements IPerfilSolicitacaoDAO {

    @Override
    public void criar(SolicitacaoPerfil solicitacaoPerfil) throws SQLException {
        String sql = "INSERT INTO perfil_solicitacoes (idUsuario)"
                   + "VALUES (?)";
        try (Connection conn = DatabaseConnectionFactory.getDatabaseConnectionFactory();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, solicitacaoPerfil.getIdUsuario());
            pstmt.executeUpdate();
        }
    }

    @Override
    public List<SolicitacaoPerfil> buscaTodos() throws SQLException {
        List<SolicitacaoPerfil> perfilSolicitacoes = new ArrayList<>();
        String sql = "SELECT * FROM perfil_solicitacoes";
        try (Connection conn = DatabaseConnectionFactory.getDatabaseConnectionFactory();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {

                perfilSolicitacoes.add(new SolicitacaoPerfil(
                    rs.getInt("idSolicitacaoPerfil"),
                    rs.getString("status"),
                    rs.getInt("idUsuario")
                ));
            }

        }
        return perfilSolicitacoes;
    }
    
    @Override
    public List<SolicitacaoPerfil> buscaEmAguardo() throws SQLException {
        List<SolicitacaoPerfil> perfilSolicitacoes = new ArrayList<>();
        String sql = "SELECT * FROM perfil_solicitacoes WHERE status = 'W'";
        try (Connection conn = DatabaseConnectionFactory.getDatabaseConnectionFactory();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {

                perfilSolicitacoes.add(new SolicitacaoPerfil(
                    rs.getInt("idSolicitacaoPerfil"),
                    rs.getString("status"),
                    rs.getInt("idUsuario")
                ));
            }

        }
        return perfilSolicitacoes;
    }

    @Override
    public void atualizar(SolicitacaoPerfil solicitacaoPerfil) throws SQLException {
        String sql = "UPDATE perfil_solicitacoes SET status = ?"
                   + "WHERE idSolicitacaoPerfil = ?";
        try (Connection conn = DatabaseConnectionFactory.getDatabaseConnectionFactory();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, solicitacaoPerfil.getStatus());
            pstmt.setInt(2, solicitacaoPerfil.getId());
            pstmt.executeUpdate();

        } 
    }

    @Override
    public void deletar(Integer id) throws SQLException {
        String sql = "DELETE FROM perfil_solicitacoes WHERE idSolicitacaoPerfil = ?";
        try (Connection conn = DatabaseConnectionFactory.getDatabaseConnectionFactory();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, id);
            pstmt.executeUpdate();

        }
    }
    
}
