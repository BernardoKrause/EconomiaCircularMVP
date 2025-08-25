/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao.h2;

import dao.IUsuarioDAO;
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
import model.Usuario;
import util.factory.connection.DatabaseConnectionFactory;

/**
 *
 * @author berna
 */
public class UsuarioDAOH2 implements IUsuarioDAO {

    @Override
    public void criar(Usuario usuario) throws SQLException {
        String sql = "INSERT INTO usuarios (nome, email, telefone, senha, eAdmin, criado_em) "
                   + "VALUES (?, ?, ?, ?, ?, CURRENT_TIMESTAMP)";
        try (Connection conn = DatabaseConnectionFactory.getDatabaseConnectionFactory();
             PreparedStatement pstmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            pstmt.setString(1, usuario.getNome());
            pstmt.setString(2, usuario.getEmail());
            pstmt.setString(3, usuario.getTelefone());
            pstmt.setString(4, usuario.getSenha());
            pstmt.setInt(5, usuario.isAdmin() ? 1 : 0);
            pstmt.executeUpdate();
        }
    }

    @Override
    public Optional<Usuario> buscaPorId(Integer id) throws SQLException {
        String sql = "SELECT * FROM usuarios WHERE idUsuario = ?";
        try (Connection conn = DatabaseConnectionFactory.getDatabaseConnectionFactory();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, id);

            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
                    String ts = rs.getString("criado_em");
                    LocalDateTime dt = (ts != null) ? LocalDateTime.parse(ts, formatter) : null;

                    boolean admin = rs.getInt("eAdmin") == 1; 

                    Usuario usuario = new Usuario(
                        rs.getString("idUsuario"),
                        rs.getString("nome"),
                        rs.getString("email"),
                        rs.getString("senha"),
                        rs.getString("telefone"),
                        dt, 
                        admin
                    );
                    
                    return Optional.of(usuario);
                }
            }
        }
        return Optional.empty();
    }

    @Override
    public List<Usuario> buscaTodos() throws SQLException {
        List<Usuario> usuarios = new ArrayList<>();
        String sql = "SELECT * FROM usuarios";
        try (Connection conn = DatabaseConnectionFactory.getDatabaseConnectionFactory();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

            while (rs.next()) {
                String ts = rs.getString("criado_em");
                LocalDateTime dt = (ts != null) ? LocalDateTime.parse(ts, formatter) : null;
                boolean admin = rs.getInt("eAdmin") == 1;

                Usuario usuario = new Usuario(
                        rs.getString("idUsuario"),
                        rs.getString("nome"),
                        rs.getString("email"),
                        rs.getString("senha"),
                        rs.getString("telefone"),
                        dt, 
                        admin
                );
                
                usuarios.add(usuario);
            }
        }
        return usuarios;
    }

    @Override
    public void atualizar(Usuario usuario) throws SQLException {
        String sql = "UPDATE usuarios SET nome = ?, email = ?, telefone = ?, senha = ?, eAdmin = ? "
                   + "WHERE idUsuario = ?";
        try (Connection conn = DatabaseConnectionFactory.getDatabaseConnectionFactory();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, usuario.getNome());
            pstmt.setString(2, usuario.getEmail());
            pstmt.setString(3, usuario.getTelefone());
            pstmt.setString(4, usuario.getSenha());
            pstmt.setInt(5, usuario.isAdmin() ? 1 : 0);
            pstmt.setInt(6, Integer.parseInt(usuario.getId()));
            pstmt.executeUpdate();
        } 
    }

    @Override
    public void deletar(Integer id) throws SQLException {
        String sql = "DELETE FROM usuarios WHERE idUsuario = ?";
        try (Connection conn = DatabaseConnectionFactory.getDatabaseConnectionFactory();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, id);
            pstmt.executeUpdate();
        }
    }

    @Override
    public Optional<Usuario> buscaPorEmail(String email) throws SQLException {
        String sql = "SELECT * FROM usuarios WHERE email = ?";
        
        try (Connection conn = DatabaseConnectionFactory.getDatabaseConnectionFactory();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, email);

            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    String ts = rs.getString("criado_em");
                    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
                    LocalDateTime dt = (ts != null) ? LocalDateTime.parse(ts, formatter) : null;
                    boolean admin = rs.getInt("eAdmin") == 1;
                    
                    Usuario usuario = new Usuario(
                        rs.getString("idUsuario"),
                        rs.getString("nome"),
                        rs.getString("email"),
                        rs.getString("senha"),
                        rs.getString("telefone"),
                        dt, 
                        admin
                    );
                    
                    return Optional.of(usuario);
                }
            }
        }
        return Optional.empty();
    }
    
    // Método adicional para verificar se email já existe (útil para cadastro)
    public boolean emailExiste(String email) throws SQLException {
        String sql = "SELECT COUNT(*) FROM usuarios WHERE email = ?";
        
        try (Connection conn = DatabaseConnectionFactory.getDatabaseConnectionFactory();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, email);

            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    return rs.getInt(1) > 0;
                }
            }
        }
        return false;
    }
    
    // Método adicional para buscar usuários por nome (busca parcial)
    public List<Usuario> buscaPorNome(String nome) throws SQLException {
        List<Usuario> usuarios = new ArrayList<>();
        String sql = "SELECT * FROM usuarios WHERE nome LIKE ?";
        
        try (Connection conn = DatabaseConnectionFactory.getDatabaseConnectionFactory();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, "%" + nome + "%");

            try (ResultSet rs = pstmt.executeQuery()) {
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
                
                while (rs.next()) {
                    String ts = rs.getString("criado_em");
                    LocalDateTime dt = (ts != null) ? LocalDateTime.parse(ts, formatter) : null;
                    boolean admin = rs.getInt("eAdmin") == 1;

                    Usuario usuario = new Usuario(
                        rs.getString("idUsuario"),
                        rs.getString("nome"),
                        rs.getString("email"),
                        rs.getString("senha"),
                        rs.getString("telefone"),
                        dt, 
                        admin
                    );
                    
                    usuarios.add(usuario);
                }
            }
        }
        return usuarios;
    }
}