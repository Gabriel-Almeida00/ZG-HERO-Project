package service

import Interface.CandidatoInterface
import entity.Candidato
import entity.Curtida

class CandidatoService implements CandidatoInterface {
    List<Candidato> candidatos = []

    List<Candidato> listarCandidatos() {
        return candidatos
    }

    void cadastrarCandidato(Candidato candidato) {
        candidatos.add(candidato)
    }

}
