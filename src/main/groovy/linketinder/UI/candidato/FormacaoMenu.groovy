package linketinder.UI.candidato

import linketinder.db.ConfigDatabase
import linketinder.dao.candidato.*
import linketinder.dao.curtida.CurtidaDao
import linketinder.dao.curtida.ICurtidaDao
import linketinder.dao.empresa.EmpresaDao
import linketinder.dao.empresa.IEmpresaDao
import linketinder.dao.vaga.IVagaDao
import linketinder.dao.vaga.VagaDao
import linketinder.db.DatabaseConnection
import linketinder.db.IDatabaseConnection
import linketinder.entity.Formacao
import linketinder.service.candidato.CandidatoFormacaoService
import linketinder.service.candidato.CandidatoService
import linketinder.service.candidato.ICandidatoFormacaoService

class FormacaoMenu {

    private ICandidatoFormacaoService candidatoFormacaoService

    FormacaoMenu() {
        ConfigDatabase config = new ConfigDatabase()
        IDatabaseConnection databaseConnection = new DatabaseConnection(config)

        ICandidatoDao candidatoDao = new CandidatoDao(databaseConnection)
        IFormacaoDao formacaoDao = new FormacaoDao(databaseConnection, candidatoDao)

        candidatoFormacaoService = new CandidatoFormacaoService(formacaoDao)
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
        candidatoFormacaoService.adicionarFormacao(formacao)
    }

    void atualizarFormacao(Reader reader) {
        println "Digite o id da formação que deseja atualizar: "
        Integer id = Integer.parseInt(reader.readLine())

        Formacao formacao = criarFormacao(reader)
        formacao.setId(id)

        candidatoFormacaoService.atualizarFormacao(formacao)
    }

    void exluirFormacao(Reader reader) {
        println "Digite o id da formação que deseja excluir: "
        Integer id = Integer.parseInt(reader.readLine())

        candidatoFormacaoService.excluirFormacao(id)
    }

    void listarFormacaoDoCandidato(Reader reader) {
        println "Digite o id da candidato: "
        Integer id = Integer.parseInt(reader.readLine())

        List<Formacao> formacaos = candidatoFormacaoService.listarFormacoesPorCandidato(id)

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
