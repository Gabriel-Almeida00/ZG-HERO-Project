package linketinder.db

import java.sql.Connection
import java.sql.DriverManager
import java.sql.PreparedStatement
import java.sql.ResultSet
import java.sql.SQLException

class DatabaseConnection implements IDatabaseConnection{
    private static final String DB_URL = "jdbc:postgresql://localhost:5432/linketinder";
    private static final String DB_USER = "postgres";
    private static final String DB_PASSWORD = "postgres";


     Connection getConnection() {
        try {
            return DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao obter a conexão com o banco de dados.", e);
        }
    }

     PreparedStatement prepareStatement(String sql) throws SQLException {
        return getConnection().prepareStatement(sql);
    }

     ResultSet executeQuery(PreparedStatement statement) throws SQLException {
        return statement.executeQuery();
    }
}
