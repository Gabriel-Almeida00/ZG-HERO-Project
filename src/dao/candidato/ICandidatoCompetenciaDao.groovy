package dao.candidato

import entity.CandidatoCompetencia
import entity.Competencias
import entity.dto.CompetenciaDTO

interface ICandidatoCompetenciaDao {
    Competencias buscarCompetenciaPorId(Integer idCompetencia)
    List<CompetenciaDTO> listarCompetenciasPorCandidato(Integer idCandidato)
    void adicionarCandidatoCompetencia(CandidatoCompetencia candidatoCompetencia);
    void atualizarNivelCompetenciaCandidato(CandidatoCompetencia candidatoCompetencia);
    void excluirCompetenciaCandidato(Integer id);
}