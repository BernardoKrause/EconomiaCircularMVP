package dao;


import dao.UsuarioDAOSQLite;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import model.Item;
import util.factory.connection.DatabaseConnectionFactory;
import dao.IItemDAO;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author berna
 */
public class ItemDAOSQLite implements IItemDAO {
    @Override
    public void criar(Item item) throws SQLException {
        String sql = "INSERT INTO itens "
                    + "(idC, tipo, subcategoria, tamanho, cor, peso, composicao, precoBase, precoFinal, gpwEvitado, mciItem, numeroCiclo, idPerfilVendedor) "
                    + "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        try (Connection conn = DatabaseConnectionFactory.getDatabaseConnectionFactory();
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
            pstmt.setInt(13, item.getVendedor().getId());
            pstmt.executeUpdate();

        }
    }

    @Override
    public List<Item> buscaTodos() throws SQLException {
        List<Item> itens = new ArrayList<>();
        String sql = "SELECT * FROM itens";
        try (Connection conn = DatabaseConnectionFactory.getDatabaseConnectionFactory();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                itens.add(new Item(
                    rs.getInt("idItem"),
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
                ));
            }
            return itens;
        }
    }
    
    @Override
    public Optional<Item> buscaPorId(Integer id) throws SQLException {
        String sql = "SELECT * FROM itens WHERE idItem = ?";
        try (Connection conn = DatabaseConnectionFactory.getDatabaseConnectionFactory();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, id);

            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    Item item = new Item(
                        rs.getInt("idItem"),
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
        try (Connection conn = DatabaseConnectionFactory.getDatabaseConnectionFactory();
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
        try (Connection conn = DatabaseConnectionFactory.getDatabaseConnectionFactory();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, idC);
            pstmt.executeUpdate();

        } catch (SQLException ex) {
            System.getLogger(UsuarioDAOSQLite.class.getName())
                  .log(System.Logger.Level.ERROR, (String) null, ex);
        }
    }
}
