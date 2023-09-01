package UI.competencia

import dao.competencia.CompetenciaDao
import dao.competencia.ICompetenciaDao
import db.DatabaseConnection
import db.IDatabaseConnection
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
                    return
                default:
                    println "Opção inválida. Tente novamente."
            }
        }
    }
}
