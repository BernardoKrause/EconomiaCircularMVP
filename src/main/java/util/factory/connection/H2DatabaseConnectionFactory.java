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
public class H2DatabaseConnectionFactory implements DatabaseConnectionFactory {

    private static final String URL = "jdbc:h2:" + System.getProperty("user.dir") + "/database;DB_CLOSE_DELAY=-1";
    private static final String USER = "sa";
    private static final String PASSWORD = "";

    static {
        setupDatabase();
    }

    /**
     *
     * @return
     * @throws SQLException
     */
    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }

    private static void setupDatabase() {
        String[] ddlQueries = {
                """
                CREATE TABLE IF NOT EXISTS tipos (
                    idTipo INT PRIMARY KEY AUTOINCREMENT,
                    descricao VARCHAR(45)
                );
                """,
                """
                CREATE TABLE IF NOT EXISTS reputacoes (
                    idReputacao INT PRIMARY KEY AUTO_INCREMENT,
                    estrelas DOUBLE,
                    beneficioClimatico DOUBLE,
                    nivel VARCHAR(45)
                );
                """,
                """
                CREATE TABLE IF NOT EXISTS condutas (
                    idConduta INT PRIMARY KEY AUTO_INCREMENT,
                    tipoConduta VARCHAR(45),
                    descricao VARCHAR(255),
                    tipoPerfil VARCHAR(45),
                    valorEstrelas DOUBLE,
                    idReputacao INT,
                    FOREIGN KEY (idReputacao) REFERENCES reputacoes(idReputacao)
                );
                """,
                """
                CREATE TABLE IF NOT EXISTS compradores (
                    idPerfilComprador INT PRIMARY KEY AUTO_INCREMENT,
                    sistemId VARCHAR(45) UNIQUE,
                    idReputacao INT,
                    FOREIGN KEY (idReputacao) REFERENCES reputacoes(idReputacao)
                );
                """,
                """
                CREATE TABLE IF NOT EXISTS vendedores (
                    idPerfilVendedor INT PRIMARY KEY AUTO_INCREMENT,
                    sistemId VARCHAR(45) UNIQUE,
                    idReputacao INT,
                    FOREIGN KEY (idReputacao) REFERENCES reputacoes(idReputacao)
                );
                """,
                """
                CREATE TABLE IF NOT EXISTS usuarios (
                    idUsuario INT PRIMARY KEY AUTO_INCREMENT,
                    nome VARCHAR(100),
                    email VARCHAR(100) UNIQUE NOT NULL,
                    telefone VARCHAR(45),
                    senha VARCHAR(100) NOT NULL,
                    eAdmin BOOLEAN DEFAULT FALSE,
                    criado_em TIMESTAMP,
                    idPerfilComprador INT,
                    idPerfilVendedor INT,
                    FOREIGN KEY (idPerfilComprador) REFERENCES compradores(idPerfilComprador),
                    FOREIGN KEY (idPerfilVendedor) REFERENCES vendedores(idPerfilVendedor)
                );
                """,
                """
                CREATE TABLE IF NOT EXISTS perfil_solicitacoes (
                    idSolicitacaoPerfil INT PRIMARY KEY AUTO_INCREMENT,
                    idUsuario INT,
                    status VARCHAR(45) NOT NULL,
                    FOREIGN KEY (idUsuario) REFERENCES usuarios(idUsuario)
                );
                """,
                """
                CREATE TABLE IF NOT EXISTS itens (
                    idItem INT PRIMARY KEY AUTO_INCREMENT,
                    idC VARCHAR(45) UNIQUE,
                    subcategoria VARCHAR(45),
                    tamanho VARCHAR(45),
                    cor VARCHAR(45),
                    peso REAL,
                    precoBase REAL,
                    precoFinal REAL,
                    gpwEvitado REAL,
                    mciItem REAL,
                    numeroCiclo INT,
                    idPerfilVendedor INT,
                    idTipo INTEGER,
                    FOREIGN KEY (idPerfilVendedor) REFERENCES vendedores(idPerfilVendedor),
                    FOREIGN KEY (idTipo) REFERENCES tipos(idTipo)
                );
                """,
                """
                CREATE TABLE IF NOT EXISTS defeitos (
                    idDefeito INT PRIMARY KEY AUTO_INCREMENT,
                    descricao VARCHAR(45),
                    percentualDesconto REAL,
                    idTipo INT,
                    FOREIGN KEY (idTipo) REFERENCES tipos(idTipo)
                );
                """,
                """
                CREATE TABLE IF NOT EXISTS item_defeitos (
                    idDefeito INT,
                    idItem INT,
                    valorDesconto DOUBLE,
                    PRIMARY KEY (idDefeito, idItem),
                    FOREIGN KEY (idDefeito) REFERENCES defeitos(idDefeito),
                    FOREIGN KEY (idItem) REFERENCES itens(idItem)
                );
                """,
                """
                CREATE TABLE IF NOT EXISTS materiais (
                    idMaterial INT PRIMARY KEY AUTO_INCREMENT,
                    descricao VARCHAR(45)
                );
                """,
                """
                CREATE TABLE IF NOT EXISTS item_materiais (
                    idItem INT,
                    idMaterial INT,
                    percentual DOUBLE,
                    PRIMARY KEY (idItem, idMaterial),
                    FOREIGN KEY (idItem) REFERENCES itens(idItem),
                    FOREIGN KEY (idMaterial) REFERENCES materiais(idMaterial)
                );
                """,
                """
                CREATE TABLE IF NOT EXISTS ofertas (
                    idOferta INT PRIMARY KEY AUTO_INCREMENT,
                    dataOferta TIMESTAMP,
                    valorOferta DOUBLE,
                    status VARCHAR(45),
                    idItem INT,
                    idPerfilComprador INT,
                    FOREIGN KEY (idItem) REFERENCES itens(idItem),
                    FOREIGN KEY (idPerfilComprador) REFERENCES compradores(idPerfilComprador)
                );
                """,
                """
                CREATE TABLE IF NOT EXISTS transacoes (
                    idTransacao INT PRIMARY KEY AUTO_INCREMENT,
                    idC VARCHAR(45) UNIQUE,
                    dataInicio TIMESTAMP,
                    dataTermino TIMESTAMP,
                    comentarioVendedor VARCHAR(255),
                    comentarioComprador VARCHAR(255),
                    idPerfilVendedor INT,
                    idPerfilComprador INT,
                    FOREIGN KEY (idPerfilVendedor) REFERENCES vendedores(idPerfilVendedor),
                    FOREIGN KEY (idPerfilComprador) REFERENCES compradores(idPerfilComprador)
                );
                """,
                """
                CREATE TABLE IF NOT EXISTS denuncias (
                    idDenuncia INT PRIMARY KEY AUTO_INCREMENT,
                    idC VARCHAR(45) UNIQUE,
                    descricao VARCHAR(255),
                    status VARCHAR(45),
                    idTransacao INT,
                    idPerfilVendedor INT,
                    idPerfilComprador INT,
                    FOREIGN KEY (idTransacao) REFERENCES transacoes(idTransacao),
                    FOREIGN KEY (idPerfilVendedor) REFERENCES vendedores(idPerfilVendedor),
                    FOREIGN KEY (idPerfilComprador) REFERENCES compradores(idPerfilComprador)
                );
                """,
                """
                CREATE TABLE IF NOT EXISTS denuncia_defeitos (
                    idDenuncia INT,
                    idDefeito INT,
                    PRIMARY KEY (idDenuncia, idDefeito),
                    FOREIGN KEY (idDenuncia) REFERENCES denuncias(idDenuncia),
                    FOREIGN KEY (idDefeito) REFERENCES defeitos(idDefeito)
                );
                """
        };

        try (Connection conn = getConnection();
             Statement stmt = conn.createStatement()) {

            for (String query : ddlQueries) {
                stmt.execute(query);
            }
            System.out.println("Banco de dados H2 inicializado com sucesso.");

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
