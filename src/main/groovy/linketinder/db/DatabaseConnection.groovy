package linketinder.db

import linketinder.exception.DataBaseException

import java.sql.*

class DatabaseConnection implements IDatabaseConnection {
    private final ConfigDatabase config

    DatabaseConnection(ConfigDatabase config) {
        this.config = config
    }

    Connection getConnection() {
        try {
            String dbUrl = config.getUrlDB()
            String dbUser = config.getUserDB()
            String dbPassword = config.getSenhaDB()

            return DriverManager.getConnection(dbUrl, dbUser, dbPassword)
        } catch (SQLException e) {
            throw new DataBaseException("Erro ao obter a conexão com o banco de dados.", e)
        }
    }

    PreparedStatement prepareStatement(String sql) throws SQLException {
        return getConnection().prepareStatement(sql)
    }

    ResultSet executeQuery(PreparedStatement statement) throws SQLException {
        return statement.executeQuery()
    }
}
