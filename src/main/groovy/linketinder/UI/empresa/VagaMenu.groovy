package linketinder.UI.empresa

import linketinder.UI.validation.DatabaseFactory
import linketinder.db.ConfigDatabase
import linketinder.dao.candidato.CandidatoDao
import linketinder.dao.candidato.ICandidatoDao
import linketinder.dao.curtida.CurtidaDao
import linketinder.dao.curtida.ICurtidaDao
import linketinder.dao.empresa.EmpresaDao
import linketinder.dao.empresa.IEmpresaDao
import linketinder.dao.vaga.IVagaDao
import linketinder.dao.vaga.VagaDao

import linketinder.db.IDatabaseConnection
import linketinder.db.factory.IDatabaseConnectionFactory
import linketinder.entity.Vaga
import linketinder.entity.dto.CandidatoQueCurtiuVagaDTO
import linketinder.entity.dto.VagaDTO
import linketinder.service.curtida.CurtidaService
import linketinder.service.curtida.ICurtidaService
import linketinder.service.vaga.IVagaService
import linketinder.service.vaga.VagaService

class VagaMenu {
    private IVagaService vagaService
    private ICurtidaService curtidaService
    private CompetenciaVagaMenu competenciaVagaMenu

    VagaMenu(ConfigDatabase configDatabase) {
        DatabaseFactory databaseFactory = new DatabaseFactory()
        IDatabaseConnectionFactory factory = databaseFactory.createConnectionFactory(configDatabase)
        IDatabaseConnection connection = factory.createConnection()

        IVagaDao vagaDao = new VagaDao(connection)
        ICandidatoDao candidatoDao = new CandidatoDao(connection)
        IEmpresaDao empresaDao = new EmpresaDao(connection)
        ICurtidaDao curtidaDao = new CurtidaDao(connection, candidatoDao, vagaDao, empresaDao)

        vagaService = new VagaService(vagaDao)
        curtidaService = new CurtidaService(curtidaDao, vagaDao)
        competenciaVagaMenu = new CompetenciaVagaMenu(configDatabase)
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

       List<CandidatoQueCurtiuVagaDTO> candidatos = curtidaService.listarCandidatosQueCurtiramVaga(id)
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
