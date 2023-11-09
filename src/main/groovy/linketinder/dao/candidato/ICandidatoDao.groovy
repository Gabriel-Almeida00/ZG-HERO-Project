package linketinder.dao.candidato


import linketinder.model.Candidato
import linketinder.model.dto.CandidatoDTO

interface ICandidatoDao {

    List<CandidatoDTO> listarCandidatos()

    Candidato obterCandidatoPorId(Integer id)
    void atualizarCandidato(Candidato candidato)
    void deletarCandidato(Integer id);
}
