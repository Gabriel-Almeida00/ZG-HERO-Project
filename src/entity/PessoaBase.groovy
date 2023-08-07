package entity

import Interface.Pessoa

class PessoaBase implements Pessoa{
    String nome
    String email
    String cep
    List<String> competencias = []

    PessoaBase(String nome, String email, String cep, List<String> competencias) {
        this.nome = nome
        this.email = email
        this.cep = cep
        this.competencias = competencias
    }

    @Override
    String getNome() {
        return nome
    }

    @Override
    String getEmail() {
        return email
    }

    @Override
    String getCep() {
        return cep
    }

    @Override
    List<String> getCompetencias() {
        return competencias
    }
}
