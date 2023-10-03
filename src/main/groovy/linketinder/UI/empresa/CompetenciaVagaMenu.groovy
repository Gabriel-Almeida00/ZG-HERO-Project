package linketinder.UI.empresa

import linketinder.UI.competencia.CompetenciaMenu
import linketinder.db.ConfigDatabase
import linketinder.dao.candidato.CandidatoDao
import linketinder.dao.candidato.ICandidatoDao
import linketinder.dao.curtida.CurtidaDao
import linketinder.dao.curtida.ICurtidaDao
import linketinder.dao.empresa.EmpresaDao
import linketinder.dao.empresa.IEmpresaDao
import linketinder.dao.vaga.IVagaCompetenciaDao
import linketinder.dao.vaga.IVagaDao
import linketinder.dao.vaga.VagaCompetenciaDao
import linketinder.dao.vaga.VagaDao
import linketinder.db.DatabaseConnection
import linketinder.db.IDatabaseConnection
import linketinder.entity.VagaCompetencia
import linketinder.entity.dto.CompetenciaDTO
import linketinder.service.vaga.IVagaCompetenciaService
import linketinder.service.vaga.VagaCompetenciaService
import linketinder.service.vaga.VagaService

class CompetenciaVagaMenu {

   private IVagaCompetenciaService vagaCompetenciaService
    CompetenciaMenu competenciaMenu

    CompetenciaVagaMenu() {
        ConfigDatabase config = new ConfigDatabase()
        IDatabaseConnection databaseConnection = new DatabaseConnection(config)

        IVagaDao vagaDao = new VagaDao(databaseConnection)
        IVagaCompetenciaDao vagaCompetenciaDao = new VagaCompetenciaDao(databaseConnection, vagaDao)

        competenciaMenu = new CompetenciaMenu()
        vagaCompetenciaService = new VagaCompetenciaService(vagaCompetenciaDao)
    }

    void exibirMenuVaga(Reader reader) {
        while (true) {
            println "Menu Competencia da Vaga:"
            println "1. Listar Competencia da Vaga"
            println "2. Cadastrar Competencia da Vaga"
            println "3. Atualizar Competencia da Vaga"
            println "4. Deletar Competencia da Vaga"
            println "5. voltar"


            int opcao = Integer.parseInt(reader.readLine())
            switch (opcao) {
                case 1:
                    listarCompetenciasDaVaga(reader)
                    break
                case 2:
                    adicionarCompetenciaVaga(reader)
                    break
                case 3:
                    atualizarCompetenciaVaga(reader)
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

    VagaCompetencia criarCompetenciaVaga(Reader reader){
        println "Digite o id da vaga: "
        Integer idVaga = Integer.parseInt(reader.readLine())

        println "Digite o id da competencia: "
        Integer idCompetencia = Integer.parseInt(reader.readLine())

        println "Digite o nível da competencia: "
        Integer nivel = Integer.parseInt(reader.readLine())

        return new VagaCompetencia(
                idVaga,
                idCompetencia,
                nivel
        )
    }

    void listarCompetenciasDaVaga(Reader reader){
        println "Digite o id da vaga: "
        Integer id = Integer.parseInt(reader.readLine())
        List<CompetenciaDTO> vagaCompetencias = vagaService.listarCompetenciasPorVaga(id)

        vagaCompetenciaService.each {competencia ->
            println "========================="
            println "ID: ${competencia.getId()}"
            println "Nome: ${competencia.getNome()}"
            println "Nivel: ${competencia.getNivel()}"
            println ""
        }
    }

    void adicionarCompetenciaVaga(Reader reader){
        println "Competencia: "
        competenciaMenu.listarCompetencias()

        VagaCompetencia vagaCompetencia = criarCompetenciaVaga(reader)
        vagaCompetenciaService.adicionarVagaCompetencia(vagaCompetencia)
    }

    void atualizarCompetenciaVaga(Reader reader){
        println "Digite o id da competencia da vaga:"
        Integer id = Integer.parseInt(reader.readLine())

        VagaCompetencia vagaCompetencia = criarCompetenciaVaga(reader)
        vagaCompetencia.setId(id)

        vagaCompetenciaService.atualizarNivelVagaCompetencia(vagaCompetencia)
    }

    void excluirCompetencia(Reader reader){
        println "Digite o id da competencia da vaga:"
        Integer id = Integer.parseInt(reader.readLine())

        vagaCompetenciaService.excluirVagaCompetencia(id)
    }
}
