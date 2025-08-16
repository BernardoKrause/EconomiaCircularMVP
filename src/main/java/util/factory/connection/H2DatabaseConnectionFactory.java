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
    
    
    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }

    private static void setupDatabase() {
        String[] ddlQueries = {
            """
            CREATE TABLE IF NOT EXISTS condutas (
                idConduta INTEGER PRIMARY KEY AUTO_INCREMENT,
                tipoConduta TEXT,
                descricao TEXT,
                tipoPerfil TEXT,
                valorEstrelas DOUBLE
            );
            """,
            """
            CREATE TABLE IF NOT EXISTS insignias (
                idInsignia INTEGER PRIMARY KEY AUTO_INCREMENT,
                nome TEXT NOT NULL,
                estrelaBonus DOUBLE
            );
            """,
            """
            CREATE TABLE IF NOT EXISTS medalhas (
                idMedalha INTEGER PRIMARY KEY AUTO_INCREMENT,
                nome TEXT NOT NULL,
                visual TEXT
            );
            """,
            """
            CREATE TABLE IF NOT EXISTS defeitos (
                idDefeito INTEGER PRIMARY KEY AUTO_INCREMENT,
                descricao TEXT,
                percentualDesconto DOUBLE
            );
            """,
            """
            CREATE TABLE IF NOT EXISTS reputacoes (
                idReputacao INTEGER PRIMARY KEY AUTO_INCREMENT,
                estrelas DOUBLE,
                beneficioClimatico DOUBLE,
                nivel TEXT,
                idConduta INTEGER,
                FOREIGN KEY (idConduta) REFERENCES condutas(idConduta)
            );
            """,
            """
            CREATE TABLE IF NOT EXISTS compradores (
                idPerfilComprador INTEGER PRIMARY KEY AUTO_INCREMENT,
                sistemId TEXT NOT NULL UNIQUE,
                idReputacao INTEGER,
                FOREIGN KEY (idReputacao) REFERENCES reputacoes(idReputacao)
            );
            """,
            """
            CREATE TABLE IF NOT EXISTS vendedores (
                idPerfilVendedor INTEGER PRIMARY KEY AUTO_INCREMENT,
                sistemId TEXT NOT NULL UNIQUE,
                idReputacao INTEGER,
                FOREIGN KEY (idReputacao) REFERENCES reputacoes(idReputacao)
            );
            """,
            """
            CREATE TABLE IF NOT EXISTS usuarios (
                idUsuario INTEGER PRIMARY KEY AUTO_INCREMENT,
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
                idItem INTEGER PRIMARY KEY AUTO_INCREMENT,
                idC TEXT NOT NULL UNIQUE,
                tipo TEXT,
                subcategoria TEXT,
                tamanho TEXT,
                cor TEXT,
                peso DOUBLE,
                composicao TEXT,
                precoBase DOUBLE,
                precoFinal DOUBLE,
                gpwEvitado INTEGER,
                mciItem DOUBLE,
                numeroCiclo INTEGER,
                idPerfilVendedor INTEGER,
                FOREIGN KEY (idPerfilVendedor) REFERENCES vendedores(idPerfilVendedor)
            );
            """,
            """
            CREATE TABLE IF NOT EXISTS ofertas (
                idOferta INTEGER PRIMARY KEY AUTO_INCREMENT,
                dataOferta TEXT,
                valorOferta DOUBLE,
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
                idTransacao INTEGER PRIMARY KEY AUTO_INCREMENT,
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
                idDenuncia INTEGER PRIMARY KEY AUTO_INCREMENT,
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
                valorDesconto DOUBLE,
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
                idSolicitacaoPerfil INTEGER PRIMARY KEY AUTO_INCREMENT,
                status TEXT NOT NULL DEFAULT 'W' CHECK (status IN ('A', 'D', 'W')),
                idUsuario INTEGER,
                FOREIGN KEY (idUsuario) REFERENCES usuarios(idUsuario)
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