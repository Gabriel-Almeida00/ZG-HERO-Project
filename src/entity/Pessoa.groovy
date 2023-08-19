package entity


class Pessoa {
    String nome
    String email
    String cep


    Pessoa(String nome, String email, String cep) {
        this.nome = nome
        this.email = email
        this.cep = cep
    }

    String getNome() {
        return nome
    }

    String getEmail() {
        return email
    }

    String getCep() {
        return cep
    }
}
