package Interface

import entity.Candidato

interface CandidatoInterface {
    List<Candidato> listarCandidatos();
    void cadastrarCandidato(Candidato candidato);
}
