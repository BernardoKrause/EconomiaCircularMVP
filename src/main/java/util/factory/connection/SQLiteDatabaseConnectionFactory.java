/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package util.factory.connection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 * @author berna
 */
public class SQLiteDatabaseConnectionFactory implements DatabaseConnectionFactory {
    private static final String URL = "jdbc:sqlite:" + System.getProperty("user.dir") + "/database.db";

    static {
        setupDatabase();
    }

    /**
     * 
     * @return 
     * @throws SQLException 
     */
    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL);
    }

    private static void setupDatabase() {
        String[] ddlQueries = {
            """
            CREATE TABLE IF NOT EXISTS condutas (
                idConduta INTEGER PRIMARY KEY AUTOINCREMENT,
                tipoConduta TEXT,
                descricao TEXT,
                tipoPerfil TEXT,
                valorEstrelas REAL
            );
            """,
            """
            CREATE TABLE IF NOT EXISTS reputacoes (
                idReputacao INTEGER PRIMARY KEY AUTOINCREMENT,
                estrelas REAL,
                beneficioClimatico REAL,
                nivel TEXT,
                idConduta INTEGER,
                FOREIGN KEY (idConduta) REFERENCES condutas(idConduta)
            );
            """,
            """
            CREATE TABLE IF NOT EXISTS compradores (
                idPerfilComprador INTEGER PRIMARY KEY AUTOINCREMENT,
                sistemId TEXT NOT NULL UNIQUE,
                idReputacao INTEGER,
                FOREIGN KEY (idReputacao) REFERENCES reputacoes(idReputacao)
            );
            """,
            """
            CREATE TABLE IF NOT EXISTS vendedores (
                idPerfilVendedor INTEGER PRIMARY KEY AUTOINCREMENT,
                sistemId TEXT NOT NULL UNIQUE,
                idReputacao INTEGER,
                FOREIGN KEY (idReputacao) REFERENCES reputacoes(idReputacao)
            );
            """,
            """
            CREATE TABLE IF NOT EXISTS usuarios (
                idUsuario INTEGER PRIMARY KEY AUTOINCREMENT,
                nome TEXT,
                email TEXT UNIQUE NOT NULL,
                telefone TEXT,
                senha TEXT NOT NULL,
                eAdmin INTEGER DEFAULT 0,
                criado_em TEXT,
                idPerfilComprador INTEGER,
                idPerfilVendedor INTEGER,
                FOREIGN KEY (idPerfilComprador) REFERENCES compradores(idPerfilComprador),
                FOREIGN KEY (idPerfilVendedor) REFERENCES vendedores(idPerfilVendedor)
            );
            """,
            """
            CREATE TABLE IF NOT EXISTS perfil_solicitacoes (
                idSolicitacaoPerfil INTEGER PRIMARY KEY AUTOINCREMENT,
                idUsuario INTEGER,
                status TEXT NOT NULL,
                FOREIGN KEY (idUsuario) REFERENCES usuarios(idUsuario)
            );
            """,
            """
            CREATE TABLE IF NOT EXISTS itens (
                idItem INTEGER PRIMARY KEY AUTOINCREMENT,
                idC TEXT UNIQUE,
                tipo TEXT,
                subcategoria TEXT,
                tamanho TEXT,
                cor TEXT,
                peso REAL,
                composicao TEXT,
                precoBase REAL,
                precoFinal REAL,
                gpwEvitado REAL,
                mciItem REAL,
                numeroCiclo INTEGER,
                idPerfilVendedor INTEGER,
                FOREIGN KEY (idPerfilVendedor) REFERENCES vendedores(idPerfilVendedor)
            );
            """,
            """
            CREATE TABLE IF NOT EXISTS item_defeitos (
                idDefeito INTEGER,
                idItem INTEGER,
                valorDesconto REAL,
                PRIMARY KEY (idDefeito, idItem),
                FOREIGN KEY (idDefeito) REFERENCES defeitos(idDefeito),
                FOREIGN KEY (idItem) REFERENCES itens(idItem)
            );
            """,
            """
            CREATE TABLE IF NOT EXISTS defeitos (
                idDefeito INTEGER PRIMARY KEY AUTOINCREMENT,
                descricao TEXT,
                percentualDesconto REAL
            );
            """,
            """
            CREATE TABLE IF NOT EXISTS ofertas (
                idOferta INTEGER PRIMARY KEY AUTOINCREMENT,
                dataOferta TEXT,
                valorOferta REAL,
                status TEXT,
                idItem INTEGER,
                idPerfilComprador INTEGER,
                FOREIGN KEY (idItem) REFERENCES itens(idItem),
                FOREIGN KEY (idPerfilComprador) REFERENCES compradores(idPerfilComprador)
            );
            """,
            """
            CREATE TABLE IF NOT EXISTS transacoes (
                idTransacao INTEGER PRIMARY KEY AUTOINCREMENT,
                idC TEXT UNIQUE,
                dataInicio TEXT,
                dataTermino TEXT,
                comentarioVendedor TEXT,
                comentarioComprador TEXT,
                idPerfilVendedor INTEGER,
                idPerfilComprador INTEGER,
                FOREIGN KEY (idPerfilVendedor) REFERENCES vendedores(idPerfilVendedor),
                FOREIGN KEY (idPerfilComprador) REFERENCES compradores(idPerfilComprador)
            );
            """,
            """
            CREATE TABLE IF NOT EXISTS denuncias (
                idDenuncia INTEGER PRIMARY KEY AUTOINCREMENT,
                idC TEXT UNIQUE,
                descricao TEXT,
                status TEXT,
                idTransacao INTEGER,
                idPerfilVendedor INTEGER,
                idPerfilComprador INTEGER,
                FOREIGN KEY (idTransacao) REFERENCES transacoes(idTransacao),
                FOREIGN KEY (idPerfilVendedor) REFERENCES vendedores(idPerfilVendedor),
                FOREIGN KEY (idPerfilComprador) REFERENCES compradores(idPerfilComprador)
            );
            """,
            """
            CREATE TABLE IF NOT EXISTS denuncia_defeitos (
                idDenuncia INTEGER,
                idDefeito INTEGER,
                PRIMARY KEY (idDenuncia, idDefeito),
                FOREIGN KEY (idDenuncia) REFERENCES denuncias(idDenuncia),
                FOREIGN KEY (idDefeito) REFERENCES defeitos(idDefeito)
            );
            """
        };

        try (Connection conn = getConnection(); Statement stmt = conn.createStatement()) {
            stmt.execute("PRAGMA foreign_keys = ON");

            for (String query : ddlQueries) {
                stmt.execute(query);
            }
            System.out.println("Banco de dados inicializado com sucesso.");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
