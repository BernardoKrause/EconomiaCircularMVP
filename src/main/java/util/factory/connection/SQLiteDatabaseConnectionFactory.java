/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package util.factory.connection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 * @author berna
 */
public class SQLiteDatabaseConnectionFactory implements DatabaseConnectionFactory {
    private static final String URL = "jdbc:sqlite:" + System.getProperty("user.dir") + "/database.db";
    private static final int DATABASE_VERSION = 1; // Incremente esta versão quando fizer mudanças
    private static boolean isInitialized = false;

    static {
        setupDatabase();
    }

    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL);
    }
    
    private static void setupDatabase() {
        if (isInitialized) {
            return;
        }

        try (Connection conn = getConnection(); Statement stmt = conn.createStatement()) {
            stmt.execute("""
                CREATE TABLE IF NOT EXISTS database_info (
                    id INTEGER PRIMARY KEY,
                    version INTEGER NOT NULL,
                    initialized BOOLEAN NOT NULL DEFAULT FALSE
                );
            """);

            ResultSet rs = stmt.executeQuery(
                "SELECT version, initialized FROM database_info WHERE id = 1"
            );

            if (rs.next()) {
                int currentVersion = rs.getInt("version");
                boolean initialized = rs.getBoolean("initialized");
                
                if (initialized && currentVersion >= DATABASE_VERSION) {
                    System.out.println("Banco de dados já inicializado. Versão: " + currentVersion);
                    isInitialized = true;
                    return;
                }
            }

            initializeDatabase(stmt);
            
            stmt.execute(
                "INSERT OR REPLACE INTO database_info (id, version, initialized) " +
                "VALUES (1, " + DATABASE_VERSION + ", TRUE)"
            );
            
            isInitialized = true;
            System.out.println("Banco de dados inicializado com sucesso. Versão: " + DATABASE_VERSION);
            
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao inicializar banco de dados", e);
        }
    }

    private static void initializeDatabase(Statement stmt) throws SQLException {
        stmt.execute("PRAGMA foreign_keys = ON");
        
        String[] ddlQueries = {
                """
                CREATE TABLE IF NOT EXISTS tipos (
                    idTipo INTEGER PRIMARY KEY AUTOINCREMENT,
                    descricao TEXT
                );
                """,
                """
                CREATE TABLE IF NOT EXISTS condutas (
                    idConduta INTEGER PRIMARY KEY AUTOINCREMENT,
                    tipoConduta TEXT,
                    descricao TEXT,
                    tipoPerfil TEXT,
                    valorEstrelas REAL,
                    idReputacao INTEGER,
                    FOREIGN KEY (idReputacao) REFERENCES reputacoes(idReputacao)
                );
                """,
                """
                CREATE TABLE IF NOT EXISTS reputacoes (
                    idReputacao INTEGER PRIMARY KEY AUTOINCREMENT,
                    estrelas REAL,
                    beneficioClimatico REAL,
                    nivel TEXT
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
                    criado_em DATETIME DEFAULT CURRENT_TIMESTAMP,
                    idPerfilComprador INTEGER,
                    idPerfilVendedor INTEGER,
                    FOREIGN KEY (idPerfilComprador) REFERENCES compradores(idPerfilComprador),
                    FOREIGN KEY (idPerfilVendedor) REFERENCES vendedores(idPerfilVendedor)
                );
                """,
                """
                CREATE TABLE IF NOT EXISTS itens (
                    idItem INTEGER PRIMARY KEY AUTOINCREMENT,
                    idC TEXT UNIQUE,
                    subcategoria TEXT,
                    tamanho TEXT,
                    cor TEXT,
                    peso REAL,
                    precoBase REAL,
                    precoFinal REAL,
                    gpwEvitado REAL,
                    mciItem REAL,
                    numeroCiclo INTEGER,
                    publicado_em DATETIME DEFAULT CURRENT_TIMESTAMP,
                    idPerfilVendedor INTEGER,
                    idTipo INTEGER,
                    FOREIGN KEY (idPerfilVendedor) REFERENCES vendedores(idPerfilVendedor),
                    FOREIGN KEY (idTipo) REFERENCES tipos(idTipo)
                );
                """,
                """
                CREATE TABLE IF NOT EXISTS defeitos (
                    idDefeito INTEGER PRIMARY KEY AUTOINCREMENT,
                    descricao TEXT,
                    percentualDesconto REAL,
                    idTipo INTEGER,
                    FOREIGN KEY (idTipo) REFERENCES tipos(idTipo)
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
                CREATE TABLE IF NOT EXISTS materiais (
                    idMaterial INTEGER PRIMARY KEY AUTOINCREMENT,
                    descricao TEXT,
                    fatorEmissao REAL
                );
                """,
                """
                CREATE TABLE IF NOT EXISTS item_materiais (
                    idItem INTEGER,
                    idMaterial INTEGER,
                    percentual REAL,
                    PRIMARY KEY (idItem, idMaterial),
                    FOREIGN KEY (idItem) REFERENCES itens(idItem),
                    FOREIGN KEY (idMaterial) REFERENCES materiais(idMaterial)
                );
                """,
                """
                CREATE TABLE IF NOT EXISTS ofertas (
                    idOferta INTEGER PRIMARY KEY AUTOINCREMENT,
                    dataOferta DATETIME DEFAULT CURRENT_TIMESTAMP,
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
                    dataInicio DATETIME DEFAULT CURRENT_TIMESTAMP,
                    dataTermino DATETIME,
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

        String[] insertMaterialQueries = {
                "INSERT OR IGNORE INTO materiais (descricao, fatorEmissao) VALUES ('Algodão', 5.2);",
                "INSERT OR IGNORE INTO materiais (descricao, fatorEmissao) VALUES ('Poliéster', 9.5);",
                "INSERT OR IGNORE INTO materiais (descricao, fatorEmissao) VALUES ('Couro', 14.8);",
                "INSERT OR IGNORE INTO materiais (descricao, fatorEmissao) VALUES ('Metal (ligas leves)', 8.6);",
                "INSERT OR IGNORE INTO materiais (descricao, fatorEmissao) VALUES ('Plástico de base fóssil', 3.1);",
                "INSERT OR IGNORE INTO materiais (descricao, fatorEmissao) VALUES ('Outros', 4.0);"
        };

        String[] insertTipoQueries = {
                "INSERT OR IGNORE INTO tipos (descricao) VALUES ('vestuario');",
                "INSERT OR IGNORE INTO tipos (descricao) VALUES ('calcado');",
                "INSERT OR IGNORE INTO tipos (descricao) VALUES ('bolsas e mochilas');",
                "INSERT OR IGNORE INTO tipos (descricao) VALUES ('bijuterias e acessorios');"
        };

        String[] insertDefeitoQueries = {
                "INSERT OR IGNORE INTO defeitos (descricao, percentualDesconto, idTipo) VALUES ('rasgo estruturante', 0.3, 1);",
                "INSERT OR IGNORE INTO defeitos (descricao, percentualDesconto, idTipo) VALUES ('Ausência de botão principal', 0.15, 1);",
                "INSERT OR IGNORE INTO defeitos (descricao, percentualDesconto, idTipo) VALUES ('ziper parcialmente funcional', 0.15, 1);",
                "INSERT OR IGNORE INTO defeitos (descricao, percentualDesconto, idTipo) VALUES ('mancha permanente', 0.20, 1);",
                "INSERT OR IGNORE INTO defeitos (descricao, percentualDesconto, idTipo) VALUES ('desgaste por pilling acentuado', 0.10, 1);",
                
                "INSERT OR IGNORE INTO defeitos (descricao, percentualDesconto, idTipo) VALUES ('sola sem relevo funcional', 0.25, 2);",
                "INSERT OR IGNORE INTO defeitos (descricao, percentualDesconto, idTipo) VALUES ('descolamento parcial de entressola', 0.20, 2);",
                "INSERT OR IGNORE INTO defeitos (descricao, percentualDesconto, idTipo) VALUES ('arranhões profundos', 0.15, 2);",
                "INSERT OR IGNORE INTO defeitos (descricao, percentualDesconto, idTipo) VALUES ('palmilha original ausente', 0.10, 2);",
                "INSERT OR IGNORE INTO defeitos (descricao, percentualDesconto, idTipo) VALUES ('odor persistente leve', 0.10, 2);",
                
                "INSERT OR IGNORE INTO defeitos (descricao, percentualDesconto, idTipo) VALUES ('alça reparada', 0.2, 3);",
                "INSERT OR IGNORE INTO defeitos (descricao, percentualDesconto, idTipo) VALUES ('fecho defeituoso', 0.20, 3);",
                "INSERT OR IGNORE INTO defeitos (descricao, percentualDesconto, idTipo) VALUES ('desbotamento extenso', 0.15, 3);",
                "INSERT OR IGNORE INTO defeitos (descricao, percentualDesconto, idTipo) VALUES ('forro rasgado', 0.15, 3);",
                
                "INSERT OR IGNORE INTO defeitos (descricao, percentualDesconto, idTipo) VALUES ('oxidação visível', 0.2, 4);",
                "INSERT OR IGNORE INTO defeitos (descricao, percentualDesconto, idTipo) VALUES ('pedra ausente', 0.15, 4);",
                "INSERT OR IGNORE INTO defeitos (descricao, percentualDesconto, idTipo) VALUES ('fecho frouxo', 0.10, 4);"
        };

            for (String query : ddlQueries) {
                stmt.execute(query);
            }

            for (String query : insertMaterialQueries) {
                stmt.execute(query);
            }
            
            for (String query : insertTipoQueries) {
                stmt.execute(query);
            }
            
            for (String query : insertDefeitoQueries) {
                stmt.execute(query);
            }
            
    }
}