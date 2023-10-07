package linketinder.db.factory

import linketinder.db.ConfigDatabase
import linketinder.db.IDatabaseConnection
import linketinder.exception.DataBaseException

import java.sql.*


class PostgreSQLDatabaseConnection implements IDatabaseConnection {
    private final ConfigDatabase config

    PostgreSQLDatabaseConnection(ConfigDatabase config) {
        this.config = config
    }

    @Override
    Connection getConnection() {
        try {
            String dbUrl = config.getUrlDB()
            String dbUser = config.getUserDB()
            String dbPassword = config.getSenhaDB()

            return DriverManager.getConnection(dbUrl, dbUser, dbPassword)
        } catch (SQLException e) {
            throw new DataBaseException("Erro ao obter a conexão com o banco de dados." + e)
        }
    }

    @Override
    PreparedStatement prepareStatement(String sql) throws SQLException {
        return getConnection().prepareStatement(sql)
    }

    @Override
    ResultSet executeQuery(PreparedStatement statement) throws SQLException {
        return statement.executeQuery()
    }
}
