package linketinder.entity

class Candidato extends Pessoa {

    private String sobrenome
    private Date dataNascimento
    private String cpf
    private List<CandidatoCompetencia> competencias
    private List<Formacao> formacoes
    private List<Experiencia> experiencias

    Candidato(
            String nome,
            String email,
            String pais,
            String cep,
            String descricao,
            String senha,
            String sobrenome,
            Date dataNascimento,
            String cpf
    ) {
        super(nome, email, pais, cep, descricao, senha)
        this.sobrenome = sobrenome
        this.dataNascimento = dataNascimento
        this.cpf = cpf
        this.competencias = []
        this.formacoes = []
        this.experiencias = []
    }

    String getSobrenome() {
        return sobrenome
    }


    Date getDataNascimento() {
        return dataNascimento
    }

    String getCpf() {
        return cpf
    }

    List<CandidatoCompetencia> getCompetencias() {
        return competencias
    }

    void setCompetencias(List<CandidatoCompetencia> competencias) {
        this.competencias = competencias
    }
}
