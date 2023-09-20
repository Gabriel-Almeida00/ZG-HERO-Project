package linketinder.UI

import linketinder.UI.candidato.CandidatoMenu
import linketinder.UI.competencia.CompetenciaMenu
import linketinder.UI.empresa.EmpresaMenu

class Menu {

    CandidatoMenu candidatoMenu
    EmpresaMenu empresaMenu
    CompetenciaMenu competenciaMenu

    Menu() {
        candidatoMenu = new CandidatoMenu()
        empresaMenu = new EmpresaMenu()
        competenciaMenu = new CompetenciaMenu()
    }

    void exibirMenuCandidato(Reader reader) {
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
