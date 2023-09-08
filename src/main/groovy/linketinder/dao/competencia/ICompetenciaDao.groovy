package linketinder.dao.competencia


import linketinder.entity.Competencias

interface ICompetenciaDao {
    List<Competencias> listarTodasCompetencias()
    Competencias buscarCompetenciaPorId(Integer id)
    void adicionarCompetencia(Competencias competencia)
    void atualizarCompetencia(Competencias competencia)
    void excluirCompetencia(Integer idCompetencia)
}