package linketinder.dao.candidato


import linketinder.entity.CandidatoCompetencia
import linketinder.entity.Competencias
import linketinder.entity.dto.CompetenciaDTO

interface ICandidatoCompetenciaDao {
    Competencias buscarCompetenciaPorId(Integer idCompetencia)
    List<CompetenciaDTO> listarCompetenciasPorCandidato(Integer idCandidato)
    void adicionarCandidatoCompetencia(CandidatoCompetencia candidatoCompetencia);
    void atualizarNivelCompetenciaCandidato(CandidatoCompetencia candidatoCompetencia);
    void excluirCompetenciaCandidato(Integer id);
}