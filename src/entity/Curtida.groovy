package entity

class Curtida {
    PessoaBase candidato
    Vaga vaga

    Curtida(PessoaBase candidato, Vaga vaga) {
        this.candidato = candidato
        this.vaga = vaga
    }
}
