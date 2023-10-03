package linketinder.db

import groovy.json.JsonException
import groovy.json.JsonSlurper
import linketinder.exception.ConfigDataBaseException

class ConfigDatabase {
    private String urlDB
    private String userDB
    private String senhaDB

    ConfigDatabase() {
       loadConfigFromFile()
    }

    private void loadConfigFromFile() throws ConfigDataBaseException {
        try {
            File configFile = new File("config.json")
            JsonSlurper jsonSlurper = new JsonSlurper()
            Object configData = jsonSlurper.parse(configFile)

            urlDB = configData.url
            userDB = configData.user
            senhaDB = configData.senha

        } catch (FileNotFoundException e) {
            throw new ConfigDataBaseException("Arquivo de configuração não encontrado", e)
        } catch (IOException e) {
            throw new ConfigDataBaseException("Erro de leitura do arquivo de configuração", e)
        } catch (JsonException e) {
            throw new ConfigDataBaseException("Erro ao analisar o arquivo JSON de configuração", e)
        }
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
