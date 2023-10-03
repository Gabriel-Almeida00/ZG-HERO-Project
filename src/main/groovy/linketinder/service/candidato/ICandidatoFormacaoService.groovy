package linketinder.service.candidato

import linketinder.entity.Formacao

interface ICandidatoFormacaoService {
    void adicionarFormacao(Formacao formacao);
    void atualizarFormacao(Formacao formacao)
    void excluirFormacao(Integer idFormacao)
    List<Formacao> listarFormacoesPorCandidato(Integer idCandidato)
}