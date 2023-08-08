package entity

import Interface.Pessoa

class PessoaBase implements Pessoa {
    String nome
    String email
    String cep
    List<String> competencias = []
    List<Curtida> curtidas = []

    PessoaBase(String nome, String email, String cep, List<String> competencias) {
        this.nome = nome
        this.email = email
        this.cep = cep
        this.competencias = competencias
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

    List<String> getCompetencias() {
        return competencias
    }
}
