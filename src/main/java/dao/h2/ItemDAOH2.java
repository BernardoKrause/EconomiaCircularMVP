/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao.h2;

import dao.IItemDAO;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import model.Item;
import model.Material;
import util.factory.connection.DatabaseConnectionFactory;

/**
 *
 * @author berna
 */
public class ItemDAOH2 implements IItemDAO {

    @Override
    public void criar(Item item) throws SQLException {
        String sqlItem = "INSERT INTO itens "
                    + "(idC, tipo, subcategoria, tamanho, cor, peso, precoBase, precoFinal, "
                    + "gpwEvitado, mciItem, numeroCiclo, idPerfilVendedor, idTipo) "
                    + "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?);";

        String sqlItemMateriais = "INSERT INTO item_materiais (idItem, idMaterial, percentual) VALUES (?, ?, ?)";

        try (Connection conn = DatabaseConnectionFactory.getDatabaseConnectionFactory();
             PreparedStatement pstmtItem = conn.prepareStatement(sqlItem, Statement.RETURN_GENERATED_KEYS);
             PreparedStatement pstmtItemMateriais = conn.prepareStatement(sqlItemMateriais)) {

            pstmtItem.setString(1, item.getIdC());
            pstmtItem.setString(2, item.getTipo());
            pstmtItem.setString(3, item.getSubcategoria());
            pstmtItem.setString(4, item.getTamanho());
            pstmtItem.setString(5, item.getCor());
            pstmtItem.setDouble(6, item.getPeso());
            pstmtItem.setDouble(7, item.getPrecoBase());
            pstmtItem.setDouble(8, item.getPrecoFinal());
            pstmtItem.setDouble(9, item.getGPWEvitado());
            pstmtItem.setDouble(10, item.getMCIItem());
            pstmtItem.setInt(11, item.getNumeroCiclo());
            pstmtItem.setInt(12, item.getVendedor().getId());
            pstmtItem.setInt(13, buscaIdTipo(item.getTipo()));

            int affectedRows = pstmtItem.executeUpdate();

            if (affectedRows == 0) {
                throw new SQLException("Falha ao criar item, nenhuma linha afetada.");
            }

            int idItem;
            try (ResultSet generatedKeys = pstmtItem.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    idItem = generatedKeys.getInt(1);
                } else {
                    throw new SQLException("Falha ao criar item, nenhum ID obtido.");
                }
            }

            List<Material> materiais = item.getComposicao();
            if (materiais != null && !materiais.isEmpty()) {
                for (Material material : materiais) {
                    int idMaterial = material.getId();

                    pstmtItemMateriais.setInt(1, idItem);
                    pstmtItemMateriais.setInt(2, idMaterial);
                    pstmtItemMateriais.setDouble(3, material.getPercentualItem());
                    pstmtItemMateriais.addBatch();
                }

                pstmtItemMateriais.executeBatch();
            }

        } catch (SQLException e) {
            throw new SQLException("Erro ao criar item: " + e.getMessage(), e);
        }
    }

    @Override
    public List<Item> buscaTodos() throws SQLException {
        List<Item> itens = new ArrayList<>();
        String sqlItem = "SELECT * FROM itens";
        String sqlMateriais = "SELECT m.idMaterial, m.descricao, m.fatorEmissao, im.percentual " +
                              "FROM item_materiais im " +
                              "INNER JOIN materiais m ON im.idMaterial = m.idMaterial " +
                              "WHERE im.idItem = ?";

        try (Connection conn = DatabaseConnectionFactory.getDatabaseConnectionFactory();
             Statement stmtItem = conn.createStatement();
             PreparedStatement pstmtMateriais = conn.prepareStatement(sqlMateriais);
             ResultSet rsItem = stmtItem.executeQuery(sqlItem)) {

            while (rsItem.next()) {
                List<Material> materiais = new ArrayList<>();
                pstmtMateriais.setInt(1, rsItem.getInt("idItem"));

                try (ResultSet rsMateriais = pstmtMateriais.executeQuery()) {
                    while (rsMateriais.next()) {
                        Material material = new Material(
                            rsMateriais.getInt("idMaterial"),
                            rsMateriais.getString("descricao"),
                            rsMateriais.getDouble("fatorEmissao"),
                            rsMateriais.getDouble("percentual")
                        );
                        materiais.add(material);
                    }
                }

                Item item = new Item(
                    rsItem.getInt("idItem"),
                    rsItem.getString("idC"),
                    rsItem.getString("tipo"),
                    rsItem.getString("subcategoria"),
                    rsItem.getString("tamanho"),
                    rsItem.getString("cor"),
                    rsItem.getDouble("peso"),
                    materiais, 
                    rsItem.getDouble("precoBase"),
                    rsItem.getDouble("precoFinal"),
                    rsItem.getDouble("GPWBase"),
                    rsItem.getDouble("GPWEvitado"),
                    rsItem.getDouble("MCIItem"),
                    rsItem.getInt("numeroCiclo"),
                    rsItem.getTimestamp("publicado_em")
                );

                itens.add(item);
            }
            return itens;
        }
    }
    
    @Override
    public Optional<Item> buscaPorId(Integer id) throws SQLException {
        String sqlItem = "SELECT * FROM itens WHERE idItem = ?";
        String sqlMateriais = "SELECT m.idMaterial, m.descricao, m.fatorEmissao, im.percentual " +
                              "FROM item_materiais im " +
                              "INNER JOIN materiais m ON im.idMaterial = m.idMaterial " +
                              "WHERE im.idItem = ?";

        try (Connection conn = DatabaseConnectionFactory.getDatabaseConnectionFactory();
             PreparedStatement pstmtItem = conn.prepareStatement(sqlItem);
             PreparedStatement pstmtMateriais = conn.prepareStatement(sqlMateriais)) {

            pstmtItem.setInt(1, id);

            try (ResultSet rsItem = pstmtItem.executeQuery()) {
                if (rsItem.next()) {
                    List<Material> materiais = new ArrayList<>();
                    pstmtMateriais.setInt(1, id);

                    try (ResultSet rsMateriais = pstmtMateriais.executeQuery()) {
                        while (rsMateriais.next()) {
                            Material material = new Material(
                                rsMateriais.getInt("idMaterial"),
                                rsMateriais.getString("descricao"),
                                rsMateriais.getDouble("fatorEmissao"),
                                rsMateriais.getDouble("percentual")
                            );
                            materiais.add(material);
                        }
                    }

                    Item item = new Item(
                        rsItem.getInt("idItem"),
                        rsItem.getString("idC"),
                        rsItem.getString("tipo"),
                        rsItem.getString("subcategoria"),
                        rsItem.getString("tamanho"),
                        rsItem.getString("cor"),
                        rsItem.getDouble("peso"),
                        materiais, 
                        rsItem.getDouble("precoBase"),
                        rsItem.getDouble("precoFinal"),
                        rsItem.getDouble("GPWBase"),
                        rsItem.getDouble("GPWEvitado"),
                        rsItem.getDouble("MCIItem"),
                        rsItem.getInt("numeroCiclo"),
                        rsItem.getTimestamp("publicado_em")
                    );

                    return Optional.of(item);
                }
            }

        }
        return Optional.empty();
    }

    @Override
    public void atualizar(Item item) throws SQLException {
        String sqlItem = "UPDATE itens SET tipo = ?, subcategoria = ?, tamanho = ?, cor = ?, peso = ?, " +
                        "precoBase = ?, precoFinal = ?, GPWBase = ?, GPWEvitado = ?, MCIItem = ?, numeroCiclo = ? " +
                        "WHERE idItem = ?";

        String sqlDeleteMateriais = "DELETE FROM item_materiais WHERE idItem = ?";
        String sqlInsertMaterial = "INSERT INTO item_materiais (idItem, idMaterial, percentual) VALUES (?, ?, ?)";

        try (Connection conn = DatabaseConnectionFactory.getDatabaseConnectionFactory();
             PreparedStatement pstmtItem = conn.prepareStatement(sqlItem);
             PreparedStatement pstmtDeleteMateriais = conn.prepareStatement(sqlDeleteMateriais);
             PreparedStatement pstmtInsertMaterial = conn.prepareStatement(sqlInsertMaterial)) {

            conn.setAutoCommit(false);

            try {
                pstmtItem.setString(1, item.getTipo());
                pstmtItem.setString(2, item.getSubcategoria());
                pstmtItem.setString(3, item.getTamanho());
                pstmtItem.setString(4, item.getCor());
                pstmtItem.setDouble(5, item.getPeso());
                pstmtItem.setDouble(6, item.getPrecoBase());
                pstmtItem.setDouble(7, item.getPrecoFinal());
                pstmtItem.setDouble(8, item.getGPWBase());
                pstmtItem.setDouble(9, item.getGPWEvitado());
                pstmtItem.setDouble(10, item.getMCIItem());
                pstmtItem.setInt(11, item.getNumeroCiclo());
                pstmtItem.setInt(12, item.getId());

                int affectedRows = pstmtItem.executeUpdate();

                if (affectedRows == 0) {
                    throw new SQLException("Falha ao atualizar item, nenhuma linha afetada.");
                }

                pstmtDeleteMateriais.setInt(1, item.getId());
                pstmtDeleteMateriais.executeUpdate();

                List<Material> materiais = item.getComposicao();
                if (materiais != null && !materiais.isEmpty()) {
                    for (Material material : materiais) {
                        pstmtInsertMaterial.setInt(1, item.getId());
                        pstmtInsertMaterial.setInt(2, material.getId());
                        pstmtInsertMaterial.setDouble(3, material.getPercentualItem());
                        pstmtInsertMaterial.addBatch();
                    }
                    pstmtInsertMaterial.executeBatch();
                }

                conn.commit();

            } catch (SQLException e) {
                conn.rollback();
                throw new SQLException("Erro ao atualizar item: " + e.getMessage(), e);
            } finally {
                conn.setAutoCommit(true);
            }
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
            System.getLogger(UsuarioDAOH2.class.getName())
                  .log(System.Logger.Level.ERROR, (String) null, ex);
        }
    }

    @Override
    public List<String> buscaTipos() throws SQLException {
        List<String> tipos = new ArrayList<>();
        String sql = "SELECT * FROM tipos";

        try (Connection conn = DatabaseConnectionFactory.getDatabaseConnectionFactory();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                tipos.add(rs.getString("descricao"));
            }
            return tipos;
        }
    }
    
    @Override
    public Integer buscaIdTipo (String descricao) throws SQLException {
        String sql = "SELECT * FROM tipos WHERE descricao = ?";
        
        try (Connection conn = DatabaseConnectionFactory.getDatabaseConnectionFactory();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, descricao);

            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    return rs.getInt("idTipo");
                }
            }

        }
        
        return null;
    } 

    @Override
    public List<Item> buscaPorVendedor(Integer idVendedor) throws SQLException {
        List<Item> itens = new ArrayList<>();
        String sqlItem = "SELECT * FROM itens WHERE idPerfilVendedor = ?";
        String sqlMateriais = "SELECT m.idMaterial, m.descricao, m.fatorEmissao, im.percentual " +
                              "FROM item_materiais im " +
                              "INNER JOIN materiais m ON im.idMaterial = m.idMaterial " +
                              "WHERE im.idItem = ?";

        try (Connection conn = DatabaseConnectionFactory.getDatabaseConnectionFactory();
             PreparedStatement pstmtItem = conn.prepareStatement(sqlItem);
             PreparedStatement pstmtMateriais = conn.prepareStatement(sqlMateriais)) {

            pstmtItem.setInt(1, idVendedor);

            try (ResultSet rsItem = pstmtItem.executeQuery()) {
                while (rsItem.next()) {
                    List<Material> materiais = new ArrayList<>();
                    int idItem = rsItem.getInt("idItem");
                    pstmtMateriais.setInt(1, idItem);

                    try (ResultSet rsMateriais = pstmtMateriais.executeQuery()) {
                        while (rsMateriais.next()) {
                            Material material = new Material(
                                rsMateriais.getInt("idMaterial"),
                                rsMateriais.getString("descricao"),
                                rsMateriais.getDouble("fatorEmissao"),
                                rsMateriais.getDouble("percentual")
                            );
                            materiais.add(material);
                        }
                    }

                    Item item = new Item(
                        rsItem.getInt("idItem"),
                        rsItem.getString("idC"),
                        rsItem.getString("tipo"),
                        rsItem.getString("subcategoria"),
                        rsItem.getString("tamanho"),
                        rsItem.getString("cor"),
                        rsItem.getDouble("peso"),
                        materiais, 
                        rsItem.getDouble("precoBase"),
                        rsItem.getDouble("precoFinal"),
                        rsItem.getDouble("GPWBase"),
                        rsItem.getDouble("GPWEvitado"),
                        rsItem.getDouble("MCIItem"),
                        rsItem.getInt("numeroCiclo"),
                        rsItem.getTimestamp("publicado_em")
                    );

                    itens.add(item);
                }
            }
        }
        return itens;
    }
    
    @Override
    public List<Item> buscaRecentes(int limite) throws SQLException {
        List<Item> itens = new ArrayList<>();
        String sqlItem = "SELECT * FROM itens ORDER BY publicado_em DESC LIMIT ?";
        String sqlMateriais = "SELECT m.idMaterial, m.descricao, m.fatorEmissao, im.percentual " +
                              "FROM item_materiais im " +
                              "INNER JOIN materiais m ON im.idMaterial = m.idMaterial " +
                              "WHERE im.idItem = ?";

        try (Connection conn = DatabaseConnectionFactory.getDatabaseConnectionFactory();
             PreparedStatement pstmtItem = conn.prepareStatement(sqlItem);
             PreparedStatement pstmtMateriais = conn.prepareStatement(sqlMateriais)) {

            pstmtItem.setInt(1, limite);

            try (ResultSet rsItem = pstmtItem.executeQuery()) {
                while (rsItem.next()) {
                    List<Material> materiais = new ArrayList<>();
                    int idItem = rsItem.getInt("idItem");
                    pstmtMateriais.setInt(1, idItem);

                    try (ResultSet rsMateriais = pstmtMateriais.executeQuery()) {
                        while (rsMateriais.next()) {
                            Material material = new Material(
                                rsMateriais.getInt("idMaterial"),
                                rsMateriais.getString("descricao"),
                                rsMateriais.getDouble("fatorEmissao"),
                                rsMateriais.getDouble("percentual")
                            );
                            materiais.add(material);
                        }
                    }

                    Item item = new Item(
                        rsItem.getInt("idItem"),
                        rsItem.getString("idC"),
                        rsItem.getString("tipo"),
                        rsItem.getString("subcategoria"),
                        rsItem.getString("tamanho"),
                        rsItem.getString("cor"),
                        rsItem.getDouble("peso"),
                        materiais, 
                        rsItem.getDouble("precoBase"),
                        rsItem.getDouble("precoFinal"),
                        rsItem.getDouble("GPWBase"),
                        rsItem.getDouble("GPWEvitado"),
                        rsItem.getDouble("MCIItem"),
                        rsItem.getInt("numeroCiclo"),
                        rsItem.getTimestamp("publicado_em")
                    );
                    
                    itens.add(item);
                }
            }
        }
        return itens;
    }
}