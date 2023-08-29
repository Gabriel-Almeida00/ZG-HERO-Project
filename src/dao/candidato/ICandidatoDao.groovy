package dao.candidato

import entity.Candidato

interface ICandidatoDao {

    List<Candidato> listarCandidatos()
    void adicionarCandidato(Candidato candidato)
    Candidato obterCandidatoPorId(Integer id)
    void atualizarCandidato(Candidato candidato)
    void deletarCandidato(Integer id);
}
