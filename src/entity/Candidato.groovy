package entity

class Candidato extends PessoaBase {
    String cpf
    int idade
    String estado
    String descricaoPessoal

    Candidato(String nome, String email, String cpf, int idade, String estado, String cep, String descricaoPessoal, List<String> competencias) {
        super(nome, email, cep, competencias)
        this.cpf = cpf
        this.idade = idade
        this.estado = estado
        this.descricaoPessoal = descricaoPessoal
    }
}
