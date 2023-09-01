package UI.candidato

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
import entity.Formacao
import service.CandidatoService

class FormacaoMenu {

    CandidatoService service

    FormacaoMenu() {
        IDatabaseConnection databaseConnection = new DatabaseConnection()
        ICandidatoDao candidatoDao = new CandidatoDao(databaseConnection)
        ICandidatoCompetenciaDao candidatoCompetenciaDao = new CandidatoCompetenciaDao(databaseConnection)
        IExperienciaDao experienciaDao = new ExperienciaDao(databaseConnection)
        IFormacaoDao formacaoDao = new FormacaoDao(databaseConnection)

        service = new CandidatoService(candidatoDao, candidatoCompetenciaDao, experienciaDao, formacaoDao)
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

     Formacao criarFormacao(Reader reader){
        println "Digite o id do candidato: "
        Integer idCandidato = Integer.parseInt(reader.readLine())

        println "Digite o nome da instituição: "
        String instituicao = reader.readLine()

        println "Digite o nome do curso: "
        String curso = reader.readLine()

        println "Digite o nivel da formação: "
        String nivel = reader.readLine()

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

    void adicionarFormacao(Reader reader){
        Formacao formacao = criarFormacao(reader)
        service.adicionarFormacao(formacao)
    }

    void atualizarFormacao(Reader reader){
        println "Digite o id da formação que deseja atualizar: "
        Integer id = Integer.parseInt(reader.readLine())

        Formacao formacao = criarFormacao(reader)
        formacao.setId(id)

        service.atualizarFormacao(formacao)
    }

    void exluirFormacao(Reader reader){
        println "Digite o id da formação que deseja excluir: "
        Integer id = Integer.parseInt(reader.readLine())


        service.excluirFormacao(id)
    }

    void listarFormacaoDoCandidato(Reader reader){
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
