package dao.sqlite;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import model.Item;
import dao.IItemDAO;
import model.Material;
import util.factory.connection.SQLiteDatabaseConnectionFactory;

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
    String sqlItem = "INSERT INTO itens "
                + "(idC, subcategoria, tamanho, cor, peso, precoBase, precoFinal, "
                + "gpwEvitado, mciItem, numeroCiclo, idPerfilVendedor, idTipo) "
                + "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?);";

    String sqlItemMateriais = "INSERT INTO item_materiais (idItem, idMaterial, percentual) VALUES (?, ?, ?)";

    try (Connection conn = SQLiteDatabaseConnectionFactory.getConnection();
         PreparedStatement pstmtItem = conn.prepareStatement(sqlItem, Statement.RETURN_GENERATED_KEYS);
         PreparedStatement pstmtItemMateriais = conn.prepareStatement(sqlItemMateriais)) {

        pstmtItem.setString(1, item.getIdC());
        pstmtItem.setString(2, item.getSubcategoria());
        pstmtItem.setString(3, item.getTamanho());
        pstmtItem.setString(4, item.getCor());
        pstmtItem.setDouble(5, item.getPeso());
        pstmtItem.setDouble(6, item.getPrecoBase());
        pstmtItem.setDouble(7, item.getPrecoFinal());
        pstmtItem.setDouble(8, item.getGPWEvitado());
        pstmtItem.setDouble(9, item.getMCIItem());
        pstmtItem.setInt(10, item.getNumeroCiclo());
        System.out.println(item.getVendedor().getId());
        pstmtItem.setInt(11, item.getVendedor().getId());
        pstmtItem.setInt(12, buscaIdTipo(item.getTipo()));

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

    try (Connection conn = SQLiteDatabaseConnectionFactory.getConnection();
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
                getTipoDescricao(rsItem.getInt("idTipo")), // Buscar descrição do tipo
                rsItem.getString("subcategoria"),
                rsItem.getString("tamanho"),
                rsItem.getString("cor"),
                rsItem.getDouble("peso"),
                materiais, 
                rsItem.getDouble("precoBase"),
                rsItem.getDouble("precoFinal"),
                rsItem.getDouble("gpwEvitado"), // Campo renomeado
                rsItem.getDouble("gpwEvitado"), // GPWBase não existe, usando gpwEvitado
                rsItem.getDouble("mciItem"),
                rsItem.getInt("numeroCiclo"),
                rsItem.getTimestamp("publicado_em")
            );

            itens.add(item);
        }
        return itens;
    }
}

// Método auxiliar para buscar descrição do tipo
private String getTipoDescricao(int idTipo) throws SQLException {
    String sql = "SELECT descricao FROM tipos WHERE idTipo = ?";
    try (Connection conn = SQLiteDatabaseConnectionFactory.getConnection();
         PreparedStatement pstmt = conn.prepareStatement(sql)) {
        pstmt.setInt(1, idTipo);
        try (ResultSet rs = pstmt.executeQuery()) {
            if (rs.next()) {
                return rs.getString("descricao");
            }
        }
    }
    return null;
}

@Override
public Optional<Item> buscaPorId(Integer id) throws SQLException {
    String sqlItem = "SELECT * FROM itens WHERE idItem = ?";
    String sqlMateriais = "SELECT m.idMaterial, m.descricao, m.fatorEmissao, im.percentual " +
                          "FROM item_materiais im " +
                          "INNER JOIN materiais m ON im.idMaterial = m.idMaterial " +
                          "WHERE im.idItem = ?";

    try (Connection conn = SQLiteDatabaseConnectionFactory.getConnection();
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
                    getTipoDescricao(rsItem.getInt("idTipo")),
                    rsItem.getString("subcategoria"),
                    rsItem.getString("tamanho"),
                    rsItem.getString("cor"),
                    rsItem.getDouble("peso"),
                    materiais, 
                    rsItem.getDouble("precoBase"),
                    rsItem.getDouble("precoFinal"),
                    rsItem.getDouble("gpwEvitado"),
                    rsItem.getDouble("gpwEvitado"),
                    rsItem.getDouble("mciItem"),
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
    String sqlItem = "UPDATE itens SET subcategoria = ?, tamanho = ?, cor = ?, peso = ?, " +
                    "precoBase = ?, precoFinal = ?, gpwEvitado = ?, mciItem = ?, numeroCiclo = ?, idTipo = ? " +
                    "WHERE idItem = ?";

    String sqlDeleteMateriais = "DELETE FROM item_materiais WHERE idItem = ?";
    String sqlInsertMaterial = "INSERT INTO item_materiais (idItem, idMaterial, percentual) VALUES (?, ?, ?)";

    try (Connection conn = SQLiteDatabaseConnectionFactory.getConnection();
         PreparedStatement pstmtItem = conn.prepareStatement(sqlItem);
         PreparedStatement pstmtDeleteMateriais = conn.prepareStatement(sqlDeleteMateriais);
         PreparedStatement pstmtInsertMaterial = conn.prepareStatement(sqlInsertMaterial)) {

        conn.setAutoCommit(false);

        try {
            pstmtItem.setString(1, item.getSubcategoria());
            pstmtItem.setString(2, item.getTamanho());
            pstmtItem.setString(3, item.getCor());
            pstmtItem.setDouble(4, item.getPeso());
            pstmtItem.setDouble(5, item.getPrecoBase());
            pstmtItem.setDouble(6, item.getPrecoFinal());
            pstmtItem.setDouble(7, item.getGPWEvitado());
            pstmtItem.setDouble(8, item.getMCIItem());
            pstmtItem.setInt(9, item.getNumeroCiclo());
            pstmtItem.setInt(10, buscaIdTipo(item.getTipo()));
            pstmtItem.setInt(11, item.getId());

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
    try (Connection conn = SQLiteDatabaseConnectionFactory.getConnection();
         PreparedStatement pstmt = conn.prepareStatement(sql)) {

        pstmt.setString(1, idC.toString()); // idC é String no banco
        pstmt.executeUpdate();

    } catch (SQLException ex) {
        System.getLogger(ItemDAOSQLite.class.getName())
              .log(System.Logger.Level.ERROR, (String) null, ex);
    }
}

@Override
public List<String> buscaTipos() throws SQLException {
    List<String> tipos = new ArrayList<>();
    String sql = "SELECT * FROM tipos";

    try (Connection conn = SQLiteDatabaseConnectionFactory.getConnection();
         Statement stmt = conn.createStatement();
         ResultSet rs = stmt.executeQuery(sql)) {

        while (rs.next()) {
            tipos.add(rs.getString("descricao"));
        }
        return tipos;
    }
}

@Override
public Integer buscaIdTipo(String descricao) throws SQLException {
    String sql = "SELECT * FROM tipos WHERE descricao = ?";
    
    try (Connection conn = SQLiteDatabaseConnectionFactory.getConnection();
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

    try (Connection conn = SQLiteDatabaseConnectionFactory.getConnection();
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
                    getTipoDescricao(rsItem.getInt("idTipo")),
                    rsItem.getString("subcategoria"),
                    rsItem.getString("tamanho"),
                    rsItem.getString("cor"),
                    rsItem.getDouble("peso"),
                    materiais, 
                    rsItem.getDouble("precoBase"),
                    rsItem.getDouble("precoFinal"),
                    rsItem.getDouble("gpwEvitado"),
                    rsItem.getDouble("gpwEvitado"),
                    rsItem.getDouble("mciItem"),
                    rsItem.getInt("numeroCiclo"),
                    rsItem.getTimestamp("publicado_em")
                );

                itens.add(item);
            }
        }
    }
    return itens;
}

public List<Item> buscaRecentes(int limite) throws SQLException {
    List<Item> itens = new ArrayList<>();
    String sqlItem = "SELECT * FROM itens ORDER BY publicado_em DESC LIMIT ?";
    String sqlMateriais = "SELECT m.idMaterial, m.descricao, m.fatorEmissao, im.percentual " +
                          "FROM item_materiais im " +
                          "INNER JOIN materiais m ON im.idMaterial = m.idMaterial " +
                          "WHERE im.idItem = ?";

    try (Connection conn = SQLiteDatabaseConnectionFactory.getConnection();
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
                    getTipoDescricao(rsItem.getInt("idTipo")),
                    rsItem.getString("subcategoria"),
                    rsItem.getString("tamanho"),
                    rsItem.getString("cor"),
                    rsItem.getDouble("peso"),
                    materiais, 
                    rsItem.getDouble("precoBase"),
                    rsItem.getDouble("precoFinal"),
                    rsItem.getDouble("gpwEvitado"),
                    rsItem.getDouble("gpwEvitado"),
                    rsItem.getDouble("mciItem"),
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