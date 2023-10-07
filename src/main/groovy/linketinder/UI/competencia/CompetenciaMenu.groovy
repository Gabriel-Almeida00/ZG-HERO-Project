package linketinder.UI.competencia

import linketinder.UI.validation.DatabaseFactory
import linketinder.db.ConfigDatabase
import linketinder.dao.competencia.CompetenciaDao
import linketinder.dao.competencia.ICompetenciaDao

import linketinder.db.IDatabaseConnection
import linketinder.db.factory.IDatabaseConnectionFactory
import linketinder.entity.Competencia
import linketinder.service.competencia.CompetenciaService

class CompetenciaMenu {
    CompetenciaService competenciaService

    CompetenciaMenu(ConfigDatabase configDatabase) {
        DatabaseFactory databaseFactory = new DatabaseFactory()
        IDatabaseConnectionFactory factory = databaseFactory.createConnectionFactory(configDatabase)
        IDatabaseConnection connection = factory.createConnection()
        ICompetenciaDao competenciaDao = new CompetenciaDao(connection)

        competenciaService = new CompetenciaService(competenciaDao)
    }

    void exibirMenuCompetencia(Reader reader) {
        while (true) {
            println "Menu Competencia:"
            println "1. Listar Competencia"
            println "2. Cadastrar Competencia"
            println "3. Atualizar Competencia"
            println "4. Deletar Competencia"
            println "5. Voltar"

            int opcao = Integer.parseInt(reader.readLine())
            switch (opcao) {
                case 1:
                    listarCompetencias()
                    break
                case 2:
                    adicionarCompetencia(reader)
                    break
                case 3:
                    atualizaCompetencia(reader)
                    break
                case 4:
                    excluirCompetencia(reader)
                    break
                case 5:
                    return
                default:
                    println "Opção inválida. Tente novamente."
            }
        }
    }

    Competencia criarCompetencia(Reader reader){
        println "Digite o nome da competencia que deseja adicionar: "
        String nome = reader.readLine()

        return new Competencia(nome)
    }

    void listarCompetencias( ){
        List<Competencia> competencias = competenciaService.listarCompetencias()

        competencias.each {competencia ->
            println "================="
            println "Id : ${competencia.id}"
            println "Nome : ${competencia.nome}"
            println ""
        }
    }

    void adicionarCompetencia(Reader reader){
        Competencia competencias = criarCompetencia(reader)
        competenciaService.adicionarCompetencia(competencias)
    }

    void atualizaCompetencia(Reader reader){
        println "Digite o id da competencia que deseja atualizar: "
        Integer id = Integer.parseInt(reader.readLine())

        Competencia competencias = criarCompetencia(reader)
        competencias.setId(id)

        competenciaService.atualizarCompetencia(competencias)
    }

    void excluirCompetencia(Reader reader){
        println "Digite o id da competencia que deseja excluir: "
        Integer id = Integer.parseInt(reader.readLine())

        competenciaService.excluirCompetencia(id)
    }
}
