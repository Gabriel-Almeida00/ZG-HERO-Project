package linketinder.entity

class Candidato {

    private Integer id
    private String nome
    private String sobrenome
    private Date dataNascimento
    private String email
    private String cpf
    private String pais
    private String cep
    private String descricao
    private String senha
    private List<CandidatoCompetencia> competencias
    private List<Formacao> formacoes
    private List<Experiencia> experiencias
    private List<CandidatoCurtido> curtida

    Candidato(
            String nome,
            String sobrenome,
            Date dataNascimento,
            String email,
            String cpf,
            String pais,
            String cep,
            String descricao,
            String senha
    ) {
        this.nome = nome
        this.sobrenome = sobrenome
        this.dataNascimento = dataNascimento
        this.email = email
        this.cpf = cpf
        this.pais = pais
        this.cep = cep
        this.descricao = descricao
        this.senha = senha
        competencias = []
        formacoes = []
        experiencias = []
        curtida = []
    }

    List<CandidatoCurtido> getCurtida() {
        return curtida
    }

    void setCurtida(List<CandidatoCurtido> curtida) {
        this.curtida = curtida
    }

    Integer getId() {
        return id
    }

    void setId(Integer id) {
        this.id = id
    }

    String getNome() {
        return nome
    }

    void setNome(String nome) {
        this.nome = nome
    }

    String getSobrenome() {
        return sobrenome
    }

    Date getDataNascimento() {
        return dataNascimento
    }

    String getEmail() {
        return email
    }

    String getCpf() {
        return cpf
    }

    String getPais() {
        return pais
    }

    String getCep() {
        return cep
    }

    String getDescricao() {
        return descricao
    }

    void setDescricao(String descricao) {
        this.descricao = descricao
    }

    String getSenha() {
        return senha
    }

    List<CandidatoCompetencia> getCompetencias() {
        return competencias
    }

    void setCompetencias(List<CandidatoCompetencia> competencias) {
        this.competencias = competencias
    }
}
