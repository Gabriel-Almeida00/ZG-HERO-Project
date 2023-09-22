package linketinder.config

import groovy.json.JsonSlurper

class Config {
    private String urlDB
    private String userDB
    private String senhaDB

    Config() {
        try {
            File configFile = new File("config.json")
            JsonSlurper jsonSlurper = new JsonSlurper()
            Object configData = jsonSlurper.parse(configFile)

            urlDB = configData.url
            userDB = configData.user
            senhaDB = configData.senha
        } catch (Exception e) {
            println("Erro ao ler o arquivo de configuração: ${e.message}")
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
