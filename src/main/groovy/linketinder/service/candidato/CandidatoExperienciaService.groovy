package linketinder.service.candidato

import linketinder.dao.candidato.IExperienciaDao
import linketinder.model.Experiencia

class CandidatoExperienciaService implements ICandidatoExperienciaService {
    private final IExperienciaDao experienciaDao

    CandidatoExperienciaService(IExperienciaDao experienciaDao) {
        this.experienciaDao = experienciaDao
    }

    @Override
    void adicionarExperiencia(Experiencia experiencia) {
        experienciaDao.adicionarExperiencia(experiencia)
    }

    @Override
    void atualizarExperiencia(Experiencia experiencia) {
        experienciaDao.atualizarExperiencia(experiencia)
    }

    @Override
    List<Experiencia> listarExperienciasPorCandidato(Integer idCandidato) {
        return experienciaDao.listarExperienciasPorCandidato(idCandidato)
    }

    @Override
    Experiencia buscarExperienciaPorId(Integer idExperiencia) {
        return this.experienciaDao.buscarExperienciaPorId(idExperiencia)
    }

    @Override
    void excluirExperiencia(Integer idExperiencia) {
        experienciaDao.excluirExperiencia(idExperiencia)
    }
}
