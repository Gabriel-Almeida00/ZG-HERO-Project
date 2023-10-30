package linketinder.db.factory

import linketinder.db.ConfigDatabase
import linketinder.db.IDatabaseConnection

class DatabaseSQLConnectionFactory implements IDatabaseConnectionFactory{
    private final ConfigDatabase config

    DatabaseSQLConnectionFactory(ConfigDatabase config) {
        this.config = config
    }

    @Override
    IDatabaseConnection createConnection() {
        return  DatabaseSQLConnectionSingleton.getInstance(config)
    }
}
