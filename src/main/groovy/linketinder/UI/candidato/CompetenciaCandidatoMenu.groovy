package linketinder.UI.candidato

import linketinder.UI.competencia.CompetenciaMenu
import linketinder.config.Config
import linketinder.dao.candidato.*
import linketinder.dao.curtida.CurtidaDao
import linketinder.dao.curtida.ICurtidaDao
import linketinder.dao.empresa.EmpresaDao
import linketinder.dao.empresa.IEmpresaDao
import linketinder.dao.vaga.IVagaDao
import linketinder.dao.vaga.VagaDao
import linketinder.db.DatabaseConnection
import linketinder.db.IDatabaseConnection
import linketinder.entity.CandidatoCompetencia
import linketinder.entity.dto.CompetenciaDTO
import linketinder.service.CandidatoService

class CompetenciaCandidatoMenu {

    CandidatoService candidatoService
    CompetenciaMenu competenciaMenu

    CompetenciaCandidatoMenu() {
        Config config = new Config()
        IDatabaseConnection databaseConnection = new DatabaseConnection(config)

        ICandidatoDao candidatoDao = new CandidatoDao(databaseConnection)
        ICandidatoCompetenciaDao candidatoCompetenciaDao = new CandidatoCompetenciaDao(databaseConnection, candidatoDao)
        IExperienciaDao experienciaDao = new ExperienciaDao(databaseConnection, candidatoDao)
        IFormacaoDao formacaoDao = new FormacaoDao(databaseConnection, candidatoDao)
        IVagaDao vagaDao = new VagaDao(databaseConnection)
        IEmpresaDao empresaDao = new EmpresaDao(databaseConnection)
        ICurtidaDao curtidaDao = new CurtidaDao(databaseConnection, candidatoDao, vagaDao, empresaDao)

        competenciaMenu = new CompetenciaMenu()
        candidatoService = new CandidatoService(candidatoDao, candidatoCompetenciaDao, experienciaDao, formacaoDao, vagaDao, curtidaDao)
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

        List<CompetenciaDTO> competencias = candidatoService.listarCompetenciasPorCandidato(idCandidato)

        competencias.each { competencia ->
            println "Competência: ID:${competencia.id}, ${competencia.nome}, Nível: ${competencia.nivel}"
        }
    }

    void adicionarCompetenciaCandidato(Reader reader) {
        println "Competencia :"
        competenciaMenu.listarCompetencias()
        CandidatoCompetencia candidatoCompetencia = criarCompetenciaCandidato(reader)
        candidatoService.adicionarCandidatoCompetencia(candidatoCompetencia)
    }

    void atualizarCompetenciaCandidato(Reader reader) {
        listarCompetenciasDoCandidato(reader)
        println "Digite o ID da competencia que deseja atualizar:"
        Integer idCompetencia = Integer.parseInt(reader.readLine())

        CandidatoCompetencia candidatoCompetencia = criarCompetenciaCandidato(reader)
        candidatoCompetencia.setId(idCompetencia)

        candidatoService.atualizarNivelCompetenciaCandidato(candidatoCompetencia)
    }

    void excluirCompetencia(Reader reader){
        println "Digite o ID da competencia:"
        Integer idCompetencia = Integer.parseInt(reader.readLine())
        candidatoService.excluirCompetenciaCandidato(idCompetencia)
    }
}

