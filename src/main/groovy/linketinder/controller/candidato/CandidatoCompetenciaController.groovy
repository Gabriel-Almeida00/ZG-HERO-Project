package linketinder.controller.candidato

import linketinder.model.CandidatoCompetencia
import linketinder.model.dto.CompetenciaDTO
import linketinder.service.candidato.ICandidatoCompetenciaService

class CandidatoCompetenciaController {
    private ICandidatoCompetenciaService candidatoCompetenciaService

    CandidatoCompetenciaController(ICandidatoCompetenciaService candidatoCompetenciaService) {
        this.candidatoCompetenciaService = candidatoCompetenciaService
    }

    List<CompetenciaDTO> listarCompetenciasPorCandidato(Integer idCandidato) {
        return candidatoCompetenciaService.listarCompetenciasPorCandidato(idCandidato)
    }

    void adicionarCandidatoCompetencia(CandidatoCompetencia candidatoCompetencia) {
        candidatoCompetenciaService.adicionarCandidatoCompetencia(candidatoCompetencia)
    }

    void atualizarNivelCompetenciaCandidato(CandidatoCompetencia candidatoCompetencia) {
        candidatoCompetenciaService.atualizarNivelCompetenciaCandidato(candidatoCompetencia)
    }

    void excluirCompetenciaCandidato(Integer id) {
        candidatoCompetenciaService.excluirCompetenciaCandidato(id)
    }
}
