package dao;


import dao.ItemDAO;
import dao.UsuarioDAOSQLite;
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
import model.Item;
import model.Vendedor;
import util.DatabaseConnection;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author berna
 */
public class ItemDAOSQLite implements ItemDAO {
    public ItemDAOSQLite() throws SQLException {
        try (Connection conn = DatabaseConnection.getConnection();
             Statement stmt = conn.createStatement()) {

            stmt.execute("PRAGMA foreign_keys = ON");

            String ddl = ""
                + "CREATE TABLE IF NOT EXISTS itens ("
                + "  idItem INTEGER PRIMARY KEY AUTOINCREMENT, "
                + "  idC TEXT NOT NULL, "
                + "  tipo TEXT NOT NULL UNIQUE, "
                + "  subcategoria TEXT NOT NULL, "
                + "  tamanho TEXT NOT NULL, "
                + "  cor TEXT NOT NULL, "
                + "  peso DOUBLE NOT NULL, "
                + "  composicao TEXT NOT NULL, "
                + "  precoBase DOUBLE NOT NULL, "
                + "  precoFinal DOUBLE NOT NULL, "
                + "  gpwEvitado INTEGER NOT NULL, "
                + "  mciItem DOUBLE NOT NULL, "
                + "  numeroCiclo INTEGER NOT NULL, "
                + "  idPerfilVendedor INTEGER, "
                + "  FOREIGN KEY(idPerfilVendedor) REFERENCES vendedores(idVendedor)"
                + ");";

            stmt.execute(ddl);
        } catch (SQLException ex) {
            ex.printStackTrace();
            throw ex;
        }
    }

    @Override
    public void criar(Item item) throws SQLException {
        String sql = "INSERT INTO itens "
                    + "(idC, tipo, subcategoria, tamanho, cor, peso, composicao, precoBase, precoFinal, gpwEvitado, mciItem, numeroCiclo, idPerfilVendedor) "
                    + "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, item.getIdC());
            pstmt.setString(2, item.getTipo());
            pstmt.setString(3, item.getSubcategoria());
            pstmt.setString(4, item.getTamanho());
            pstmt.setString(5, item.getCor());
            pstmt.setDouble(6, item.getPeso());
            pstmt.setString(7, item.getComposicao());
            pstmt.setDouble(8, item.getPrecoBase());
            pstmt.setDouble(9, item.getPrecoFinal());
            pstmt.setInt(10, item.getGPWEvitado());
            pstmt.setDouble(11, item.getMCIItem());
            pstmt.setInt(12, item.getNumeroCiclo());
            pstmt.setInt(13, getIdVendedor(item.getVendedor()));
            pstmt.executeUpdate();

        }
    }

    @Override
    public List<Item> buscaTodos() throws SQLException {
        List<Item> itens = new ArrayList<>();
        String sql = "SELECT * FROM itens";
        try (Connection conn = DatabaseConnection.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                itens.add(new Item(
                    rs.getString("tipo"),
                    rs.getString("subcategoria"),
                    rs.getString("tamanho"),
                    rs.getString("cor"),
                    rs.getDouble("peso"),
                    rs.getString("composicao"),
                    rs.getDouble("precoBase")
                ));
            }
            return itens;
        }
    }
    
    @Override
    public Optional<Item> buscaPorIdC(Integer idC) throws SQLException {
        String sql = "SELECT * FROM itens WHERE idC = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, idC);

            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    Item item = new Item(
                        rs.getString("idC"),
                        rs.getString("tipo"),
                        rs.getString("subcategoria"),
                        rs.getString("tamanho"),
                        rs.getString("cor"),
                        rs.getDouble("peso"),
                        rs.getString("composicao"),
                        rs.getDouble("precoBase"),
                        rs.getDouble("precoFinal"),
                        rs.getInt("gpwEvitado"),
                        rs.getDouble("mciItem"),
                        rs.getInt("numeroCiclo")
                    );
                    
                    return Optional.of(item);
                }
            }

        }
        return Optional.empty();
    }

    @Override
    public void atualizar(Item item) throws SQLException {
        String sql = "UPDATE itens SET tipo = ?, subcategoria = ?, tamanho = ?, cor = ?, peso = ?, composicao = ?, precoBase = ? , precoFinal = ? , gpwEvitado = ? , mciItem = ?, numeroCiclo = ?"
                   + "WHERE idC = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(2, item.getTipo());
            pstmt.setString(3, item.getSubcategoria());
            pstmt.setString(4, item.getTamanho());
            pstmt.setString(5, item.getCor());
            pstmt.setDouble(6, item.getPeso());
            pstmt.setString(7, item.getComposicao());
            pstmt.setDouble(8, item.getPrecoBase());
            pstmt.setDouble(9, item.getPrecoFinal());
            pstmt.setInt(10, item.getGPWEvitado());
            pstmt.setDouble(11, item.getMCIItem());
            pstmt.setInt(12, item.getNumeroCiclo());
            pstmt.executeUpdate();

        } 
    }

    @Override
    public void deletar(Integer idC) throws SQLException {
        String sql = "DELETE FROM itens WHERE idC = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, idC);
            pstmt.executeUpdate();

        } catch (SQLException ex) {
            System.getLogger(UsuarioDAOSQLite.class.getName())
                  .log(System.Logger.Level.ERROR, (String) null, ex);
        }
    }
    
    @Override
    public Integer getIdVendedor(Vendedor vendedor) throws SQLException {
        String sql = "SELECT * FROM vendedores WHERE sistemId = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, Integer.parseInt(vendedor.getId()));

            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    return rs.getInt("idPerfilVendedor");
                }
            }

        }
        return null;
    }
}
