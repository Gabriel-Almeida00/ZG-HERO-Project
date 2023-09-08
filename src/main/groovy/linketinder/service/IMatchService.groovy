package linketinder.service


import linketinder.entity.dto.CandidatoCurtidoDTO
import linketinder.entity.dto.VagaCurtidaDTO

interface IMatchService {
    List<VagaCurtidaDTO> encontrarMatchesPelaVaga(Integer idCandidato, Integer idVaga)
    List<CandidatoCurtidoDTO> encontrarMatchesPeloCandidato(Integer idCandidato)
}