package Interface

import entity.Candidato

interface ICandidato {
    List<Candidato> listarCandidatos();
    void cadastrarCandidato(Candidato candidato);
}
