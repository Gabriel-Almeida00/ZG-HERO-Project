package linketinder.service.competencia


import linketinder.dao.competencia.ICompetenciaDao
import linketinder.entity.Competencia

class CompetenciaService implements ICompetenciaService {
    private ICompetenciaDao competenciaDao

    CompetenciaService(ICompetenciaDao competenciaDao) {
        this.competenciaDao = competenciaDao
    }

    @Override
    List<Competencia> listarCompetencias() {
        return competenciaDao.listarTodasCompetencias()
    }

    @Override
    Competencia buscarCompetenciaPorId(Integer idCompetencia) {
        return competenciaDao.buscarCompetenciaPorId(idCompetencia)
    }

    @Override
    void adicionarCompetencia(Competencia competencia) {
        competenciaDao.adicionarCompetencia(competencia)
    }

    @Override
    void atualizarCompetencia(Competencia competencia) {
        competenciaDao.atualizarCompetencia(competencia)
    }

    @Override
    void excluirCompetencia(Integer idCompetencia) {
        competenciaDao.excluirCompetencia(idCompetencia)
    }
}
