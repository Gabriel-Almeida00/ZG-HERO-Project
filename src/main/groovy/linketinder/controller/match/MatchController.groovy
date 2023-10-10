package linketinder.controller.match

import linketinder.model.dto.MatchCandidatoDTO
import linketinder.model.dto.MatchEmpresaDTO
import linketinder.service.match.IMatchService

class MatchController {
    private IMatchService matchService

    MatchController(IMatchService matchService) {
        this.matchService = matchService
    }

    List<MatchCandidatoDTO> encontrarMatchesPorEmpresa(Integer idEmpresa){
        return matchService.encontrarMatchesPelaEmpresa(idEmpresa)
    }

    List<MatchEmpresaDTO> encontrarMatchesPorCandidato(Integer idCandidato) {
        return matchService.encontrarMatchesPeloCandidato(idCandidato)
    }
}
