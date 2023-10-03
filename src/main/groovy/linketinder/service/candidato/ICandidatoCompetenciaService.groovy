package linketinder.service.candidato

import linketinder.entity.CandidatoCompetencia
import linketinder.entity.dto.CompetenciaDTO

interface ICandidatoCompetenciaService {
    List<CompetenciaDTO> listarCompetenciasPorCandidato(Integer idCandidato)
    void adicionarCandidatoCompetencia(CandidatoCompetencia candidatoCompetencia);
    void atualizarNivelCompetenciaCandidato(CandidatoCompetencia candidatoCompetencia);
    void excluirCompetenciaCandidato(Integer id);
}