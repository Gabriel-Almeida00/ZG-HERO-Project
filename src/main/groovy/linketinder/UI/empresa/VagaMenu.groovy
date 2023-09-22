package linketinder.UI.empresa

import linketinder.config.Config
import linketinder.dao.candidato.CandidatoDao
import linketinder.dao.candidato.ICandidatoDao
import linketinder.dao.curtida.CurtidaDao
import linketinder.dao.curtida.ICurtidaDao
import linketinder.dao.empresa.EmpresaDao
import linketinder.dao.empresa.IEmpresaDao
import linketinder.dao.vaga.IVagaCompetenciaDao
import linketinder.dao.vaga.IVagaDao
import linketinder.dao.vaga.VagaCompetenciaDao
import linketinder.dao.vaga.VagaDao
import linketinder.db.DatabaseConnection
import linketinder.db.IDatabaseConnection
import linketinder.entity.Vaga
import linketinder.entity.dto.CandidatoQueCurtiuVagaDTO
import linketinder.entity.dto.VagaDTO
import linketinder.service.VagaService

class VagaMenu {

    VagaService vagaService
    CompetenciaVagaMenu competenciaVagaMenu

    VagaMenu() {
        Config config = new Config()
        IDatabaseConnection databaseConnection = new DatabaseConnection(config)
        IVagaDao vagaDao = new VagaDao(databaseConnection)
        IVagaCompetenciaDao vagaCompetenciaDao = new VagaCompetenciaDao(databaseConnection, vagaDao)
        ICandidatoDao candidatoDao = new CandidatoDao(databaseConnection)
        IEmpresaDao empresaDao = new EmpresaDao(databaseConnection)
        ICurtidaDao curtidaDao = new CurtidaDao(databaseConnection, candidatoDao, vagaDao, empresaDao)

        vagaService = new VagaService(vagaDao, vagaCompetenciaDao, curtidaDao)
        competenciaVagaMenu = new CompetenciaVagaMenu()
    }

    void exibirMenuVaga(Reader reader) {
        while (true) {
            println "Menu Vaga:"
            println "1. Listar Vagas"
            println "2. Cadastrar Vaga"
            println "3. Atualizar Vaga"
            println "4. Deletar Vaga"
            println "5. Gerenciar Competencia da vaga"
            println "6. Listar Candidatos que curtiram vaga"
            println "7. Voltar"

            int opcao = Integer.parseInt(reader.readLine())
            switch (opcao) {
                case 1:
                    listarVagasDaEmpresa(reader)
                    break
                case 2:
                    adicionarVaga(reader)
                    break
                case 3:
                    atualizarVaga(reader)
                    break
                case 4:
                    excluirVaga(reader)
                    break
                case 5:
                    competenciaVagaMenu.exibirMenuVaga(reader)
                    break
                case 6:
                    listarCandidatosQueCurtiramVaga(reader)
                    break
                case 7:
                    return
                default:
                    println "Opção inválida. Tente novamente."
            }
        }
    }

    Vaga criarVaga(Reader reader) {
        println "Digite o id da empresa: "
        Integer idEmpresa = Integer.parseInt(reader.readLine())

        println "Digite o nome da vaga: "
        String nome = reader.readLine()

        println "Digite a descrição da vaga: "
        String descricao = reader.readLine()

        println "Digite a cidade da vaga: "
        String cidade = reader.readLine()

        println "Digite a formação exigida da vaga: "
        Integer formacaoMinima = Integer.parseInt(reader.readLine())

        println "Digite a experiencia exigida da vaga"
        Integer experienciaMinima = Integer.parseInt(reader.readLine())

        return new Vaga(
                idEmpresa,
                nome,
                descricao,
                cidade,
                formacaoMinima,
                experienciaMinima
        )
    }

    void listarVagas() {
        List<VagaDTO> vagas = vagaService.listarTodasVagas()

        vagas.each { vaga ->
            println "==========="
            println "Id: ${vaga.id}"
            println "Nome :${vaga.nome}"
            println "Descrição: ${vaga.descricao}"
            println "Competências:"

            vaga.nomeCompetencia.each { competencia ->
                println "  - ${competencia}"
            }
            println()
        }
    }

    void listarVagasDaEmpresa(Reader reader) {
        println "Digite o id da empresa: "
        Integer id = Integer.parseInt(reader.readLine())

        List<VagaDTO> vagas = vagaService.listarVagasDaEmpresa(id)

        vagas.each { vaga ->
            println "==========="
            println "Id: ${vaga.id}"
            println "Nome :${vaga.nome}"
            println "Descrição: ${vaga.descricao}"

            println "Competências:"
            vaga.nomeCompetencia.each {competenca ->
                println(" - ${competenca}")
            }
            println()
        }
    }

    void adicionarVaga(Reader reader) {
        Vaga vaga = criarVaga(reader)
        vagaService.adicionarVaga(vaga)
    }

    void atualizarVaga(Reader reader) {
        println "Digite o id da vaga: "
        Integer id = Integer.parseInt(reader.readLine())

        Vaga vaga = criarVaga(reader)
        vaga.setId(id)

        vagaService.atualizarVaga(vaga)
    }

    void excluirVaga(Reader reader) {
        println "Digite o id da vaga: "
        Integer id = Integer.parseInt(reader.readLine())

        vagaService.excluirVaga(id)
    }

   void listarCandidatosQueCurtiramVaga(Reader reader){
       println "Digite o id da vaga: "
       Integer id = Integer.parseInt(reader.readLine())

       List<CandidatoQueCurtiuVagaDTO> candidatos = vagaService.listarCandidatosQueCurtiramVaga(id)
       candidatos.forEach { candidato ->
           println("==============")
           println("ID: ${candidato.getIdCandidato()}")
           println("Descrição: ${candidato.getDescricao()}")

           candidato.nomes.forEach { competencia ->
               println("Competencia:  $competencia")
           }
       }
   }
}
