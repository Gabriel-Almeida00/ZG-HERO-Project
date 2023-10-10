package linketinder.controller.candidato

import linketinder.model.Formacao
import linketinder.service.candidato.ICandidatoFormacaoService

class CandidatoFormacaoController {
    private ICandidatoFormacaoService candidatoFormacaoService

    CandidatoFormacaoController(ICandidatoFormacaoService candidatoFormacaoService) {
        this.candidatoFormacaoService = candidatoFormacaoService
    }

    void adicionarFormacao(Formacao formacao) {
        candidatoFormacaoService.adicionarFormacao(formacao)
    }

    void atualizarFormacao(Formacao formacao) {
        candidatoFormacaoService.atualizarFormacao(formacao)
    }

    void excluirFormacao(Integer idFormacao) {
        candidatoFormacaoService.excluirFormacao(idFormacao)
    }

    List<Formacao> listarFormacoesPorCandidato(Integer idCandidato) {
        return candidatoFormacaoService.listarFormacoesPorCandidato(idCandidato)
    }
}
