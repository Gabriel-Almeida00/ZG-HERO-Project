package entity

class Candidato extends PessoaBase {
    String cpf
    int idade
    String estado
    String descricaoPessoal
    List<Competencias> competencias = [];

    Candidato(String nome, String email, String cep, String cpf, int idade, String estado, String descricaoPessoal, List<Competencias> competencias) {
        super(nome, email, cep)
        this.cpf = cpf
        this.idade = idade
        this.estado = estado
        this.descricaoPessoal = descricaoPessoal
        this.competencias = competencias
    }

    List<Competencias> getCompetencias() {
        return competencias
    }

    void setCompetencias(List<Competencias> competencias) {
        this.competencias = competencias
    }
}
