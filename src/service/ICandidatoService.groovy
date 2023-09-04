package service

import entity.Candidato
import entity.CandidatoCompetencia
import entity.Competencias
import entity.dto.CandidatoDTO
import entity.Experiencia
import entity.Formacao
import entity.dto.CompetenciaDTO

interface ICandidatoService {
    List<CandidatoDTO> listarCandidatos() ;
    Candidato obterCandidatoPorId(Integer idCandidato);
    void cadastrarCandidato(Candidato candidato);
    void atualizarCandidato(Candidato candidato);
    void deletarCandidato(Integer candidatoId);

    List<CompetenciaDTO> listarCompetenciasPorCandidato(Integer idCandidato)
    Competencias buscarCompetenciaPorId(Integer idCompetencia)
    void adicionarCandidatoCompetencia(CandidatoCompetencia candidatoCompetencia);
    void atualizarNivelCompetenciaCandidato(CandidatoCompetencia candidatoCompetencia);
    void excluirCompetenciaCandidato(Integer id);

    void adicionarExperiencia(Experiencia experiencia);
    void atualizarExperiencia(Experiencia experiencia);
    List<Experiencia> listarExperienciasPorCandidato(Integer idCandidato);
    void excluirExperiencia(Integer idExperiencia);

    void adicionarFormacao(Formacao formacao);
    void atualizarFormacao(Formacao formacao)
    void excluirFormacao(Integer idFormacao)
    List<Formacao> listarFormacoesPorCandidato(Integer idCandidato)

    void curtirVaga(Integer idCandidato, Integer idVaga)
}
