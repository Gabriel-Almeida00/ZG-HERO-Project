package linketinder.service.candidato

import linketinder.model.Formacao

interface ICandidatoFormacaoService {
    void adicionarFormacao(Formacao formacao);
    void atualizarFormacao(Formacao formacao)
    void excluirFormacao(Integer idFormacao)
    Formacao buscarFormacaoPorId(Integer idFormacao)
    List<Formacao> listarFormacoesPorCandidato(Integer idCandidato)
}