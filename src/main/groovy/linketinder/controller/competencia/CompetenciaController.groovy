package linketinder.controller.competencia

import linketinder.model.Competencia
import linketinder.service.competencia.ICompetenciaService

class CompetenciaController {
    private ICompetenciaService competenciaService

    CompetenciaController(ICompetenciaService competenciaService) {
        this.competenciaService = competenciaService
    }

    List<Competencia> listarCompetencias() {
        return competenciaService.listarCompetencias()
    }

    Competencia buscarCompetenciaPorId(Integer idCompetencia) {
        return competenciaService.buscarCompetenciaPorId(idCompetencia)
    }

    void adicionarCompetencia(Competencia competencia) {
        competenciaService.adicionarCompetencia(competencia)
    }

    void atualizarCompetencia(Competencia competencia) {
        competenciaService.atualizarCompetencia(competencia)
    }

    void excluirCompetencia(Integer idCompetencia) {
        competenciaService.excluirCompetencia(idCompetencia)
    }
}
