package entity


class PessoaBase  {
    String nome
    String email
    String cep
    List<Curtida> curtidas = []

    PessoaBase(String nome, String email, String cep) {
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

    List<Curtida> getCurtidas() {
        return curtidas
    }

}
