package linketinder.service


import linketinder.entity.Competencias

interface ICompetenciaService {
    List<Competencias> listarCompetencias()
    Competencias buscarCompetenciaPorId(Integer id)
    void adicionarCompetencia(Competencias competencia)
    void atualizarCompetencia(Competencias competencia)
    void excluirCompetencia(Integer idCompetencia)
}