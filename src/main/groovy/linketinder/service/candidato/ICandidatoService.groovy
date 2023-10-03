package linketinder.service.candidato

import linketinder.entity.Candidato
import linketinder.entity.dto.CandidatoDTO

interface ICandidatoService {
    List<CandidatoDTO> listarCandidatos() ;
    Candidato obterCandidatoPorId(Integer idCandidato);
    void cadastrarCandidato(Candidato candidato);
    void atualizarCandidato(Candidato candidato);
    void deletarCandidato(Integer candidatoId);
}
