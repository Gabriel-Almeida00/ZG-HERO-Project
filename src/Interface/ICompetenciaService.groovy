package Interface

import entity.Competencias

interface ICompetenciaService {
    Competencias obterCompetenciaPorId(Integer id);
    List<Competencias> listarCompetencias();
    void adicionarCompetencia(Competencias competencia);
    void atualizarCompetencia(Integer id, Competencias competencia);
    void deletarCompetencia(Integer id);
}