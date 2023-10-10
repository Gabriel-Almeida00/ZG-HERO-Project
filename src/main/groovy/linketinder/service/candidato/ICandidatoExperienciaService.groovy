package linketinder.service.candidato

import linketinder.model.Experiencia

interface ICandidatoExperienciaService {
    void adicionarExperiencia(Experiencia experiencia);
    void atualizarExperiencia(Experiencia experiencia);
    List<Experiencia> listarExperienciasPorCandidato(Integer idCandidato);
    void excluirExperiencia(Integer idExperiencia);

}