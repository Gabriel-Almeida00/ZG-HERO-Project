package linketinder.service.competencia


import linketinder.entity.Competencia

interface ICompetenciaService {
    List<Competencia> listarCompetencias()
    Competencia buscarCompetenciaPorId(Integer id)
    void adicionarCompetencia(Competencia competencia)
    void atualizarCompetencia(Competencia competencia)
    void excluirCompetencia(Integer idCompetencia)
}