package linketinder.entity

class Vaga {

    Integer id
    Integer idEmpresa
    String nome
    String descricao
    String cidade
    Integer formacaoMinima
    Integer experienciaMinima
    List<VagaCompetencia> competencias
    List<VagaCurtida> curtida


    Vaga(Integer idEmpresa,
         String nome,
         String descricao,
         String cidade,
         Integer formacaoMinima,
         Integer experienciaMinima
         ) {
        this.idEmpresa = idEmpresa
        this.nome = nome
        this.descricao = descricao
        this.cidade = cidade
        this.formacaoMinima = formacaoMinima
        this.experienciaMinima = experienciaMinima
        competencias = []
        curtida = []
    }

    List<VagaCurtida> getCurtida() {
        return curtida
    }

    void setCurtida(List<VagaCurtida> curtida) {
        this.curtida = curtida
    }

    Integer getId() {
        return id
    }

    void setId(Integer id) {
        this.id = id
    }

    Integer getIdEmpresa() {
        return idEmpresa
    }

    void setIdEmpresa(Integer idEmpresa) {
        this.idEmpresa = idEmpresa
    }

    String getNome() {
        return nome
    }

    void setNome(String nome) {
        this.nome = nome
    }

    String getDescricao() {
        return descricao
    }

    void setDescricao(String descricao) {
        this.descricao = descricao
    }

    String getCidade() {
        return cidade
    }

    Integer getFormacaoMinima() {
        return formacaoMinima
    }

    Integer getExperienciaMinima() {
        return experienciaMinima
    }

    List<VagaCompetencia> getCompetencias() {
        return competencias
    }

    void setCompetencias(List<VagaCompetencia> competencias) {
        this.competencias = competencias
    }
}
