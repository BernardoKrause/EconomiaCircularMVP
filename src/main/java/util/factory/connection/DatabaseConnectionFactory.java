/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package util.factory.connection;

import adapter.DotEnvAdapter;
import java.sql.Connection;
import java.sql.SQLException;


/**
 *
 * @author berna
 */
public interface DatabaseConnectionFactory {
    
    public static Connection getDatabaseConnectionFactory() throws SQLException {
        String sgbd = DotEnvAdapter.getEnv("REPOSITORY");
        if (sgbd.equalsIgnoreCase("SQLite")) {
            return SQLiteDatabaseConnectionFactory.getConnection();
        } else if (sgbd.equalsIgnoreCase("H2")) {
            return H2DatabaseConnectionFactory.getConnection();
        } else {
            throw new IllegalArgumentException("SGBD não suportado.");
        }
    }
}
