package UI.candidato

import UI.empresa.VagaMenu
import dao.candidato.CandidatoCompetenciaDao
import dao.candidato.CandidatoDao
import dao.candidato.ExperienciaDao
import dao.candidato.FormacaoDao
import dao.candidato.ICandidatoCompetenciaDao
import dao.candidato.ICandidatoDao
import dao.candidato.IExperienciaDao
import dao.candidato.IFormacaoDao
import dao.curtida.CurtidaDao
import dao.curtida.ICurtidaDao
import dao.vaga.IVagaDao
import dao.vaga.VagaDao
import db.DatabaseConnection
import db.IDatabaseConnection
import entity.Candidato
import entity.VagaCompetencia
import entity.VagaCurtida
import entity.dto.CandidatoDTO
import service.CandidatoService

import java.text.SimpleDateFormat

class CandidatoMenu {

    CandidatoService candidatoService
    CompetenciaCandidatoMenu competenciaCandidatoMenu
    ExperienciaMenu experienciaCandidatoMenu
    FormacaoMenu formacaoMenu
    VagaMenu vagaMenu

    CandidatoMenu() {
        IDatabaseConnection databaseConnection = new DatabaseConnection()
        ICandidatoDao candidatoDao = new CandidatoDao(databaseConnection)
        ICandidatoCompetenciaDao candidatoCompetenciaDao = new CandidatoCompetenciaDao(databaseConnection)
        IExperienciaDao experienciaDao = new ExperienciaDao(databaseConnection)
        IFormacaoDao formacaoDao = new FormacaoDao(databaseConnection)
        IVagaDao vagaDao = new VagaDao(databaseConnection)
        ICurtidaDao curtidaDao = new CurtidaDao(databaseConnection)

        formacaoMenu = new FormacaoMenu()
        experienciaCandidatoMenu = new ExperienciaMenu()
        competenciaCandidatoMenu = new CompetenciaCandidatoMenu()
        vagaMenu = new VagaMenu()
        candidatoService = new CandidatoService(candidatoDao, candidatoCompetenciaDao, experienciaDao, formacaoDao, vagaDao, curtidaDao)
    }

    void exibirMenuCandidato(Reader reader) {
        while (true) {
            println "Menu Candidato:"
            println "1. Listar candidatos"
            println "2. Cadastrar candidato"
            println "3. Atualizar candidato"
            println "4. Deletar candidato"
            println "5. Gerenciar Competencias do candidato"
            println "6. Gerenciar experiencias do candidato"
            println "7. Gerenciar formações do candidato"
            println "8. Curtir Vaga"
            println "9. Voltar"

            int opcao = Integer.parseInt(reader.readLine())
            switch (opcao) {
                case 1:
                    listarCandidatos()
                    break
                case 2:
                    cadastrarCandidato(reader)
                    break
                case 3:
                    atualizarCandidato(reader)
                    break
                case 4:
                    deletarCandidato(reader)
                    break
                case 5:
                    competenciaCandidatoMenu.exibirMenuCandidato(reader)
                    break
                case 6:
                    experienciaCandidatoMenu.exibirMenuCandidato(reader)
                    break
                case 7:
                    formacaoMenu.exibirMenuCandidato(reader)
                    break
                case 8:
                    curtirVaga(reader)
                    break
                case 9:
                    return
                default:
                    println "Opção inválida. Tente novamente."
            }
        }
    }

    Candidato criarCandidato(Reader reader) {
        println "Digite o nome do candidato:"
        String nome = reader.readLine()

        println "Digite o sobrenome do candidato:"
        String sobrenome = reader.readLine()

        println "Digite a data de nascimento (formato dd/MM/yyyy):"
        String dataNascimentoString = reader.readLine()
        Date dataNascimento = new SimpleDateFormat("dd/MM/yyyy").parse(dataNascimentoString)

        println "Digite o email do candidato:"
        String email = reader.readLine()

        println "Digite o CPF do candidato:"
        String cpf = reader.readLine()

        println "Digite o país do candidato:"
        String pais = reader.readLine()

        println "Digite o CEP do candidato:"
        String cep = reader.readLine()

        println "Digite a descrição do candidato:"
        String descricao = reader.readLine()

        println "Digite a senha do candidato:"
        String senha = reader.readLine()

        return new Candidato(
                nome,
                sobrenome,
                dataNascimento,
                email,
                cpf,
                pais,
                cep,
                descricao,
                senha
        )
    }

    VagaCurtida criarVagaCurtida(Reader reader) {
        println "Digite o id do candidato: "
        Integer idCandidato = Integer.parseInt(reader.readLine())

        println "Digite o id da vaga: "
        Integer idVaga = Integer.parseInt(reader.readLine())

        return new VagaCurtida(
                idCandidato, idVaga
        )
    }

    void cadastrarCandidato(Reader reader) {
        Candidato candidato = criarCandidato(reader)
        candidatoService.cadastrarCandidato(candidato)
    }

    void atualizarCandidato(Reader reader) {
        println "Digite o ID do candidato que deseja atualizar:"
        Integer idCandidato = Integer.parseInt(reader.readLine())

        Candidato candidato = criarCandidato(reader)
        candidato.setId(idCandidato)

        candidatoService.atualizarCandidato(candidato)
    }

    void listarCandidatos() {
        List<CandidatoDTO> candidatos = candidatoService.listarCandidatos()
        candidatos.each { candidato ->
            println "======================="
            println "ID: ${candidato.id}"
            println "Descrição:"
            println "  - ${candidato.descricao}"

            println "Formações:"
            candidato.formacoes.each { formacao ->
                println "  - ${formacao.curso} - (${formacao.anoConclusao})"
            }

            println "Experiências:"
            candidato.experiencias.each { experiencia ->
                println "  - ${experiencia.cargo} - ${experiencia.nivel}"
            }

            println "Competências:"
            candidato.competencias.each { competencia ->
                println "  - ${competencia.nome} - ${competencia.nivel}"
            }
        }
        println ""
    }

    void deletarCandidato(Reader reader) {
        println "Digite o ID do candidato que deseja deletar:"
        Integer idCandidato = Integer.parseInt(reader.readLine())

        candidatoService.deletarCandidato(idCandidato)
    }

    void curtirVaga(Reader reader) {
        vagaMenu.listarVagas()
        VagaCurtida vagaCurtida = criarVagaCurtida(reader)
        candidatoService.curtirVaga(vagaCurtida.getIdCandidata(), vagaCurtida.getIdVaga())
    }

}
