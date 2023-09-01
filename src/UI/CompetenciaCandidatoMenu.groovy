package UI

import dao.candidato.CandidatoCompetenciaDao
import dao.candidato.CandidatoDao
import dao.candidato.ExperienciaDao
import dao.candidato.FormacaoDao
import dao.candidato.ICandidatoCompetenciaDao
import dao.candidato.ICandidatoDao
import dao.candidato.IExperienciaDao
import dao.candidato.IFormacaoDao
import db.DatabaseConnection
import db.IDatabaseConnection
import entity.CandidatoCompetencia
import entity.Competencias
import service.CandidatoService


class CompetenciaCandidatoMenu {

    CandidatoService candidatoService

    CompetenciaCandidatoMenu() {
        IDatabaseConnection databaseConnection = new DatabaseConnection()
        ICandidatoDao candidatoDao = new CandidatoDao(databaseConnection)
        ICandidatoCompetenciaDao candidatoCompetenciaDao = new CandidatoCompetenciaDao(databaseConnection)
        IExperienciaDao experienciaDao = new ExperienciaDao(databaseConnection)
        IFormacaoDao formacaoDao = new FormacaoDao(databaseConnection)

        candidatoService = new CandidatoService(candidatoDao, candidatoCompetenciaDao, experienciaDao, formacaoDao)
    }

    void exibirMenuCandidato(Reader reader) {
        while (true) {
            println "Menu Competencias do Candidato:"
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

        println "Digite o nivel da competencia"
        String nivel = reader.readLine()

        return new CandidatoCompetencia(
                idCandidato, idCompetencia, nivel
        )
    }

    void listarCompetenciasDoCandidato(Reader reader) {
        println "Digite o ID do candidato:"
        Integer idCandidato = Integer.parseInt(reader.readLine())

        List<Competencias> competencias = candidatoService.listarCompetenciasPorCandidato(idCandidato)

        competencias.each { competencia ->
            println "Competência: ID:${competencia.id}, ${competencia.nome}, Nível: ${competencia.nivel}"
        }
    }

    void adicionarCompetenciaCandidato(Reader reader) {
        CandidatoCompetencia candidatoCompetencia = criarCompetenciaCandidato(reader)
        candidatoService.adicionarCandidatoCompetencia(candidatoCompetencia)
    }

    void atualizarCompetenciaCandidato(Reader reader) {
        println "Digite o ID da competencia:"
        Integer idCompetencia = Integer.parseInt(reader.readLine())

        CandidatoCompetencia candidatoCompetencia = criarCompetenciaCandidato(reader)
        candidatoCompetencia.setId(idCompetencia)

        candidatoService.atualizarNivelCompetenciaCandidato(candidatoCompetencia);
    }

    void excluirCompetencia(Reader reader){
        println "Digite o ID da competencia:"
        Integer idCompetencia = Integer.parseInt(reader.readLine())
        candidatoService.excluirCompetenciaCandidato(idCompetencia)
    }
}

