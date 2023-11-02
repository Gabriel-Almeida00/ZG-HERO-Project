package linketinder.model

class Candidato extends Pessoa {

    private String sobrenome
    private Date dataNascimento
    private String cpf
    private String redeSocial
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
            String redeSocial,
            String cpf
    ) {
        super(nome, email, pais, cep, descricao, senha)
        this.sobrenome = sobrenome
        this.dataNascimento = dataNascimento
        this.redeSocial = redeSocial
        this.cpf = cpf
        this.competencias = []
        this.formacoes = []
        this.experiencias = []
    }

    String getRedeSocial() {
        return redeSocial
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
}
