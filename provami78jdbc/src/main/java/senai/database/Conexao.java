package senai.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Conexao {
    private static final String URL = "jdbc:mysql://localhost:3306/provami78?useSSL=false&serverTimezone=UTC";
    private static final String USER = "root";
    private static final String PASSWORD = "mysqlPW";

    public static Connection conectar() throws SQLException {
        Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
            System.out.println("Conexão com o banco estabelecida.");
            return conn;
        }
}
