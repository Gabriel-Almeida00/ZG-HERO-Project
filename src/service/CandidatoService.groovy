package service

import Interface.ICandidato
import entity.Candidato

class CandidatoService implements ICandidato {
    List<Candidato> candidatos = []

    List<Candidato> listarCandidatos() {
        return candidatos
    }

    void cadastrarCandidato(Candidato candidato) {
        candidatos.add(candidato)
    }

}
