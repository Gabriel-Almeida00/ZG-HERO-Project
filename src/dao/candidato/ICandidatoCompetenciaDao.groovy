package dao.candidato

import entity.CandidatoCompetencia
import entity.Competencias

interface ICandidatoCompetenciaDao {
    List<Competencias> listarCompetenciasPorCandidato(Integer idCandidato)
    Competencias buscarCompetenciaPorId(Integer idCompetencia);
    void adicionarCandidatoCompetencia(CandidatoCompetencia candidatoCompetencia);
    void atualizarNivelCompetenciaCandidato(CandidatoCompetencia candidatoCompetencia);
    void excluirCompetenciaCandidato(Integer id);
}