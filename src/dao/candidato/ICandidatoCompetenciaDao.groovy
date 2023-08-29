package dao.candidato

import entity.Competencias

interface ICandidatoCompetenciaDao {
    List<Competencias> listarCompetenciasPorCandidato(Integer idCandidato);
    Competencias buscarCompetenciaPorId(Integer idCompetencia);
    void adicionarCandidatoCompetencia(Integer idCandidato, Integer idCompetencia);
    void atualizarNivelCompetenciaCandidato(Integer idCandidato, Integer idCompetencia, String novoNivel);
    void excluirCompetenciaCandidato(Integer idCandidato, Integer idCompetencia);
}