package Interface

import entity.Candidato
import entity.Experiencia
import entity.Formacao

import java.sql.SQLException


interface ICandidato {
    List<Candidato> listarCandidatos() ;
    Candidato obterCandidatoPorId(Integer idCandidato);
    void cadastrarCandidato(Candidato candidato);
    void atualizarCandidato(Candidato candidato);
    void deletarCandidato(Integer candidatoId);
    void adicionarFormacaoCandidato(Integer idCandidato, Formacao formacao);
    void adicionarExperienciaCandidato(Integer idCandidato, Experiencia experiencia);
    void adicionarCompetencia(Integer idCandidato, Integer idCompetencia)
}
