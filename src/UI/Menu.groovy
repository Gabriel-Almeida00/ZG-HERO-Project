package UI

import UI.candidato.CandidatoMenu
import UI.competencia.CompetenciaMenu
import UI.empresa.EmpresaMenu
import service.EmpresaService

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
            println "Menu Candidato:"
            println "1. Gerenciar candidatos"
            println "2. Gerenciar empresa"
            println "3. Gerenciar Competencias"
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
