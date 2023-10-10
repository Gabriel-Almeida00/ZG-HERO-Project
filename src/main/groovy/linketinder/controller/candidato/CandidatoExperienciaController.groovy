package linketinder.controller.candidato

import linketinder.model.Experiencia
import linketinder.service.candidato.ICandidatoExperienciaService

class CandidatoExperienciaController {
    private ICandidatoExperienciaService candidatoExperienciaService

    CandidatoExperienciaController(ICandidatoExperienciaService candidatoExperienciaService) {
        this.candidatoExperienciaService = candidatoExperienciaService
    }

    void adicionarExperiencia(Experiencia experiencia) {
        candidatoExperienciaService.adicionarExperiencia(experiencia)
    }

    void atualizarExperiencia(Experiencia experiencia) {
        candidatoExperienciaService.atualizarExperiencia(experiencia)
    }

    List<Experiencia> listarExperienciasPorCandidato(Integer idCandidato) {
        return candidatoExperienciaService.listarExperienciasPorCandidato(idCandidato)
    }

    void excluirExperiencia(Integer idExperiencia) {
        candidatoExperienciaService.excluirExperiencia(idExperiencia)
    }
}
