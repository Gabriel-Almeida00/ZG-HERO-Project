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
import linketinder.entity.Experiencia
import linketinder.service.candidato.CandidatoFormacaoService
import linketinder.service.candidato.CandidatoService
import linketinder.service.candidato.ICandidatoFormacaoService

class ExperienciaMenu {

   private ICandidatoFormacaoService candidatoFormacaoService

    ExperienciaMenu() {
        ConfigDatabase config = new ConfigDatabase()
        IDatabaseConnection databaseConnection = new DatabaseConnection(config)

        ICandidatoDao candidatoDao = new CandidatoDao(databaseConnection)
        IFormacaoDao formacaoDao = new FormacaoDao(databaseConnection, candidatoDao)

        candidatoFormacaoService = new CandidatoFormacaoService(formacaoDao)
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
        candidatoFormacaoService.adicionarExperiencia(experiencia)
    }

    void atualizarExperiencia(Reader reader) {
        println "Digite o Id da experiencia que deseja atualiza: "
        Integer id = Integer.parseInt(reader.readLine())

        Experiencia experiencia = criarExperiencia(reader)
        experiencia.setId(id)

        candidatoFormacaoService.atualizarExperiencia(experiencia)
    }

    void excluirExperiencia(Reader reader) {
        println "Digite o Id da experiencia que deseja excluir: "
        Integer id = Integer.parseInt(reader.readLine())

        candidatoFormacaoService.excluirExperiencia(id)
    }

    void listarExperienciaDoCandidato(Reader reader) {
        println "Digite o ID do candidato:"
        Integer idCandidato = Integer.parseInt(reader.readLine())

        List<Experiencia> experiencias = candidatoFormacaoService.listarExperienciasPorCandidato(idCandidato)

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
