package service

import entity.Candidato
import entity.dto.CandidatoCurtidoDTO
import entity.dto.CandidatoDTO
import entity.dto.EmpresaDTO
import entity.Vaga
import entity.dto.VagaCurtidaDTO

interface IMatchService {
    List<VagaCurtidaDTO> encontrarMatchesPelaVaga(Integer idCandidato, Integer idVaga)
    List<CandidatoCurtidoDTO> encontrarMatchesPeloCandidato(Integer idCandidato)
}