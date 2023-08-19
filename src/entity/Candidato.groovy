package entity

class Candidato extends Pessoa {
    String cpf
    int idade
    String estado
    String descricaoPessoal
    List<Competencias> competencias = []
    List<Formacao> formacoes = []
    List<Experiencia> experiencias = []
    List<CurtidaCandidato> curtidas = []

    Candidato(
            String nome,
            String email,
            String cep,
            String cpf,
            int idade,
            String estado,
            String descricaoPessoal,
            List<Competencias> competencias,
            List<Formacao> formacoes,
            List<Experiencia> experiencias)
    {
        super(nome, email, cep)
        this.cpf = cpf
        this.idade = idade
        this.estado = estado
        this.descricaoPessoal = descricaoPessoal
        this.competencias = competencias
        this.formacoes = formacoes
        this.experiencias = experiencias
    }

    List<Competencias> getCompetencias() {
        return competencias
    }

    List<CurtidaCandidato> getCurtidas() {
        return curtidas
    }
}
