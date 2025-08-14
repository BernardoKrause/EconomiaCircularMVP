package dao;


import dao.UsuarioDAO;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import model.Usuario;
import util.DatabaseConnection;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author berna
 */
public class UsuarioDAOSQLite implements UsuarioDAO {
    
    public UsuarioDAOSQLite() throws SQLException {
        try (Connection conn = DatabaseConnection.getConnection();
                Statement stmt = conn.createStatement()) {
           stmt.execute("CREATE TABLE IF NOT EXISTS usuarios (" +
                         "idUsuario INTEGER PRIMARY KEY AUTOINCREMENT," +
                         "nome VARCHAR(45) NOT NULL," +
                         "email VARCHAR(45) NOT NULL," +
                         "telefone VARCHAR(45) NOT NULL," +
                         "senha VARCHAR(45) NOT NULL," +
                         "eAdmin TINYINT NOT NULL," +
                         "idPerfilComprador INTEGER," +
                         "idPerfilVendedor INTEGER," +
                         "criado_em DATETIME DEFAULT CURRENT_TIMESTAMP" +
                         "FOREIGN KEY(idPerfilComprador) REFERENCES compradores (idComprador)" +
                         "FOREIGN KEY(idPerfilVendedor) REFERENCES vendedores (idVendedor))");
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }
    
    @Override
    public void criar(Usuario usuario) {
        String sql = "INSERT INTO usuarios (nome, email, telefone, senha, eAdmin)";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, usuario.getNome());
            pstmt.setString(2, usuario.getEmail());
            pstmt.setString(3, usuario.getTelefone());
            pstmt.setString(4, usuario.getSenha());
            pstmt.setInt(5, usuario.isAdmin() ? 1 : 0);
            pstmt.executeUpdate();
        } catch (SQLException ex) {
            System.getLogger(UsuarioDAOSQLite.class.getName()).log(System.Logger.Level.ERROR, (String) null, ex);
        }
    }
    
    @Override
    public Usuario buscaPorId(Integer id) {
        String sql = "SELECT * from usuarios WHERE idUsuario = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, id);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
                LocalDateTime dateTime = LocalDateTime.parse(rs.getString("criado_em"), formatter);
                return new Usuario(rs.getString("idUsuario"), rs.getString("nome"), rs.getString("email"), rs.getString("telefone"), rs.getBoolean("isAdmin"), dateTime);
            }
        } catch (SQLException ex) {
            System.getLogger(UsuarioDAOSQLite.class.getName()).log(System.Logger.Level.ERROR, (String) null, ex);
        }
        return null;
    }
    
    @Override
    public List<Usuario> buscaTodos() {
        List<Usuario> usuarios = new ArrayList<>();
        String sql = "SELECT * FROM usuarios";
        try (Connection conn = DatabaseConnection.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
                LocalDateTime dateTime = LocalDateTime.parse(rs.getString("criado_em"), formatter);
                usuarios.add(new Usuario(rs.getString("idUsuario"), rs.getString("nome"), rs.getString("email"), rs.getString("telefone"), rs.getBoolean("isAdmin"), dateTime));
            }
        } catch (SQLException ex) {
            System.getLogger(UsuarioDAOSQLite.class.getName()).log(System.Logger.Level.ERROR, (String) null, ex);
        }
        return usuarios;
    }
    
    @Override
    public void atualizar(Usuario usuario) {
        String sql = "UPDATE usuarios SET nome = ?, email = ?, telefone = ?, senha = ? WHERE idUsuario = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, usuario.getNome());
            pstmt.setString(2, usuario.getEmail());
            pstmt.setString(3, usuario.getTelefone());
            pstmt.setString(4, usuario.getSenha());
            pstmt.setInt(5, Integer.parseInt(usuario.getId()));
            pstmt.executeUpdate();
        } catch (SQLException ex) {
            System.getLogger(UsuarioDAOSQLite.class.getName()).log(System.Logger.Level.ERROR, (String) null, ex);
        }
    }
    
    @Override
    public void deletar(Integer id) {
        String sql = "DELETE FROM usuarios WHERE idUsuario = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, id);
            pstmt.executeUpdate();
        } catch (SQLException ex) {
            System.getLogger(UsuarioDAOSQLite.class.getName()).log(System.Logger.Level.ERROR, (String) null, ex);
        }
    }
}
