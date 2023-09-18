package linketinder.db

import linketinder.config.Config

import java.sql.*

class DatabaseConnection implements IDatabaseConnection {
    private static final String DB_URL = "jdbc:postgresql://localhost:5432/linketinder"
    private static final String DB_USER = "postgres"
    private static final String DB_PASSWORD = Config.SENHA


    Connection getConnection() {
        try {
            return DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD)
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao obter a conexão com o banco de dados.", e)
        }
    }

    PreparedStatement prepareStatement(String sql) throws SQLException {
        return getConnection().prepareStatement(sql)
    }

    ResultSet executeQuery(PreparedStatement statement) throws SQLException {
        return statement.executeQuery()
    }
}
