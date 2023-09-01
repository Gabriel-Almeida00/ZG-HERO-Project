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
import entity.Candidato
import entity.dto.CandidatoDTO
import service.CandidatoService

import java.text.SimpleDateFormat

class CandidatoMenu {

    CandidatoService candidatoService
    CompetenciaCandidatoMenu competenciaCandidatoMenu
    ExperienciaMenu experienciaCandidatoMenu
    FormacaoMenu formacaoMenu

    CandidatoMenu() {
        IDatabaseConnection databaseConnection = new DatabaseConnection()
        ICandidatoDao candidatoDao = new CandidatoDao(databaseConnection)
        ICandidatoCompetenciaDao candidatoCompetenciaDao = new CandidatoCompetenciaDao(databaseConnection)
        IExperienciaDao experienciaDao = new ExperienciaDao(databaseConnection)
        IFormacaoDao formacaoDao = new FormacaoDao(databaseConnection)

        formacaoMenu = new FormacaoMenu()
        experienciaCandidatoMenu = new ExperienciaMenu()
        competenciaCandidatoMenu = new CompetenciaCandidatoMenu()
        candidatoService = new CandidatoService(candidatoDao, candidatoCompetenciaDao, experienciaDao, formacaoDao)
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
            println "8. Voltar"

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
        candidatos.each {
            println "======================="
            println "ID: ${it.id}"
            println "Descrição: ${it.descricao}"
            println "Formações: ${it.formacoes}"
            println "Experiencias: ${it.experiencias}"
            println "Competencias: ${it.competencias}"
            println ""

        }
    }

    void deletarCandidato(Reader reader) {
        println "Digite o ID do candidato que deseja deletar:"
        Integer idCandidato = Integer.parseInt(reader.readLine())

        candidatoService.deletarCandidato(idCandidato)
    }

}
