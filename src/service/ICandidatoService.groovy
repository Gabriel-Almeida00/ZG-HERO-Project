package service

import entity.Candidato
import entity.Competencias
import entity.Experiencia
import entity.Formacao

import java.sql.SQLException


interface ICandidatoService {
    List<Candidato> listarCandidatos() ;
    Candidato obterCandidatoPorId(Integer idCandidato);
    void cadastrarCandidato(Candidato candidato);
    void atualizarCandidato(Candidato candidato);
    void deletarCandidato(Integer candidatoId);

    List<Competencias> listarCompetenciasCandidato(Integer idCandidato);
    void adicionarCompetenciaCandidato(Integer idCandidato, Competencias competencias);
    void atualizarNivelCompetencia(Integer idCandidato, Integer idCompetencia, String novoNivel);
    void excluirCompetenciaCandidato(Integer idCandidato, Integer idCompetencia);

    void adicionarExperiencia(Experiencia experiencia);
    void atualizarExperiencia(Experiencia experiencia);
    List<Experiencia> listarExperienciasPorCandidato(Integer idCandidato);
    void excluirExperiencia(Integer idExperiencia);

    void adicionarFormacao(Formacao formacao);
    void atualizarFormacao(Formacao formacao);
    void excluirFormacao(Integer idFormacao);
    List<Formacao> listarFormacoesPorCandidato(Integer idCandidato);
}
