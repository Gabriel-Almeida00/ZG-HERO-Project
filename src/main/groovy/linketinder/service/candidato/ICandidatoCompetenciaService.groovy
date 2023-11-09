package linketinder.service.candidato

import linketinder.model.CandidatoCompetencia
import linketinder.model.dto.CandidatoCompetenciaDTO
import linketinder.model.dto.CompetenciaDTO

interface ICandidatoCompetenciaService {
    List<CompetenciaDTO> listarCompetenciasPorCandidato(Integer idCandidato)
    CandidatoCompetenciaDTO buscarCompetenciaDoCandidatoPorId(Integer id)
    void adicionarCandidatoCompetencia(CandidatoCompetencia candidatoCompetencia);
    void atualizarNivelCompetenciaCandidato(CandidatoCompetencia candidatoCompetencia);
    void excluirCompetenciaCandidato(Integer id);
}