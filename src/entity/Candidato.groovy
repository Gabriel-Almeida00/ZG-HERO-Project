package entity

class Candidato   {

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
    private List<CandidatoCurtido> curtida;

    Candidato(String nome,
              String sobrenome,
              Date dataNascimento,
              String email,
              String cpf,
              String pais,
              String cep,
              String descricao,
              String senha)
    {
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

    void setSobrenome(String sobrenome) {
        this.sobrenome = sobrenome
    }

    Date getDataNascimento() {
        return dataNascimento
    }

    void setDataNascimento(Date dataNascimento) {
        this.dataNascimento = dataNascimento
    }

    String getEmail() {
        return email
    }

    void setEmail(String email) {
        this.email = email
    }

    String getCpf() {
        return cpf
    }

    void setCpf(String cpf) {
        this.cpf = cpf
    }

    String getPais() {
        return pais
    }

    void setPais(String pais) {
        this.pais = pais
    }

    String getCep() {
        return cep
    }

    void setCep(String cep) {
        this.cep = cep
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

    void setSenha(String senha) {
        this.senha = senha
    }


    List<CandidatoCompetencia> getCompetencias() {
        return competencias
    }

    void setCompetencias(List<CandidatoCompetencia> competencias) {
        this.competencias = competencias
    }

    List<Formacao> getFormacoes() {
        return formacoes
    }

    void setFormacoes(List<Formacao> formacoes) {
        this.formacoes = formacoes
    }

    List<Experiencia> getExperiencias() {
        return experiencias
    }

    void setExperiencias(List<Experiencia> experiencias) {
        this.experiencias = experiencias
    }

    void adicionarFormacao(Formacao formacao) {
        formacoes.add(formacao);
    }

    void removerFormacao(Formacao formacao) {
        formacoes.remove(formacao);
    }

    void adicionarExperiencia(Experiencia experiencia) {
        experiencias.add(experiencia);
    }

    void removerExperiencia(Experiencia experiencia) {
        experiencias.remove(experiencia);
    }

    void adicionarCompetencia(CandidatoCompetencia competencia) {
        competencias.add(competencia);
    }

    void removerCompetencia(CandidatoCompetencia competencia) {
        competencias.remove(competencia);
    }
}
