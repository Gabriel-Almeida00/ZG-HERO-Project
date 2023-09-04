package dao.match

import entity.dto.CandidatoCurtidoDTO
import entity.dto.VagaCurtidaDTO

interface IMatchDao {
    List<VagaCurtidaDTO> encontrarMatchesPelaVaga(Integer idCandidato, Integer idVaga)
    List<CandidatoCurtidoDTO> encontrarMatchesPeloCandidato(Integer idCandidato)
}
