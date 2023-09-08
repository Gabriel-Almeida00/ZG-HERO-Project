package linketinder.dao.candidato


import linketinder.entity.Formacao

interface IFormacaoDao {
    List<Formacao> listarFormacoesPorCandidato(Integer idCandidato);
    Formacao buscarFormacaoPorId(Integer idFormacao)
    void adicionarFormacao(Formacao formacao);
    void atualizarFormacao(Formacao formacao);
    void excluirFormacao(Integer idFormacao);
}