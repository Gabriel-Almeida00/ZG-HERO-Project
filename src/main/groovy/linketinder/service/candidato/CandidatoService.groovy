package linketinder.service.candidato

import linketinder.dao.candidato.ICandidatoDao
import linketinder.dao.curtida.ICurtidaDao
import linketinder.dao.vaga.IVagaDao
import linketinder.entity.Candidato
import linketinder.entity.dto.CandidatoDTO

class CandidatoService implements ICandidatoService {

    private final ICandidatoDao candidatoDao

    CandidatoService(ICandidatoDao candidatoDao) {
        this.candidatoDao = candidatoDao
    }

    @Override
    List<CandidatoDTO> listarCandidatos() {
        return candidatoDao.listarCandidatos()
    }

    @Override
    Candidato obterCandidatoPorId(Integer id) {
        Candidato candidato = candidatoDao.obterCandidatoPorId(id)
        return candidato
    }

    @Override
    void cadastrarCandidato(Candidato candidato) {
        candidatoDao.adicionarCandidato(candidato)
    }

    @Override
    void atualizarCandidato(Candidato candidato) {
        candidatoDao.atualizarCandidato(candidato)
    }

    @Override
    void deletarCandidato(Integer candidatoId) {
        candidatoDao.deletarCandidato(candidatoId)
    }
}