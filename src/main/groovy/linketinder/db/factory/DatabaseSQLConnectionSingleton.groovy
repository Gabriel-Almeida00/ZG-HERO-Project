package linketinder.db.factory

import linketinder.db.ConfigDatabase
import linketinder.db.IDatabaseConnection
import linketinder.exception.DataBaseException

import java.sql.*


class DatabaseSQLConnectionSingleton implements IDatabaseConnection {
    private final ConfigDatabase config
    private Connection connection
    private static DatabaseSQLConnectionSingleton instance

    private DatabaseSQLConnectionSingleton(ConfigDatabase config) {
        this.config = config
    }

    static DatabaseSQLConnectionSingleton getInstance(ConfigDatabase config) {
        if (instance == null) {
            instance = new DatabaseSQLConnectionSingleton(config)
        }
        return instance
    }

    @Override
    Connection getConnection() {
        if (connection == null) {
            try {
                String dbUrl = config.getUrlDB()
                String dbUser = config.getUserDB()
                String dbPassword = config.getSenhaDB()

                Class.forName("org.postgresql.Driver")
                connection = DriverManager.getConnection(dbUrl, dbUser, dbPassword)
            } catch (SQLException e) {
                throw new DataBaseException("Erro ao obter a conexão com o banco de dados." + e)
            }
        }
        return connection
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
