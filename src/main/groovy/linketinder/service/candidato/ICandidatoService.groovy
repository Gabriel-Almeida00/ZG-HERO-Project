package linketinder.service.candidato

import linketinder.model.Candidato
import linketinder.model.dto.CandidatoDTO

interface ICandidatoService {
    List<CandidatoDTO> listarCandidatos() ;
    Candidato obterCandidatoPorId(Integer idCandidato);
    void atualizarCandidato(Candidato candidato);
    void deletarCandidato(Integer candidatoId);
}
