package entity

class CurtidaVaga extends Curtida {
    Vaga vaga

    CurtidaVaga(Candidato candidato, Vaga vaga) {
        super(candidato)
        this.vaga = vaga
    }

    Candidato getCandidato() {
        return candidato
    }

    Vaga getVaga() {
        return vaga
    }
}
