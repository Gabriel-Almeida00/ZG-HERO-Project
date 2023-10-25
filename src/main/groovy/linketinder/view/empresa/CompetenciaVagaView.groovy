package linketinder.view.empresa

import linketinder.controller.vaga.VagaCompetenciaController
import linketinder.view.competencia.CompetenciaView
import linketinder.db.factory.DatabaseFactory
import linketinder.db.ConfigDatabase
import linketinder.dao.vaga.IVagaCompetenciaDao
import linketinder.dao.vaga.IVagaDao
import linketinder.dao.vaga.VagaCompetenciaDao
import linketinder.dao.vaga.VagaDao

import linketinder.db.IDatabaseConnection
import linketinder.db.factory.IDatabaseConnectionFactory
import linketinder.model.VagaCompetencia
import linketinder.model.dto.CompetenciaDTO
import linketinder.service.vaga.IVagaCompetenciaService
import linketinder.service.vaga.VagaCompetenciaService

class CompetenciaVagaView {
    private VagaCompetenciaController competenciaVagaController
    private IVagaCompetenciaService vagaCompetenciaService
    private CompetenciaView competenciaMenu

    CompetenciaVagaView(ConfigDatabase configDatabase) {
        DatabaseFactory databaseFactory = new DatabaseFactory()
        IDatabaseConnectionFactory factory = databaseFactory.createConnectionFactory(configDatabase)
        IDatabaseConnection connection = factory.createConnection()

        IVagaDao vagaDao = new VagaDao(connection)
        IVagaCompetenciaDao vagaCompetenciaDao = new VagaCompetenciaDao(connection, vagaDao)

        competenciaMenu = new CompetenciaView(configDatabase)
        vagaCompetenciaService = new VagaCompetenciaService(vagaCompetenciaDao)
        competenciaVagaController = new VagaCompetenciaController(vagaCompetenciaService)
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

    VagaCompetencia criarCompetenciaVaga(Reader reader) {
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

    void listarCompetenciasDaVaga(Reader reader) {
        println "Digite o id da vaga: "
        Integer id = Integer.parseInt(reader.readLine())
        List<CompetenciaDTO> vagaCompetencias = competenciaVagaController.listarCompetenciasPorVaga(id)

        vagaCompetencias.each { competencia ->
            println "========================="
            println "ID: ${competencia.getId()}"
            println "Nome: ${competencia.getNome()}"
            println "Nivel: ${competencia.getNivel()}"
            println ""
        }
    }

    void adicionarCompetenciaVaga(Reader reader) {
        println "Competencia: "
        competenciaMenu.listarCompetencias()

        VagaCompetencia vagaCompetencia = criarCompetenciaVaga(reader)
        competenciaVagaController.adicionarVagaCompetencia(vagaCompetencia)
    }

    void atualizarCompetenciaVaga(Reader reader) {
        println "Digite o id da competencia da vaga:"
        Integer id = Integer.parseInt(reader.readLine())

        VagaCompetencia vagaCompetencia = criarCompetenciaVaga(reader)
        vagaCompetencia.setId(id)

        competenciaVagaController.atualizarNivelVagaCompetencia(vagaCompetencia)
    }

    void excluirCompetencia(Reader reader) {
        println "Digite o id da competencia da vaga:"
        Integer id = Integer.parseInt(reader.readLine())

        competenciaVagaController.excluirVagaCompetencia(id)
    }
}
