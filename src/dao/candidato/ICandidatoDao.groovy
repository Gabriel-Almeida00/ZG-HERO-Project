package dao.candidato

import entity.Candidato
import entity.dto.CandidatoDTO

interface ICandidatoDao {

    List<CandidatoDTO> listarCandidatos()
    void adicionarCandidato(Candidato candidato)
    Candidato obterCandidatoPorId(Integer id)
    void atualizarCandidato(Candidato candidato)
    void deletarCandidato(Integer id);
}
