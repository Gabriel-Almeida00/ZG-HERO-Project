package linketinder.service

import linketinder.entity.dto.MatchCandidatoDTO
import linketinder.entity.dto.MatchEmpresaDTO

interface IMatchService {
    List<MatchCandidatoDTO> encontrarMatchesPelaEmpresa(Integer idEmpresa)
    List<MatchEmpresaDTO> encontrarMatchesPeloCandidato(Integer idCandidato)
}