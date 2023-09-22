package linketinder.UI.candidato

import linketinder.config.Config
import linketinder.dao.candidato.*
import linketinder.dao.curtida.CurtidaDao
import linketinder.dao.curtida.ICurtidaDao
import linketinder.dao.empresa.EmpresaDao
import linketinder.dao.empresa.IEmpresaDao
import linketinder.dao.vaga.IVagaDao
import linketinder.dao.vaga.VagaDao
import linketinder.db.DatabaseConnection
import linketinder.db.IDatabaseConnection
import linketinder.entity.Experiencia
import linketinder.service.CandidatoService

class ExperienciaMenu {

    CandidatoService service

    ExperienciaMenu() {
        Config config = new Config()
        IDatabaseConnection databaseConnection = new DatabaseConnection(config)

        ICandidatoDao candidatoDao = new CandidatoDao(databaseConnection)
        ICandidatoCompetenciaDao candidatoCompetenciaDao = new CandidatoCompetenciaDao(databaseConnection, candidatoDao)
        IExperienciaDao experienciaDao = new ExperienciaDao(databaseConnection, candidatoDao)
        IFormacaoDao formacaoDao = new FormacaoDao(databaseConnection, candidatoDao)
        IVagaDao vagaDao = new VagaDao(databaseConnection)
        IEmpresaDao empresaDao = new EmpresaDao(databaseConnection)
        ICurtidaDao curtidaDao = new CurtidaDao(databaseConnection, candidatoDao, vagaDao, empresaDao)

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
