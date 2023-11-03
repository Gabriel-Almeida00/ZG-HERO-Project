package linketinder.service.candidato

import linketinder.dao.candidato.ICandidatoCompetenciaDao
import linketinder.model.CandidatoCompetencia
import linketinder.model.dto.CompetenciaDTO

class CandidatoCompetenciaService implements ICandidatoCompetenciaService {
    private final ICandidatoCompetenciaDao candidatoCompetenciaDao

    CandidatoCompetenciaService(ICandidatoCompetenciaDao candidatoCompetenciaDao) {
        this.candidatoCompetenciaDao = candidatoCompetenciaDao
    }

    @Override
    List<CompetenciaDTO> listarCompetenciasPorCandidato(Integer idCandidato) {
        return candidatoCompetenciaDao.listarCompetenciasPorCandidato(idCandidato)
    }

    @Override
    CompetenciaDTO buscarCompetenciaDoCandidatoPorId(Integer id) {
        return this.candidatoCompetenciaDao.buscarCompetenciaDoCandidatoPorId(id)
    }

    @Override
    void adicionarCandidatoCompetencia(CandidatoCompetencia candidatoCompetencia) {
        candidatoCompetenciaDao.adicionarCandidatoCompetencia(candidatoCompetencia)
    }

    @Override
    void atualizarNivelCompetenciaCandidato(CandidatoCompetencia candidatoCompetencia) {
        candidatoCompetenciaDao.atualizarNivelCompetenciaCandidato(candidatoCompetencia)
    }

    @Override
    void excluirCompetenciaCandidato(Integer id) {
        candidatoCompetenciaDao.excluirCompetenciaCandidato(id)
    }
}
