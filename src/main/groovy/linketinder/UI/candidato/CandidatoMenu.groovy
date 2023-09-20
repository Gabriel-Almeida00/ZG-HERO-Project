package linketinder.UI.candidato

import linketinder.UI.empresa.VagaMenu
import linketinder.dao.candidato.*
import linketinder.dao.curtida.CurtidaDao
import linketinder.dao.curtida.ICurtidaDao
import linketinder.dao.match.IMatchDao
import linketinder.dao.match.MatchDao
import linketinder.dao.vaga.IVagaDao
import linketinder.dao.vaga.VagaDao
import linketinder.entity.Candidato
import linketinder.entity.VagaCurtida
import linketinder.entity.dto.CandidatoDTO
import linketinder.entity.dto.EmpresaDTO
import linketinder.entity.dto.MatchEmpresaDTO
import linketinder.service.CandidatoService
import linketinder.service.MatchService

import java.text.SimpleDateFormat

class CandidatoMenu {

    CandidatoService candidatoService
    MatchService matchService
    CompetenciaCandidatoMenu competenciaCandidatoMenu
    ExperienciaMenu experienciaCandidatoMenu
    FormacaoMenu formacaoMenu
    VagaMenu vagaMenu

    CandidatoMenu() {
        ICandidatoDao candidatoDao = new CandidatoDao()
        ICandidatoCompetenciaDao candidatoCompetenciaDao = new CandidatoCompetenciaDao()
        IExperienciaDao experienciaDao = new ExperienciaDao()
        IFormacaoDao formacaoDao = new FormacaoDao()
        IVagaDao vagaDao = new VagaDao()
        ICurtidaDao curtidaDao = new CurtidaDao()
        IMatchDao matchDao = new MatchDao()

        formacaoMenu = new FormacaoMenu()
        experienciaCandidatoMenu = new ExperienciaMenu()
        competenciaCandidatoMenu = new CompetenciaCandidatoMenu()
        vagaMenu = new VagaMenu()
        matchService = new MatchService(matchDao)
        candidatoService = new CandidatoService(candidatoDao, candidatoCompetenciaDao, experienciaDao, formacaoDao, vagaDao, curtidaDao)
    }

    void exibirMenuCandidato(Reader reader) {
        while (true) {
            println "Menu Candidato:"
            println "1. Listar candidatos"
            println "2. Cadastrar candidato"
            println "3. Atualizar candidato"
            println "4. Deletar candidato"
            println "5. Gerenciar Competencia do candidato"
            println "6. Gerenciar experiencias do candidato"
            println "7. Gerenciar formações do candidato"
            println "8. Curtir Vaga"
            println "9. Verificar Match"
            println "10. Listar empresas que curtiram candidato"
            println "11. Voltar"

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
                    verificaMatch(reader)
                    break
                case 10:
                    listarEmpresasQueCurtiramCandidato(reader)
                    break
                case 11:
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
            println "Nome: ${candidato.nome}"
            println "Descrição:"
            println "  - ${candidato.descricao}"

            println "Competências:"
            candidato.competencias.each { competencia ->
                println "  - ${competencia}"
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
        Integer idCandidato = vagaCurtida.getIdCandidata()
        Integer idVaga = vagaCurtida.getIdVaga()

        candidatoService.curtirVaga(idCandidato, idVaga)
    }

    void listarEmpresasQueCurtiramCandidato(Reader reader) {
        println("Digite o id do candidato: ")
        Integer id = Integer.parseInt(reader.readLine())
        List<EmpresaDTO> empresas = candidatoService.listarEmpresasQueCurtiramCandidato(id)

        empresas.forEach { empresa ->
            println("==========")
            println("Empresas: ")
            println("Descrição:  ${empresa.getDescricaoEmpresa()}")
            println("País:  ${empresa.getPais()}")
            println()
        }
    }

    void verificaMatch(Reader reader) {
        println("Digite o id do candidato: ")
        Integer id = Integer.parseInt(reader.readLine())
        List<MatchEmpresaDTO> matchs = matchService.encontrarMatchesPeloCandidato(id)

        matchs.each { match ->
            println "============================"
            println "DEU MATCH"
            println "A seguinte empresa curtiu seu perfil: "
            println "Nome :  ${match.getNomeEmpresa()}"
            println "Descrição: ${match.getDescricaoEmpresa()}"
            println "Vaga que o candidato curitu: "
            println "Nome: ${match.getNomeVaga()}"
            println "Descrição: ${match.getDescricaoVaga()}"
            println ""
        }
    }
}
