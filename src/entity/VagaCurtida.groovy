package entity

class VagaCurtida {
    Integer id
    Candidato candidato
    Vaga vaga

    VagaCurtida(Candidato candidato, Vaga vaga) {
        this.candidato = candidato
        this.vaga = vaga
    }

    Integer getId() {
        return id
    }

    void setId(Integer id) {
        this.id = id
    }

    Candidato getCandidato() {
        return candidato
    }

    void setCandidato(Candidato candidato) {
        this.candidato = candidato
    }

    Vaga getVaga() {
        return vaga
    }

    void setVaga(Vaga vaga) {
        this.vaga = vaga
    }
}
