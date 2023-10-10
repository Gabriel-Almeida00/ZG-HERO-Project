package linketinder.dao.match

import linketinder.model.dto.MatchCandidatoDTO
import linketinder.model.dto.MatchEmpresaDTO

interface IMatchDao {
    List<MatchCandidatoDTO> encontrarMatchesPelaEmpresa(Integer idEmpresa)
    List<MatchEmpresaDTO> encontrarMatchesPeloCandidato(Integer idCandidato)
}
