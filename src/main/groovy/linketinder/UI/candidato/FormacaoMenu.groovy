package linketinder.UI.candidato

import linketinder.dao.candidato.CandidatoCompetenciaDao
import linketinder.dao.candidato.CandidatoDao
import linketinder.dao.candidato.ExperienciaDao
import linketinder.dao.candidato.FormacaoDao
import linketinder.dao.candidato.ICandidatoCompetenciaDao
import linketinder.dao.candidato.ICandidatoDao
import linketinder.dao.candidato.IExperienciaDao
import linketinder.dao.candidato.IFormacaoDao
import linketinder.dao.curtida.CurtidaDao
import linketinder.dao.curtida.ICurtidaDao
import linketinder.dao.vaga.IVagaDao
import linketinder.dao.vaga.VagaDao
import linketinder.db.DatabaseConnection
import linketinder.db.IDatabaseConnection
import linketinder.entity.Formacao
import linketinder.service.CandidatoService

class FormacaoMenu {

    CandidatoService service

    FormacaoMenu() {
        IDatabaseConnection databaseConnection = new DatabaseConnection()
        ICandidatoDao candidatoDao = new CandidatoDao(databaseConnection)
        ICandidatoCompetenciaDao candidatoCompetenciaDao = new CandidatoCompetenciaDao(databaseConnection)
        IExperienciaDao experienciaDao = new ExperienciaDao(databaseConnection)
        IFormacaoDao formacaoDao = new FormacaoDao(databaseConnection)
        IVagaDao vagaDao = new VagaDao(databaseConnection)
        ICurtidaDao curtidaDao = new CurtidaDao(databaseConnection)

        service = new CandidatoService(candidatoDao, candidatoCompetenciaDao, experienciaDao, formacaoDao, vagaDao, curtidaDao)
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
        service.adicionarFormacao(formacao)
    }

    void atualizarFormacao(Reader reader) {
        println "Digite o id da formação que deseja atualizar: "
        Integer id = Integer.parseInt(reader.readLine())

        Formacao formacao = criarFormacao(reader)
        formacao.setId(id)

        service.atualizarFormacao(formacao)
    }

    void exluirFormacao(Reader reader) {
        println "Digite o id da formação que deseja excluir: "
        Integer id = Integer.parseInt(reader.readLine())


        service.excluirFormacao(id)
    }

    void listarFormacaoDoCandidato(Reader reader) {
        println "Digite o id da candidato: "
        Integer id = Integer.parseInt(reader.readLine())

        List<Formacao> formacaos = service.listarFormacoesPorCandidato(id)

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
