package linketinder.db

class ConfigDatabase {
    private String dbType = "PostgreSQL"
    private String urlDB = "jdbc:postgresql://localhost:5432/linketinder.sql"
    private String userDB = "postgres"
    private String senhaDB = "postgres"

    ConfigDatabase() {}

    String getDbType() {
        return dbType
    }

    String getUrlDB() {
        return urlDB
    }

    String getUserDB() {
        return userDB
    }

    String getSenhaDB() {
        return senhaDB
    }
}
