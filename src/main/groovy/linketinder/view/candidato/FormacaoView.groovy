package linketinder.view.candidato

import linketinder.controller.candidato.CandidatoFormacaoController
import linketinder.view.validation.DatabaseFactory
import linketinder.db.ConfigDatabase
import linketinder.dao.candidato.*
import linketinder.db.IDatabaseConnection
import linketinder.db.factory.IDatabaseConnectionFactory
import linketinder.model.Formacao
import linketinder.service.candidato.CandidatoFormacaoService
import linketinder.service.candidato.ICandidatoFormacaoService

class FormacaoView {
    private ICandidatoFormacaoService candidatoFormacaoService
    private CandidatoFormacaoController candidatoFormacaoController

    FormacaoView(ConfigDatabase configDatabase) {
        DatabaseFactory databaseFactory = new DatabaseFactory()
        IDatabaseConnectionFactory factory = databaseFactory.createConnectionFactory(configDatabase)
        IDatabaseConnection connection = factory.createConnection()

        ICandidatoDao candidatoDao = new CandidatoDao(connection)
        IFormacaoDao formacaoDao = new FormacaoDao(connection, candidatoDao)

        candidatoFormacaoService = new CandidatoFormacaoService(formacaoDao)
        candidatoFormacaoController = new CandidatoFormacaoController(candidatoFormacaoService)
    }

    void exibirMenuCandidato(Reader reader) {
        while (true) {
            println "Menu formações do Candidato:"
            println "1. Listar formações do candidato"
            println "2. adicionar formação"
            println "3. Atualizar formação"
            println "4. excluir formação"
            println "5. Voltar"

            int opcao = Integer.parseInt(reader.readLine())
            switch (opcao) {
                case 1:
                    listarFormacaoDoCandidato(reader)
                    break
                case 2:
                    adicionarFormacao(reader)
                    break
                case 3:
                    atualizarFormacao(reader)
                    break
                case 4:
                    exluirFormacao(reader)
                    break
                case 5:
                    return
                default:
                    println "Opção inválida. Tente novamente."
            }
        }
    }

    Formacao criarFormacao(Reader reader) {
        println "Digite o id do candidato: "
        Integer idCandidato = Integer.parseInt(reader.readLine())

        println "Digite o nome da instituição: "
        String instituicao = reader.readLine()

        println "Digite o nome do curso: "
        String curso = reader.readLine()

        println "Digite o nivel da formação: "
        Integer nivel = Integer.parseInt(reader.readLine())

        println "Digite o ano de conclusao da formação: "
        String anoConclusao = reader.readLine()

        return new Formacao(
                idCandidato,
                instituicao,
                curso,
                nivel,
                anoConclusao
        )
    }

    void adicionarFormacao(Reader reader) {
        Formacao formacao = criarFormacao(reader)
        candidatoFormacaoController.adicionarFormacao(formacao)
    }

    void atualizarFormacao(Reader reader) {
        println "Digite o id da formação que deseja atualizar: "
        Integer id = Integer.parseInt(reader.readLine())

        Formacao formacao = criarFormacao(reader)
        formacao.setId(id)

        candidatoFormacaoController.atualizarFormacao(formacao)
    }

    void exluirFormacao(Reader reader) {
        println "Digite o id da formação que deseja excluir: "
        Integer id = Integer.parseInt(reader.readLine())

        candidatoFormacaoController.excluirFormacao(id)
    }

    void listarFormacaoDoCandidato(Reader reader) {
        println "Digite o id da candidato: "
        Integer id = Integer.parseInt(reader.readLine())

        List<Formacao> formacaos = candidatoFormacaoController.listarFormacoesPorCandidato(id)

        formacaos.each { formacao ->
            println "============================"
            println "Formação: ID:${formacao.id}"
            println "Curso: ${formacao.curso}"
            println "Instituição: ${formacao.instituicao}"
            println "Ano de Conslusão: ${formacao.anoConclusao}"
            println ""
        }
    }
}
