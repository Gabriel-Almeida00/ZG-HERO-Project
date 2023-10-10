package linketinder.service.match

import linketinder.model.dto.MatchCandidatoDTO
import linketinder.model.dto.MatchEmpresaDTO

interface IMatchService {
    List<MatchCandidatoDTO> encontrarMatchesPelaEmpresa(Integer idEmpresa)
    List<MatchEmpresaDTO> encontrarMatchesPeloCandidato(Integer idCandidato)
}