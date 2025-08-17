/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package util.factory.connection;

import java.sql.Connection;
import java.sql.DriverManager;
import static java.sql.DriverManager.getConnection;
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
            CREATE TABLE IF NOT EXISTS insignias (
                idInsignia INTEGER PRIMARY KEY AUTOINCREMENT,
                nome TEXT NOT NULL,
                estrelaBonus REAL
            );
            """,
            """
            CREATE TABLE IF NOT EXISTS medalhas (
                idMedalha INTEGER PRIMARY KEY AUTOINCREMENT,
                nome TEXT NOT NULL,
                visual TEXT
            );
            """,
            """
            CREATE TABLE IF NOT EXISTS defeitos (
                idDefeito INTEGER PRIMARY KEY AUTOINCREMENT,
                descricao TEXT,
                percentualDesconto REAL,
                valorDesconto REAL
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
            CREATE TABLE IF NOT EXISTS itens (
                idItem INTEGER PRIMARY KEY AUTOINCREMENT,
                idC TEXT NOT NULL UNIQUE,
                tipo TEXT,
                subcategoria TEXT,
                tamanho TEXT,
                cor TEXT,
                peso REAL,
                composicao TEXT,
                precoBase REAL,
                precoFinal REAL,
                gpwEvitado INTEGER,
                mciItem REAL,
                numeroCiclo INTEGER,
                idPerfilVendedor INTEGER,
                FOREIGN KEY (idPerfilVendedor) REFERENCES vendedores(idPerfilVendedor)
            );
            """,
            """
            CREATE TABLE IF NOT EXISTS ofertas (
                idOferta INTEGER PRIMARY KEY AUTOINCREMENT,
                dataOferta TEXT,
                valorOferta REAL,
                status TEXT,
                idPerfilVendedor INTEGER,
                idItem INTEGER,
                idPerfilComprador INTEGER,
                FOREIGN KEY (idPerfilVendedor) REFERENCES vendedores(idPerfilVendedor),
                FOREIGN KEY (idItem) REFERENCES itens(idItem),
                FOREIGN KEY (idPerfilComprador) REFERENCES compradores(idPerfilComprador)
            );
            """,
            """
            CREATE TABLE IF NOT EXISTS transacoes (
                idTransacao INTEGER PRIMARY KEY AUTOINCREMENT,
                idC TEXT NOT NULL UNIQUE,
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
                idC TEXT NOT NULL UNIQUE,
                descricao TEXT,
                status TEXT,
                idPerfilComprador INTEGER,
                idPerfilVendedor INTEGER,
                FOREIGN KEY (idPerfilComprador) REFERENCES compradores(idPerfilComprador),
                FOREIGN KEY (idPerfilVendedor) REFERENCES vendedores(idPerfilVendedor)
            );
            """,
            """
            CREATE TABLE IF NOT EXISTS vendedor_insignias (
                idInsignia INTEGER,
                idPerfilVendedor INTEGER,
                PRIMARY KEY (idInsignia, idPerfilVendedor),
                FOREIGN KEY (idInsignia) REFERENCES insignias(idInsignia),
                FOREIGN KEY (idPerfilVendedor) REFERENCES vendedores(idPerfilVendedor)
            );
            """,
            """
            CREATE TABLE IF NOT EXISTS comprador_insignias (
                idInsignia INTEGER,
                idPerfilComprador INTEGER,
                PRIMARY KEY (idInsignia, idPerfilComprador),
                FOREIGN KEY (idInsignia) REFERENCES insignias(idInsignia),
                FOREIGN KEY (idPerfilComprador) REFERENCES compradores(idPerfilComprador)
            );
            """,
            """
            CREATE TABLE IF NOT EXISTS vendedor_medalhas (
                idMedalha INTEGER,
                idPerfilVendedor INTEGER,
                PRIMARY KEY (idMedalha, idPerfilVendedor),
                FOREIGN KEY (idMedalha) REFERENCES medalhas(idMedalha),
                FOREIGN KEY (idPerfilVendedor) REFERENCES vendedores(idPerfilVendedor)
            );
            """,
            """
            CREATE TABLE IF NOT EXISTS comprador_medalhas (
                idMedalha INTEGER,
                idPerfilComprador INTEGER,
                PRIMARY KEY (idMedalha, idPerfilComprador),
                FOREIGN KEY (idMedalha) REFERENCES medalhas(idMedalha),
                FOREIGN KEY (idPerfilComprador) REFERENCES compradores(idPerfilComprador)
            );
            """,
            """
            CREATE TABLE IF NOT EXISTS item_defeitos (
                idItem INTEGER,
                idDefeito INTEGER,
                PRIMARY KEY (idItem, idDefeito),
                FOREIGN KEY (idItem) REFERENCES itens(idItem),
                FOREIGN KEY (idDefeito) REFERENCES defeitos(idDefeito)
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
            """,
            """
            CREATE TABLE IF NOT EXISTS perfil_solicitacoes (
                idSolicitacaoPerfil INTEGER PRIMARY KEY AUTOINCREMENT,
                status TEXT NOT NULL DEFAULT 'W' CHECK (status IN ('A', 'D', 'W')),
                idUsuario INTEGER,
                FOREIGN KEY (idUsuario) REFERENCES usuarios(idUsuario)
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
