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
public class H2DatabaseConnectionFactory implements DatabaseConnectionFactory {
    private static final String URL = "jdbc:h2:./database;DB_CLOSE_DELAY=-1";
    private static final String USER = "sa";
    private static final String PASSWORD = "";
    private static final int DATABASE_VERSION = 1;
    private static boolean isInitialized = false;

    static {
        try {
            // Carregar o driver H2 explicitamente
            Class.forName("org.h2.Driver");
            setupDatabase();
        } catch (ClassNotFoundException e) {
            throw new RuntimeException("Driver H2 não encontrado no classpath", e);
        }
    }

    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }
    
    private static void setupDatabase() {
        if (isInitialized) {
            return;
        }

        try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD); 
             Statement stmt = conn.createStatement()) {
            
            stmt.execute("""
                CREATE TABLE IF NOT EXISTS database_info (
                    id INTEGER PRIMARY KEY,
                    version INTEGER NOT NULL,
                    initialized BOOLEAN NOT NULL DEFAULT FALSE
                );
            """);

            // Verificar se já está inicializado
            ResultSet rs = stmt.executeQuery(
                "SELECT version, initialized FROM database_info WHERE id = 1"
            );

            if (rs.next()) {
                int currentVersion = rs.getInt("version");
                boolean initialized = rs.getBoolean("initialized");
                
                if (initialized && currentVersion >= DATABASE_VERSION) {
                    System.out.println("Banco de dados H2 já inicializado. Versão: " + currentVersion);
                    isInitialized = true;
                    return;
                }
            }

            // Se não estiver inicializado ou versão antiga, inicializar
            initializeDatabase(stmt);
            
            
            stmt.execute(
                "MERGE INTO database_info KEY(id) VALUES (1, " + DATABASE_VERSION + ", TRUE)"
            );
            
            isInitialized = true;
            System.out.println("Banco de dados H2 inicializado com sucesso. Versão: " + DATABASE_VERSION);
            
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao inicializar banco de dados H2", e);
        }
    }

    private static void initializeDatabase(Statement stmt) throws SQLException {
        stmt.execute("SET REFERENTIAL_INTEGRITY TRUE");
        
        String[] ddlQueries = {
                """
                CREATE TABLE IF NOT EXISTS tipos (
                    idTipo INTEGER PRIMARY KEY AUTO_INCREMENT,
                    descricao VARCHAR(45)
                );
                """,
                """
                CREATE TABLE IF NOT EXISTS reputacoes (
                    idReputacao INTEGER PRIMARY KEY AUTO_INCREMENT,
                    estrelas DOUBLE,
                    beneficioClimatico DOUBLE,
                    nivel VARCHAR(45)
                );
                """,
                """
                CREATE TABLE IF NOT EXISTS condutas (
                    idConduta INTEGER PRIMARY KEY AUTO_INCREMENT,
                    tipoConduta VARCHAR(45),
                    descricao VARCHAR(255),
                    tipoPerfil VARCHAR(45),
                    valorEstrelas DOUBLE,
                    idReputacao INTEGER,
                    FOREIGN KEY (idReputacao) REFERENCES reputacoes(idReputacao)
                );
                """,
                """
                CREATE TABLE IF NOT EXISTS compradores (
                    idPerfilComprador INTEGER PRIMARY KEY AUTO_INCREMENT,
                    sistemId VARCHAR(45) UNIQUE,
                    idReputacao INTEGER,
                    FOREIGN KEY (idReputacao) REFERENCES reputacoes(idReputacao)
                );
                """,
                """
                CREATE TABLE IF NOT EXISTS vendedores (
                    idPerfilVendedor INTEGER PRIMARY KEY AUTO_INCREMENT,
                    sistemId VARCHAR(45) UNIQUE,
                    idReputacao INTEGER,
                    FOREIGN KEY (idReputacao) REFERENCES reputacoes(idReputacao)
                );
                """,
                """
                CREATE TABLE IF NOT EXISTS usuarios (
                    idUsuario INTEGER PRIMARY KEY AUTO_INCREMENT,
                    nome VARCHAR(100),
                    email VARCHAR(100) UNIQUE NOT NULL,
                    telefone VARCHAR(45),
                    senha VARCHAR(100) NOT NULL,
                    eAdmin BOOLEAN DEFAULT FALSE,
                    criado_em TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
                    idPerfilComprador INTEGER,
                    idPerfilVendedor INTEGER,
                    FOREIGN KEY (idPerfilComprador) REFERENCES compradores(idPerfilComprador),
                    FOREIGN KEY (idPerfilVendedor) REFERENCES vendedores(idPerfilVendedor)
                );
                """,
                """
                CREATE TABLE IF NOT EXISTS itens (
                    idItem INTEGER PRIMARY KEY AUTO_INCREMENT,
                    idC VARCHAR(45) UNIQUE,
                    subcategoria VARCHAR(45),
                    tamanho VARCHAR(45),
                    cor VARCHAR(45),
                    peso DOUBLE,
                    precoBase DOUBLE,
                    precoFinal DOUBLE,
                    gpwEvitado DOUBLE,
                    mciItem DOUBLE,
                    numeroCiclo INTEGER,
                    publicado_em TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
                    idPerfilVendedor INTEGER,
                    idTipo INTEGER,
                    FOREIGN KEY (idPerfilVendedor) REFERENCES vendedores(idPerfilVendedor),
                    FOREIGN KEY (idTipo) REFERENCES tipos(idTipo)
                );
                """,
                """
                CREATE TABLE IF NOT EXISTS defeitos (
                    idDefeito INTEGER PRIMARY KEY AUTO_INCREMENT,
                    descricao VARCHAR(45),
                    percentualDesconto DOUBLE,
                    idTipo INTEGER,
                    FOREIGN KEY (idTipo) REFERENCES tipos(idTipo)
                );
                """,
                """
                CREATE TABLE IF NOT EXISTS item_defeitos (
                    idDefeito INTEGER,
                    idItem INTEGER,
                    valorDesconto DOUBLE,
                    PRIMARY KEY (idDefeito, idItem),
                    FOREIGN KEY (idDefeito) REFERENCES defeitos(idDefeito),
                    FOREIGN KEY (idItem) REFERENCES itens(idItem)
                );
                """,
                """
                CREATE TABLE IF NOT EXISTS materiais (
                    idMaterial INTEGER PRIMARY KEY AUTO_INCREMENT,
                    descricao VARCHAR(45),
                    fatorEmissao DOUBLE
                );
                """,
                """
                CREATE TABLE IF NOT EXISTS item_materiais (
                    idItem INTEGER,
                    idMaterial INTEGER,
                    percentual DOUBLE,
                    PRIMARY KEY (idItem, idMaterial),
                    FOREIGN KEY (idItem) REFERENCES itens(idItem),
                    FOREIGN KEY (idMaterial) REFERENCES materiais(idMaterial)
                );
                """,
                """
                CREATE TABLE IF NOT EXISTS ofertas (
                    idOferta INTEGER PRIMARY KEY AUTO_INCREMENT,
                    dataOferta TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
                    valorOferta DOUBLE,
                    status VARCHAR(45),
                    idItem INTEGER,
                    idPerfilComprador INTEGER,
                    FOREIGN KEY (idItem) REFERENCES itens(idItem),
                    FOREIGN KEY (idPerfilComprador) REFERENCES compradores(idPerfilComprador)
                );
                """,
                """
                CREATE TABLE IF NOT EXISTS transacoes (
                    idTransacao INTEGER PRIMARY KEY AUTO_INCREMENT,
                    idC VARCHAR(45) UNIQUE,
                    dataInicio TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
                    dataTermino TIMESTAMP,
                    comentarioVendedor VARCHAR(255),
                    comentarioComprador VARCHAR(255),
                    idPerfilVendedor INTEGER,
                    idPerfilComprador INTEGER,
                    FOREIGN KEY (idPerfilVendedor) REFERENCES vendedores(idPerfilVendedor),
                    FOREIGN KEY (idPerfilComprador) REFERENCES compradores(idPerfilComprador)
                );
                """,
                """
                CREATE TABLE IF NOT EXISTS denuncias (
                    idDenuncia INTEGER PRIMARY KEY AUTO_INCREMENT,
                    idC VARCHAR(45) UNIQUE,
                    descricao VARCHAR(255),
                    status VARCHAR(45),
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
                "MERGE INTO materiais KEY(descricao) VALUES ('Algodão', 5.2);",
                "MERGE INTO materiais KEY(descricao) VALUES ('Poliéster', 9.5);",
                "MERGE INTO materiais KEY(descricao) VALUES ('Couro', 14.8);",
                "MERGE INTO materiais KEY(descricao) VALUES ('Metal (ligas leves)', 8.6);",
                "MERGE INTO materiais KEY(descricao) VALUES ('Plástico de base fóssil', 3.1);",
                "MERGE INTO materiais KEY(descricao) VALUES ('Outros', 4.0);"
        };

        String[] insertTipoQueries = {
                "MERGE INTO tipos KEY(descricao) VALUES ('vestuario');",
                "MERGE INTO tipos KEY(descricao) VALUES ('calcado');",
                "MERGE INTO tipos KEY(descricao) VALUES ('bolsas e mochilas');",
                "MERGE INTO tipos KEY(descricao) VALUES ('bijuterias e acessorios');"
        };

        String[] insertDefeitoQueries = {
                "MERGE INTO defeitos KEY(descricao, idTipo) VALUES ('rasgo estruturante', 0.3, 1);",
                "MERGE INTO defeitos KEY(descricao, idTipo) VALUES ('Ausência de botão principal', 0.15, 1);",
                "MERGE INTO defeitos KEY(descricao, idTipo) VALUES ('ziper parcialmente funcional', 0.15, 1);",
                "MERGE INTO defeitos KEY(descricao, idTipo) VALUES ('mancha permanente', 0.20, 1);",
                "MERGE INTO defeitos KEY(descricao, idTipo) VALUES ('desgaste por pilling acentuado', 0.10, 1);",
                
                "MERGE INTO defeitos KEY(descricao, idTipo) VALUES ('sola sem relevo funcional', 0.25, 2);",
                "MERGE INTO defeitos KEY(descricao, idTipo) VALUES ('descolamento parcial de entressola', 0.20, 2);",
                "MERGE INTO defeitos KEY(descricao, idTipo) VALUES ('arranhões profundos', 0.15, 2);",
                "MERGE INTO defeitos KEY(descricao, idTipo) VALUES ('palmilha original ausente', 0.10, 2);",
                "MERGE INTO defeitos KEY(descricao, idTipo) VALUES ('odor persistente leve', 0.10, 2);",
                
                "MERGE INTO defeitos KEY(descricao, idTipo) VALUES ('alça reparada', 0.2, 3);",
                "MERGE INTO defeitos KEY(descricao, idTipo) VALUES ('fecho defeituoso', 0.20, 3);",
                "MERGE INTO defeitos KEY(descricao, idTipo) VALUES ('desbotamento extenso', 0.15, 3);",
                "MERGE INTO defeitos KEY(descricao, idTipo) VALUES ('forro rasgado', 0.15, 3);",
                
                "MERGE INTO defeitos KEY(descricao, idTipo) VALUES ('oxidação visível', 0.2, 4);",
                "MERGE INTO defeitos KEY(descricao, idTipo) VALUES ('pedra ausente', 0.15, 4);",
                "MERGE INTO defeitos KEY(descricao, idTipo) VALUES ('fecho frouxo', 0.10, 4);"
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