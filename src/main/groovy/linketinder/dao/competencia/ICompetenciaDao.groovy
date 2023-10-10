package linketinder.dao.competencia


import linketinder.model.Competencia

interface ICompetenciaDao {
    List<Competencia> listarTodasCompetencias()

    Competencia buscarCompetenciaPorId(Integer id)
    void adicionarCompetencia(Competencia competencia)
    void atualizarCompetencia(Competencia competencia)
    void excluirCompetencia(Integer idCompetencia)
}