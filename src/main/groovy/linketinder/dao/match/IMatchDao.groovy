package linketinder.dao.match

import linketinder.entity.dto.MatchCandidatoDTO
import linketinder.entity.dto.MatchEmpresaDTO

interface IMatchDao {
    List<MatchCandidatoDTO> encontrarMatchesPelaEmpresa(Integer idEmpresa)
    List<MatchEmpresaDTO> encontrarMatchesPeloCandidato(Integer idCandidato)
}
