package linketinder.db.factory

import linketinder.db.IDatabaseConnection

interface IDatabaseConnectionFactory {
    IDatabaseConnection createConnection();
}