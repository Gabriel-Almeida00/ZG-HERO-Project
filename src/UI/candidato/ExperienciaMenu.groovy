package UI.candidato

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
import entity.Experiencia
import service.CandidatoService


class ExperienciaMenu {

    CandidatoService service

    ExperienciaMenu() {
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
            println "Menu Experiencias do Candidato:"
            println "1. Listar Experiencias do candidato"
            println "2. adicionar Experiencias"
            println "3. Atualizar Experiencias"
            println "4. excluir Experiencias"
            println "5. Voltar"

            int opcao = Integer.parseInt(reader.readLine())
            switch (opcao) {
                case 1:
                    listarExperienciaDoCandidato(reader)
                    break
                case 2:
                    adicionarExperiencia(reader)
                    break
                case 3:
                    atualizarExperiencia(reader)
                    break
                case 4:
                    excluirExperiencia(reader)
                    break
                case 5:
                    return
                default:
                    println "Opção inválida. Tente novamente."
            }
        }
    }

    Experiencia criarExperiencia(Reader reader) {
        println "Digite o Id do candidato:"
        Integer idCandidato = Integer.parseInt(reader.readLine())

        println "Digite o cargo da experiencia:"
        String cargo = reader.readLine()

        println "Digite a empresa da experiencia:"
        String empresa = reader.readLine()


        println "Digite o nivel:"
        String nivel = reader.readLine()

        return new Experiencia(
                idCandidato,
                cargo,
                empresa,
                nivel
        )
    }

    void adicionarExperiencia(Reader reader) {
        Experiencia experiencia = criarExperiencia(reader)
        service.adicionarExperiencia(experiencia)
    }

    void atualizarExperiencia(Reader reader) {
        println "Digite o Id da experiencia que deseja atualiza: "
        Integer id = Integer.parseInt(reader.readLine())

        Experiencia experiencia = criarExperiencia(reader)
        experiencia.setId(id)

        service.atualizarExperiencia(experiencia)
    }

    void excluirExperiencia(Reader reader) {
        println "Digite o Id da experiencia que deseja excluir: "
        Integer id = Integer.parseInt(reader.readLine())


        service.excluirExperiencia(id)
    }

    void listarExperienciaDoCandidato(Reader reader) {
        println "Digite o ID do candidato:"
        Integer idCandidato = Integer.parseInt(reader.readLine())

        List<Experiencia> experiencias = service.listarExperienciasPorCandidato(idCandidato)

        experiencias.each { experiencia ->
            println "============================"
            println "Experiencia: ID:${experiencia.id}"
            println "Cargo: ${experiencia.cargo}"
            println "Nível: ${experiencia.nivel}"
            println "Empresa: ${experiencia.empresa}"
            println ""
        }
    }

}
