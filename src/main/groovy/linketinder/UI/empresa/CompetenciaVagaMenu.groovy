package linketinder.UI.empresa

import linketinder.UI.competencia.CompetenciaMenu
import linketinder.dao.vaga.IVagaCompetenciaDao
import linketinder.dao.vaga.IVagaDao
import linketinder.dao.vaga.VagaCompetenciaDao
import linketinder.dao.vaga.VagaDao
import linketinder.db.DatabaseConnection
import linketinder.db.IDatabaseConnection
import linketinder.entity.VagaCompetencia
import linketinder.entity.dto.CompetenciaDTO
import linketinder.service.VagaService

class CompetenciaVagaMenu {

    VagaService vagaService
    CompetenciaMenu competenciaMenu

    CompetenciaVagaMenu() {
        IDatabaseConnection databaseConnection = new DatabaseConnection()
        IVagaCompetenciaDao vagaCompetenciaDao = new VagaCompetenciaDao(databaseConnection)
        IVagaDao vagaDao = new VagaDao(databaseConnection)

        competenciaMenu = new CompetenciaMenu()
        vagaService = new VagaService(vagaDao, vagaCompetenciaDao)
    }

    void exibirMenuVaga(Reader reader) {
        while (true) {
            println "Menu Competencias da Vaga:"
            println "1. Listar Competencias da Vaga"
            println "2. Cadastrar Competencias da Vaga"
            println "3. Atualizar Competencias da Vaga"
            println "4. Deletar Competencias da Vaga"
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

        vagaCompetencias.each {competencia ->
            println "========================="
            println "ID: ${competencia.id}"
            println "Nome: ${competencia.nome}"
            println "Nivel: ${competencia.nivel}"
            println ""
        }
    }

    void adicionarCompetenciaVaga(Reader reader){
        println "Competencias: "
        competenciaMenu.listarCompetencias()
        VagaCompetencia vagaCompetencia = criarCompetenciaVaga(reader)
        vagaService.adicionarVagaCompetencia(vagaCompetencia)
    }

    void atualizarCompetenciaVaga(Reader reader){
        println "Digite o id da competencia da vaga:"
        Integer id = Integer.parseInt(reader.readLine())

        VagaCompetencia vagaCompetencia = criarCompetenciaVaga(reader)
        vagaCompetencia.setId(id)

        vagaService.atualizarNivelVagaCompetencia(vagaCompetencia)
    }

    void excluirCompetencia(Reader reader){
        println "Digite o id da competencia da vaga:"
        Integer id = Integer.parseInt(reader.readLine())

        vagaService.excluirVagaCompetencia(id)
    }
}
