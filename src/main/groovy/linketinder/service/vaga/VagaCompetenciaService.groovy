package linketinder.service.vaga

import linketinder.dao.vaga.IVagaCompetenciaDao
import linketinder.model.VagaCompetencia
import linketinder.model.dto.CompetenciaDTO

class VagaCompetenciaService implements IVagaCompetenciaService{
    private IVagaCompetenciaDao vagaCompetenciaDao

    VagaCompetenciaService(IVagaCompetenciaDao vagaCompetenciaDao) {
        this.vagaCompetenciaDao = vagaCompetenciaDao
    }

    @Override
    void adicionarVagaCompetencia(VagaCompetencia vagaCompetencia) {
        vagaCompetenciaDao.adicionarVagaCompetencia(vagaCompetencia)
    }

    @Override
    void atualizarNivelVagaCompetencia(VagaCompetencia vagaCompetencia) {
        vagaCompetenciaDao.atualizarNivelVagaCompetencia(vagaCompetencia)
    }

    @Override
    void excluirVagaCompetencia(Integer idVagaCompetencia) {
        vagaCompetenciaDao.excluirVagaCompetencia(idVagaCompetencia)

    }

    @Override
    List<CompetenciaDTO> listarCompetenciasPorVaga(Integer idVaga) {
        List<CompetenciaDTO> vagaCompetenciaList = vagaCompetenciaDao.listarCompetenciasPorVaga(idVaga)
        return vagaCompetenciaList

    }

    @Override
    VagaCompetencia buscarCompetenciaDaVagaporId(Integer id) {
        return this.vagaCompetenciaDao.buscarCompetenciaDaVagaporId(id)
    }
}
