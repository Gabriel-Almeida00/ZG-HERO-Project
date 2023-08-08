package entity

class Curtida {
    PessoaBase candidato
    PessoaBase empresa

    Curtida(PessoaBase candidato, PessoaBase empresa) {
        this.candidato = candidato
        this.empresa = empresa
    }
}
