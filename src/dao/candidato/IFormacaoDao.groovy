package dao.candidato

import entity.Formacao

import java.sql.SQLException

interface IFormacaoDao {
    List<Formacao> listarFormacoesPorCandidato(Integer idCandidato);
    Formacao buscarFormacaoPorId(Integer idFormacao)
    void adicionarFormacao(Formacao formacao);
    void atualizarFormacao(Formacao formacao);
    void excluirFormacao(Integer idFormacao);
}