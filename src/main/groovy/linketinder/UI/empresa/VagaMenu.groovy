package linketinder.UI.empresa


import linketinder.dao.vaga.IVagaCompetenciaDao
import linketinder.dao.vaga.IVagaDao
import linketinder.dao.vaga.VagaCompetenciaDao
import linketinder.dao.vaga.VagaDao
import linketinder.db.DatabaseConnection
import linketinder.db.IDatabaseConnection
import linketinder.entity.Vaga
import linketinder.entity.dto.VagaDTO
import linketinder.service.VagaService

class VagaMenu {

    VagaService vagaService
    CompetenciaVagaMenu competenciaVagaMenu

    VagaMenu() {
        IDatabaseConnection databaseConnection = new DatabaseConnection()
        IVagaCompetenciaDao vagaCompetenciaDao = new VagaCompetenciaDao(databaseConnection)
        IVagaDao vagaDao = new VagaDao(databaseConnection)

        vagaService = new VagaService(vagaDao, vagaCompetenciaDao)
        competenciaVagaMenu = new CompetenciaVagaMenu()
    }

    void exibirMenuVaga(Reader reader) {
        while (true) {
            println "Menu Vaga:"
            println "1. Listar Vagas"
            println "2. Cadastrar Vaga"
            println "3. Atualizar Vaga"
            println "4. Deletar Vaga"
            println "5. Gerenciar Competencias da vaga"
            println "6. Voltar"

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
                    return
                default:
                    println "Opção inválida. Tente novamente."
            }
        }
    }

    Vaga criarVaga(Reader reader){
        println "Digite o id da empresa: "
        Integer idEmpresa = Integer.parseInt(reader.readLine())

        println "Digite o nome da vaga: "
        String nome = reader.readLine()

        println "Digite a descrição da vaga: "
        String descricao = reader.readLine()

        println "Digite a cidade da vaga: "
        String cidade = reader.readLine()

        println "Digite a formação exigida da vaga: "
        String formacaoMinima = reader.readLine()

        println "Digite a experiencia exigida da vaga"
        String experienciaMinima = reader.readLine()

        return  new Vaga(
                idEmpresa,
                nome,
                descricao,
                cidade,
                formacaoMinima,
                experienciaMinima
        )
    }

    void listarVagas(){
        List<VagaDTO> vagas = vagaService.listarTodasVagas()

        vagas.each {vaga ->
            println "==========="
            println "Id: ${vaga.id}"
            println "Nome :${vaga.nome}"
            println "Descrição: ${vaga.descricao}"
            println "Cidade: ${vaga.cidade}"
            println "Requisito de Experiencia: ${vaga.experienciaMinima}"
            println "Requisito de Formação: ${vaga.formacaoMinima}"
            println()
        }
    }

    void listarVagasDaEmpresa(Reader reader){
        println "Digite o id da empresa: "
        Integer id = Integer.parseInt(reader.readLine())

        List<VagaDTO> vagas = vagaService.listarVagasDaEmpresa(id)

        vagas.each {vaga ->
            println "==========="
            println "Id: ${vaga.id}"
            println "Nome :${vaga.nome}"
            println "Descrição: ${vaga.descricao}"
            println "Cidade: ${vaga.cidade}"
            println "Requisito de Experiencia: ${vaga.experienciaMinima}"
            println "Requisito de Formação: ${vaga.formacaoMinima}"
            println()
        }
    }

    void adicionarVaga(Reader reader){
        Vaga vaga = criarVaga(reader)
        vagaService.adicionarVaga(vaga)
    }

    void atualizarVaga(Reader reader){
        println "Digite o id da vaga: "
        Integer id = Integer.parseInt(reader.readLine())

        Vaga vaga = criarVaga(reader)
        vaga.setId(id)

        vagaService.atualizarVaga(vaga)
    }

    void excluirVaga(Reader reader){
        println "Digite o id da vaga: "
        Integer id = Integer.parseInt(reader.readLine())

        vagaService.excluirVaga(id)
    }
}
