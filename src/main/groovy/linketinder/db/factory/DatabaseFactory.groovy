package linketinder.db.factory

import linketinder.db.ConfigDatabase

class DatabaseFactory {
    IDatabaseConnectionFactory createConnectionFactory(ConfigDatabase configDatabase) {
        String dbType = configDatabase.getDbType()

        if (dbType == "PostgreSQL") {
            return new DatabaseSQLConnectionFactory(configDatabase)
        }

        throw new IllegalArgumentException("Tipo de banco de dados não suportado: " + dbType)
    }
}
