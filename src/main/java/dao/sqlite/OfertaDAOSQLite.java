/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao.sqlite;

import dao.IOfertaDAO;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import model.Oferta;
import model.Item;
import model.Comprador;
import util.factory.connection.DatabaseConnectionFactory;
import dao.IItemDAO;
import dao.IPerfilDAO;
import dao.sqlite.ItemDAOSQLite;
import dao.sqlite.PerfilCompradorDAOSQLite;
import model.Perfil;

/**
 *
 * @author berna
 */
public class OfertaDAOSQLite implements IOfertaDAO {

    private IItemDAO itemDAO;
    private IPerfilDAO compradorDAO;

    public OfertaDAOSQLite() {
        this.itemDAO = new ItemDAOSQLite();
        this.compradorDAO = new PerfilCompradorDAOSQLite();
    }

    @Override
    public void criar(Oferta oferta) throws SQLException {
        String sql = "INSERT INTO ofertas (valorOferta, idItem, idPerfilComprador) "
                   + "VALUES (?, ?, ?)";
        try (Connection conn = DatabaseConnectionFactory.getDatabaseConnectionFactory();
             PreparedStatement pstmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            pstmt.setDouble(1, oferta.getValor());
            pstmt.setInt(2, oferta.getItem().getId());
            pstmt.setInt(3, oferta.getComprador().getId());
            pstmt.executeUpdate();
            
        }
    }

    @Override
    public List<Oferta> buscaTodos() throws SQLException {
        List<Oferta> ofertas = new ArrayList<>();
        String sql = "SELECT * FROM ofertas";
        
        try (Connection conn = DatabaseConnectionFactory.getDatabaseConnectionFactory();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                Optional<Item> itemOpt = itemDAO.buscaPorId(rs.getInt("idItem"));
                Optional<Perfil> compradorOpt = compradorDAO.buscaPorId(rs.getInt("idPerfilComprador"));

                Oferta oferta = new Oferta(
                    rs.getInt("idOferta"),
                    rs.getTimestamp("dataOferta"),
                    rs.getDouble("valorOferta"),
                    rs.getString("status"),
                    itemOpt.get(),
                    (Comprador)compradorOpt.get()
                );
                
                ofertas.add(oferta);
            }
        }
        return ofertas;
    }

    @Override
    public List<Oferta> buscaPorComprador(Integer idComprador) throws SQLException {
        List<Oferta> ofertas = new ArrayList<>();
        String sql = "SELECT * FROM ofertas WHERE idPerfilComprador = ?";
        
        try (Connection conn = DatabaseConnectionFactory.getDatabaseConnectionFactory();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, idComprador);

            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    Optional<Item> itemOpt = itemDAO.buscaPorId(rs.getInt("idItem"));          
                    Optional<Perfil> compradorOpt = compradorDAO.buscaPorId(rs.getInt("idPerfilComprador"));

                    Oferta oferta = new Oferta(
                        rs.getInt("idOferta"),
                        rs.getTimestamp("dataOferta"),
                        rs.getDouble("valorOferta"),
                        rs.getString("status"),
                        itemOpt.get(),
                        (Comprador)compradorOpt.get()
                    );
                    
                    ofertas.add(oferta);
                }
            }
        }
        return ofertas;
    }

    @Override
    public void atualizar(Oferta oferta) throws SQLException {
        String sql = "UPDATE ofertas SET valorOferta = ?, status = ? "
                   + "WHERE idOferta = ?";
        try (Connection conn = DatabaseConnectionFactory.getDatabaseConnectionFactory();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setDouble(1, oferta.getValor());
            pstmt.setString(2, oferta.getStatus());
            pstmt.setInt(3, oferta.getId());
            pstmt.executeUpdate();
        } 
    }

    @Override
    public void deletar(Integer id) throws SQLException {
        String sql = "DELETE FROM ofertas WHERE idOferta = ?";
        try (Connection conn = DatabaseConnectionFactory.getDatabaseConnectionFactory();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, id);
            pstmt.executeUpdate();
        }
    }

    @Override
    public List<Oferta> buscaRecentes(int limite) throws SQLException {
        List<Oferta> ofertas = new ArrayList<>();
        String sql = "SELECT * FROM ofertas ORDER BY dataOferta DESC LIMIT ?";

        try (Connection conn = DatabaseConnectionFactory.getDatabaseConnectionFactory();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, limite);

            try (ResultSet rs = pstmt.executeQuery()) {
                while (rs.next()) {
                    Optional<Item> itemOpt = itemDAO.buscaPorId(rs.getInt("idItem"));
                    Optional<Perfil> compradorOpt = compradorDAO.buscaPorId(rs.getInt("idPerfilComprador"));
                    
                    Oferta oferta = new Oferta(
                        rs.getInt("idOferta"),
                        rs.getTimestamp("dataOferta"),
                        rs.getDouble("valorOferta"),
                        rs.getString("status"),
                        itemOpt.get(),
                        (Comprador)compradorOpt.get()
                    );
                    
                    ofertas.add(oferta);
                }
            }
        }
        return ofertas;
    }
    
    // Métodos adicionais
    @Override
    public List<Oferta> buscaPorItem(Integer idItem) throws SQLException {
        List<Oferta> ofertas = new ArrayList<>();
        String sql = "SELECT * FROM ofertas WHERE idItem = ? ORDER BY dataOferta DESC";
        
        try (Connection conn = DatabaseConnectionFactory.getDatabaseConnectionFactory();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, idItem);

            try (ResultSet rs = pstmt.executeQuery()) {
                while (rs.next()) {
                    Optional<Item> itemOpt = itemDAO.buscaPorId(rs.getInt("idItem"));
                    Optional<Perfil> compradorOpt = compradorDAO.buscaPorId(rs.getInt("idPerfilComprador"));

                    Oferta oferta = new Oferta(
                        rs.getInt("idOferta"),
                        rs.getTimestamp("dataOferta"),
                        rs.getDouble("valorOferta"),
                        rs.getString("status"),
                        itemOpt.get(),
                        (Comprador)compradorOpt.get()
                    );
                    
                    ofertas.add(oferta);
                }
            }
        }
        return ofertas;
    }
    
    @Override
    public List<Oferta> buscaPorStatus(String status) throws SQLException {
        List<Oferta> ofertas = new ArrayList<>();
        String sql = "SELECT * FROM ofertas WHERE status = ? ORDER BY dataOferta DESC";
        
        try (Connection conn = DatabaseConnectionFactory.getDatabaseConnectionFactory();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, status);

            try (ResultSet rs = pstmt.executeQuery()) {
                while (rs.next()) {
                    Optional<Item> itemOpt = itemDAO.buscaPorId(rs.getInt("idItem"));
                    Optional<Perfil> compradorOpt = compradorDAO.buscaPorId(rs.getInt("idPerfilComprador"));

                    Oferta oferta = new Oferta(
                        rs.getInt("idOferta"),
                        rs.getTimestamp("dataOferta"),
                        rs.getDouble("valorOferta"),
                        rs.getString("status"),
                        itemOpt.get(),
                        (Comprador)compradorOpt.get()
                    );
                    
                    ofertas.add(oferta);
                }
            }
        }
        return ofertas;
    }
}