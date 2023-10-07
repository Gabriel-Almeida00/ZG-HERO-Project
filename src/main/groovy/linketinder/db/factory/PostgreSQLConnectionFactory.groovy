package linketinder.db.factory

import linketinder.db.ConfigDatabase
import linketinder.db.IDatabaseConnection

class PostgreSQLConnectionFactory implements IDatabaseConnectionFactory{
    private final ConfigDatabase config

    PostgreSQLConnectionFactory(ConfigDatabase config) {
        this.config = config
    }

    @Override
    IDatabaseConnection createConnection() {
        return new PostgreSQLDatabaseConnection(config)
    }
}
