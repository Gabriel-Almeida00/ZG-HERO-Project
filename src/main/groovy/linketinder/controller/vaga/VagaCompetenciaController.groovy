package linketinder.controller.vaga

import linketinder.model.VagaCompetencia
import linketinder.model.dto.CompetenciaDTO
import linketinder.service.vaga.IVagaCompetenciaService

class VagaCompetenciaController {
    private IVagaCompetenciaService vagaCompetenciaService

    VagaCompetenciaController(IVagaCompetenciaService vagaCompetenciaService) {
        this.vagaCompetenciaService = vagaCompetenciaService
    }

    void adicionarVagaCompetencia(VagaCompetencia vagaCompetencia) {
        vagaCompetenciaService.adicionarVagaCompetencia(vagaCompetencia)
    }

    void atualizarNivelVagaCompetencia(VagaCompetencia vagaCompetencia) {
        vagaCompetenciaService.atualizarNivelVagaCompetencia(vagaCompetencia)
    }

    void excluirVagaCompetencia(Integer idVagaCompetencia) {
        vagaCompetenciaService.excluirVagaCompetencia(idVagaCompetencia)

    }

    List<CompetenciaDTO> listarCompetenciasPorVaga(Integer idVaga) {
        return vagaCompetenciaService.listarCompetenciasPorVaga(idVaga)
    }
}
