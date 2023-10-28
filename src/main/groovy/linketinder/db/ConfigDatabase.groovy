package linketinder.db

class ConfigDatabase {
    private String dbType = "PostgreSQL"
    private String urlDB = "jdbc:postgresql://localhost:5432/linketinder"
    private String userDB = "postgres"
    private String senhaDB = "postgres"

     ConfigDatabase() {
        //loadConfigFromFile()
    }

//    private void loadConfigFromFile() throws ConfigDataBaseException {
//        try {
//            File configFile = new File("config.json")
//            JsonSlurper jsonSlurper = new JsonSlurper()
//            Object configData = jsonSlurper.parse(configFile)
//
//            dbType = configData.dbType
//            urlDB = configData.url
//            userDB = configData.user
//            senhaDB = configData.senha
//
//        } catch (FileNotFoundException e) {
//            throw new ConfigDataBaseException("Arquivo de configuração não encontrado", e)
//        } catch (IOException e) {
//            throw new ConfigDataBaseException("Erro de leitura do arquivo de configuração", e)
//        } catch (JsonException e) {
//            throw new ConfigDataBaseException("Erro ao analisar o arquivo JSON de configuração", e)
//        }
//    }

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
