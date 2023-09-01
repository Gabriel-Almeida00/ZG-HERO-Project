package UI.competencia

import dao.competencia.CompetenciaDao
import dao.competencia.ICompetenciaDao
import db.DatabaseConnection
import db.IDatabaseConnection
import entity.Competencias
import service.CompetenciaService

class CompetenciaMenu {

    CompetenciaService competenciaService

    CompetenciaMenu() {
        IDatabaseConnection databaseConnection = new DatabaseConnection()
        ICompetenciaDao competenciaDao = new CompetenciaDao(databaseConnection)
        competenciaService = new CompetenciaService(competenciaDao)
    }

    void exibirMenuCompetencia(Reader reader) {
        while (true) {
            println "Menu Competencia:"
            println "1. Listar Competencias"
            println "2. Cadastrar Competencia"
            println "3. Atualizar Competencia"
            println "4. Deletar Competencia"
            println "5. Voltar"

            int opcao = Integer.parseInt(reader.readLine())
            switch (opcao) {
                case 1:
                    listarCompetencias()
                    break
                case 2:
                    adicionarCompetencia(reader)
                    break
                case 3:
                    atualizaCompetencia(reader)
                    break
                case 4:
                    excluirCompetencia(reader)
                    break
                case 5:
                    return
                default:
                    println "Opção inválida. Tente novamente."
            }
        }
    }

    Competencias criarCompetencia(Reader reader){
        println "Digite o nome da competencia que deseja adicionar: "
        String nome = reader.readLine()

        return new Competencias(nome)
    }

    void listarCompetencias( ){
        List<Competencias> competencias = competenciaService.listarCompetencias()

        competencias.each {competencia ->
            println "================="
            println "Id : ${competencia.id}"
            println "Nome : ${competencia.nome}"
            println ""
        }
    }

    void adicionarCompetencia(Reader reader){
        Competencias competencias = criarCompetencia(reader)
        competenciaService.adicionarCompetencia(competencias)
    }

    void atualizaCompetencia(Reader reader){
        println "Digite o id da competencia que deseja atualizar: "
        Integer id = Integer.parseInt(reader.readLine())

        Competencias competencias = criarCompetencia(reader)
        competencias.setId(id)

        competenciaService.atualizarCompetencia(competencias)
    }

    void excluirCompetencia(Reader reader){
        println "Digite o id da competencia que deseja excluir: "
        Integer id = Integer.parseInt(reader.readLine())

        competenciaService.excluirCompetencia(id)
    }
}
