package linketinder.view

import linketinder.view.candidato.CandidatoView
import linketinder.view.competencia.CompetenciaView
import linketinder.view.empresa.EmpresaView
import linketinder.db.ConfigDatabase

class Menu {

    CandidatoView candidatoMenu
    EmpresaView empresaMenu
    CompetenciaView competenciaMenu

    Menu(ConfigDatabase configDatabase) {
        candidatoMenu = new CandidatoView(configDatabase)
        empresaMenu = new EmpresaView(configDatabase)
        competenciaMenu = new CompetenciaView(configDatabase)
    }

    void exibirMenu(Reader reader) {
        while (true) {
            println "Menu :"
            println "1. Gerenciar candidatos"
            println "2. Gerenciar empresa"
            println "3. Gerenciar Competencia"
            println "4. sair"

            int opcao = Integer.parseInt(reader.readLine())
            switch (opcao) {
                case 1:
                    candidatoMenu.exibirMenuCandidato(reader)
                    break
                case 2:
                    empresaMenu.exibirMenuEmpresa(reader)
                    break
                case 3:
                    competenciaMenu.exibirMenuCompetencia(reader)
                    break
                case 4:
                    return
                default:
                    println "Opção inválida. Tente novamente."
            }
        }
    }
}
