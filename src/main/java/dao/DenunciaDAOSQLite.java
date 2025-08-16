/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;
import model.Denuncia;
import util.DatabaseConnection;

/**
 *
 * @author berna
 */
public class DenunciaDAOSQLite implements DenunciaDAO {

    @Override
    public void criar(Denuncia denuncia) throws SQLException {
        String sql = "INSERT INTO denuncias (idC, descricao, status, idPerfilComprador, idPerfilVendedor)"
                   + "VALUES (?, ?, ?, ?, ?)";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, denuncia.getIdC());
            pstmt.setString(2, denuncia.getDescricao());
            pstmt.setString(3, denuncia.getStatus());
            pstmt.setInt(4, denuncia.getComprador().getId());
            pstmt.setInt(5, denuncia.getVendedor().getId());
            pstmt.executeUpdate();
        }
    }

    @Override
    public List<Denuncia> buscaTodos() throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public Optional<Denuncia> buscaPorIdC(Integer idC) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void atualizar(Denuncia denuncia) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void deletar(Integer idDenuncia) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
    
}
