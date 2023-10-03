package linketinder.entity

class Vaga {

    private Integer id
    private Integer idEmpresa
    private String nome
    private String descricao
    private String cidade
    private Integer formacaoMinima
    private Integer experienciaMinima
    private List<VagaCompetencia> competencias
    private List<VagaCurtida> curtida


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

    Integer getId() {
        return id
    }

    void setId(Integer id) {
        this.id = id
    }

    Integer getIdEmpresa() {
        return idEmpresa
    }

    String getNome() {
        return nome
    }

    String getDescricao() {
        return descricao
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
}
