package linketinder.UI.candidato

import linketinder.UI.competencia.CompetenciaMenu
import linketinder.UI.validation.DatabaseFactory
import linketinder.db.ConfigDatabase
import linketinder.dao.candidato.*
import linketinder.db.IDatabaseConnection
import linketinder.db.factory.IDatabaseConnectionFactory
import linketinder.entity.CandidatoCompetencia
import linketinder.entity.dto.CompetenciaDTO
import linketinder.service.candidato.CandidatoCompetenciaService
import linketinder.service.candidato.ICandidatoCompetenciaService

class CompetenciaCandidatoMenu {

    private ICandidatoCompetenciaService candidatoCompetenciaService
    private CompetenciaMenu competenciaMenu

    CompetenciaCandidatoMenu(ConfigDatabase configDatabase) {
        DatabaseFactory databaseFactory = new DatabaseFactory()
        IDatabaseConnectionFactory factory = databaseFactory.createConnectionFactory(configDatabase)
        IDatabaseConnection connection = factory.createConnection()

        ICandidatoDao candidatoDao = new CandidatoDao(connection)
        ICandidatoCompetenciaDao candidatoCompetenciaDao = new CandidatoCompetenciaDao(connection, candidatoDao)

        competenciaMenu = new CompetenciaMenu(configDatabase)
        candidatoCompetenciaService = new CandidatoCompetenciaService(candidatoCompetenciaDao)
    }

    void exibirMenuCandidato(Reader reader) {
        while (true) {
            println "Menu Competencia do Candidato:"
            println "1. Listar competencias do candidato"
            println "2. adicionar competencia"
            println "3. Atualizar competencia"
            println "4. excluir competencia"
            println "5. Voltar"

            int opcao = Integer.parseInt(reader.readLine())
            switch (opcao) {
                case 1:
                    listarCompetenciasDoCandidato(reader)
                    break
                case 2:
                    adicionarCompetenciaCandidato(reader)
                    break
                case 3:
                    atualizarCompetenciaCandidato(reader)
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

    CandidatoCompetencia criarCompetenciaCandidato(Reader reader) {
        println "Digite o Id do candidato:"
        Integer idCandidato = Integer.parseInt(reader.readLine())

        println "Digite o Id da competencia:"
        Integer idCompetencia = Integer.parseInt(reader.readLine())

        println "Digite o nivel da competencia (1 a 3):"
        Integer nivel = Integer.parseInt(reader.readLine())


        return new CandidatoCompetencia(idCandidato, idCompetencia, nivel)
    }



    void listarCompetenciasDoCandidato(Reader reader) {
        println "Digite o ID do candidato:"
        Integer idCandidato = Integer.parseInt(reader.readLine())

        List<CompetenciaDTO> competencias = candidatoCompetenciaService.listarCompetenciasPorCandidato(idCandidato)

        competencias.each { competencia ->
            println "Competência: ID:${competencia.id}, ${competencia.nome}, Nível: ${competencia.nivel}"
        }
    }

    void adicionarCompetenciaCandidato(Reader reader) {
        println "Competencia :"
        competenciaMenu.listarCompetencias()
        CandidatoCompetencia candidatoCompetencia = criarCompetenciaCandidato(reader)
        candidatoCompetenciaService.adicionarCandidatoCompetencia(candidatoCompetencia)
    }

    void atualizarCompetenciaCandidato(Reader reader) {
        listarCompetenciasDoCandidato(reader)
        println "Digite o ID da competencia que deseja atualizar:"
        Integer idCompetencia = Integer.parseInt(reader.readLine())

        CandidatoCompetencia candidatoCompetencia = criarCompetenciaCandidato(reader)
        candidatoCompetencia.setId(idCompetencia)

        candidatoCompetenciaService.atualizarNivelCompetenciaCandidato(candidatoCompetencia)
    }

    void excluirCompetencia(Reader reader){
        println "Digite o ID da competencia:"
        Integer idCompetencia = Integer.parseInt(reader.readLine())
        candidatoCompetenciaService.excluirCompetenciaCandidato(idCompetencia)
    }
}

