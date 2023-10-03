package linketinder.service.candidato

import linketinder.dao.candidato.IFormacaoDao
import linketinder.entity.Formacao

class CandidatoFormacaoService implements ICandidatoFormacaoService {
    private final IFormacaoDao formacaoDao

    CandidatoFormacaoService(IFormacaoDao formacaoDao) {
        this.formacaoDao = formacaoDao
    }

    @Override
    void adicionarFormacao(Formacao formacao) {
        formacaoDao.adicionarFormacao(formacao)
    }

    @Override
    void atualizarFormacao(Formacao formacao) {
        formacaoDao.atualizarFormacao(formacao)
    }

    @Override
    void excluirFormacao(Integer idFormacao) {
        formacaoDao.excluirFormacao(idFormacao)
    }

    @Override
    List<Formacao> listarFormacoesPorCandidato(Integer idCandidato) {
        return formacaoDao.listarFormacoesPorCandidato(idCandidato)
    }
}
