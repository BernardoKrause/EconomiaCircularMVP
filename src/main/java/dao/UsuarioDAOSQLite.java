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
import java.util.Optional;
import model.Usuario;
import util.DatabaseConnection;

public class UsuarioDAOSQLite implements UsuarioDAO {

    public UsuarioDAOSQLite() throws SQLException {
        try (Connection conn = DatabaseConnection.getConnection();
             Statement stmt = conn.createStatement()) {

            stmt.execute("PRAGMA foreign_keys = ON");

            String ddl = ""
                + "CREATE TABLE IF NOT EXISTS usuarios ("
                + "  idUsuario INTEGER PRIMARY KEY AUTOINCREMENT, "
                + "  nome TEXT NOT NULL, "
                + "  email TEXT NOT NULL UNIQUE, "
                + "  telefone TEXT NOT NULL, "
                + "  senha TEXT NOT NULL, "
                + "  eAdmin INTEGER NOT NULL CHECK (eAdmin IN (0,1)), "
                + "  idPerfilComprador INTEGER, "
                + "  idPerfilVendedor INTEGER, "
                + "  criado_em TEXT DEFAULT CURRENT_TIMESTAMP, "
                + "  FOREIGN KEY(idPerfilComprador) REFERENCES compradores(idComprador), "
                + "  FOREIGN KEY(idPerfilVendedor) REFERENCES vendedores(idVendedor)"
                + ");";

            stmt.execute(ddl);
        } catch (SQLException ex) {
            ex.printStackTrace();
            throw ex;
        }
    }

    @Override
    public void criar(Usuario usuario) throws SQLException {
        String sql = "INSERT INTO usuarios (nome, email, telefone, senha, eAdmin) "
                   + "VALUES (?, ?, ?, ?, ?)";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, usuario.getNome());
            pstmt.setString(2, usuario.getEmail());
            pstmt.setString(3, usuario.getTelefone());
            pstmt.setString(4, usuario.getSenha());
            pstmt.setInt(5, usuario.isAdmin() ? 1 : 0);
            pstmt.executeUpdate();

        }
    }

    @Override
    public Usuario buscaPorId(Integer id) {
        String sql = "SELECT * FROM usuarios WHERE idUsuario = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, id);

            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
                    String ts = rs.getString("criado_em");
                    LocalDateTime dt = (ts != null) ? LocalDateTime.parse(ts, formatter) : null;

                    boolean admin = rs.getInt("eAdmin") == 1; // ← coluna correta
                    return new Usuario(
                        rs.getString("idUsuario"),
                        rs.getString("nome"),
                        rs.getString("email"),
                        rs.getString("telefone"),
                        admin,
                        dt
                    );
                }
            }

        } catch (SQLException ex) {
            System.getLogger(UsuarioDAOSQLite.class.getName())
                  .log(System.Logger.Level.ERROR, (String) null, ex);
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

            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

            while (rs.next()) {
                String ts = rs.getString("criado_em");
                LocalDateTime dt = (ts != null) ? LocalDateTime.parse(ts, formatter) : null;
                boolean admin = rs.getInt("eAdmin") == 1;

                usuarios.add(new Usuario(
                    rs.getString("idUsuario"),
                    rs.getString("nome"),
                    rs.getString("email"),
                    rs.getString("telefone"),
                    admin,
                    dt
                ));
            }

        } catch (SQLException ex) {
            System.getLogger(UsuarioDAOSQLite.class.getName())
                  .log(System.Logger.Level.ERROR, (String) null, ex);
        }
        return usuarios;
    }

    @Override
    public void atualizar(Usuario usuario) {
        String sql = "UPDATE usuarios SET nome = ?, email = ?, telefone = ?, senha = ? "
                   + "WHERE idUsuario = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, usuario.getNome());
            pstmt.setString(2, usuario.getEmail());
            pstmt.setString(3, usuario.getTelefone());
            pstmt.setString(4, usuario.getSenha());
            pstmt.setInt(5, Integer.parseInt(usuario.getId()));
            pstmt.executeUpdate();

        } catch (SQLException ex) {
            System.getLogger(UsuarioDAOSQLite.class.getName())
                  .log(System.Logger.Level.ERROR, (String) null, ex);
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
            System.getLogger(UsuarioDAOSQLite.class.getName())
                  .log(System.Logger.Level.ERROR, (String) null, ex);
        }
    }

    public Optional<Usuario> buscaPorEmail(String email) throws SQLException {
        String sql = "SELECT * FROM usuarios WHERE email = ?";
        
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, email);

            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {

                    Usuario usuario = new Usuario(
                        rs.getString("email"),
                        rs.getString("senha")
                    );
                    
                    return Optional.of(usuario);
                }
            }
        }
        return Optional.empty();
    }
}
