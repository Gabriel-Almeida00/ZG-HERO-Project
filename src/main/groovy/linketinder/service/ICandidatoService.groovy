package linketinder.service


import linketinder.entity.*
import linketinder.entity.dto.CandidatoDTO
import linketinder.entity.dto.CompetenciaDTO

interface ICandidatoService {
    List<CandidatoDTO> listarCandidatos() ;

    Candidato obterCandidatoPorId(Integer idCandidato);
    void cadastrarCandidato(Candidato candidato);
    void atualizarCandidato(Candidato candidato);
    void deletarCandidato(Integer candidatoId);

    List<CompetenciaDTO> listarCompetenciasPorCandidato(Integer idCandidato)

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
