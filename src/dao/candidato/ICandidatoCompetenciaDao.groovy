package dao.candidato

import entity.CandidatoCompetencia
import entity.Competencias

interface ICandidatoCompetenciaDao {
    Competencias buscarCompetenciaPorId(Integer idCompetencia)
    List<Competencias> listarCompetenciasPorCandidato(Integer idCandidato)
    void adicionarCandidatoCompetencia(CandidatoCompetencia candidatoCompetencia);
    void atualizarNivelCompetenciaCandidato(CandidatoCompetencia candidatoCompetencia);
    void excluirCompetenciaCandidato(Integer id);
}