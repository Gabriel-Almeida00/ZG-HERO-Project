package linketinder.dao.match


import linketinder.entity.dto.CandidatoCurtidoDTO
import linketinder.entity.dto.VagaCurtidaDTO

interface IMatchDao {
    List<VagaCurtidaDTO> encontrarMatchesPelaVaga(Integer idCandidato, Integer idVaga)
    List<CandidatoCurtidoDTO> encontrarMatchesPeloCandidato(Integer idCandidato)
}
