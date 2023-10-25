package linketinder.view.candidato

import linketinder.controller.candidato.CandidatoExperienciaController
import linketinder.dao.candidato.CandidatoDao
import linketinder.dao.candidato.ExperienciaDao
import linketinder.dao.candidato.ICandidatoDao
import linketinder.dao.candidato.IExperienciaDao
import linketinder.db.ConfigDatabase
import linketinder.db.IDatabaseConnection
import linketinder.db.factory.IDatabaseConnectionFactory
import linketinder.model.Experiencia
import linketinder.service.candidato.CandidatoExperienciaService
import linketinder.service.candidato.ICandidatoExperienciaService
import linketinder.db.factory.DatabaseFactory

class ExperienciaView {

    private ICandidatoExperienciaService candidatoExperienciaService
    private CandidatoExperienciaController candidatoExperienciaController

    ExperienciaView(ConfigDatabase configDatabase) {
        DatabaseFactory databaseFactory = new DatabaseFactory()
        IDatabaseConnectionFactory factory = databaseFactory.createConnectionFactory(configDatabase)
        IDatabaseConnection connection = factory.createConnection()

        ICandidatoDao candidatoDao = new CandidatoDao(connection)
        IExperienciaDao experienciaDao = new ExperienciaDao(connection, candidatoDao)

        candidatoExperienciaService = new CandidatoExperienciaService(experienciaDao)
        candidatoExperienciaController = new CandidatoExperienciaController(candidatoExperienciaService)
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
        Integer nivel = Integer.parseInt(reader.readLine())

        return new Experiencia(
                idCandidato,
                cargo,
                empresa,
                nivel
        )
    }

    void adicionarExperiencia(Reader reader) {
        Experiencia experiencia = criarExperiencia(reader)
        candidatoExperienciaController.adicionarExperiencia(experiencia)
    }

    void atualizarExperiencia(Reader reader) {
        println "Digite o Id da experiencia que deseja atualiza: "
        Integer id = Integer.parseInt(reader.readLine())

        Experiencia experiencia = criarExperiencia(reader)
        experiencia.setId(id)

        candidatoExperienciaController.atualizarExperiencia(experiencia)
    }

    void excluirExperiencia(Reader reader) {
        println "Digite o Id da experiencia que deseja excluir: "
        Integer id = Integer.parseInt(reader.readLine())

        candidatoExperienciaController.excluirExperiencia(id)
    }

    void listarExperienciaDoCandidato(Reader reader) {
        println "Digite o ID do candidato:"
        Integer idCandidato = Integer.parseInt(reader.readLine())

        List<Experiencia> experiencias = candidatoExperienciaController.listarExperienciasPorCandidato(idCandidato)

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