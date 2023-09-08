package linketinder.dao.candidato


import linketinder.entity.Candidato
import linketinder.entity.dto.CandidatoDTO

interface ICandidatoDao {

    List<CandidatoDTO> listarCandidatos()
    void adicionarCandidato(Candidato candidato)

    Candidato obterCandidatoPorId(Integer id)
    void atualizarCandidato(Candidato candidato)
    void deletarCandidato(Integer id);
}
