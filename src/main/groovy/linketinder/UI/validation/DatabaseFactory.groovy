package linketinder.UI.validation

import linketinder.db.ConfigDatabase
import linketinder.db.factory.IDatabaseConnectionFactory
import linketinder.db.factory.PostgreSQLConnectionFactory

class DatabaseFactory {
    IDatabaseConnectionFactory createConnectionFactory(ConfigDatabase configDatabase) {
        String dbType = configDatabase.getDbType()

        if (dbType == "PostgreSQL") {
            return new PostgreSQLConnectionFactory(configDatabase)
        }

        throw new IllegalArgumentException("Tipo de banco de dados não suportado: " + dbType)
    }
}
