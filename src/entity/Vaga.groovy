package entity

import entity.enums.NivelExperiencia
import entity.enums.NivelFormacao

class Vaga {

    Integer id
    Integer idEmpresa
    String nome
    String descricao
    String cidade
    String formacaoMinima
    String experienciaMinima
    List<VagaCompetencia> competencias


    Vaga(Integer idEmpresa,
         String nome,
         String descricao,
         String cidade,
         String formacaoMinima,
         String experienciaMinima
         ) {
        this.idEmpresa = idEmpresa
        this.nome = nome
        this.descricao = descricao
        this.cidade = cidade
        this.formacaoMinima = formacaoMinima
        this.experienciaMinima = experienciaMinima
        competencias = []
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

    void setCidade(String cidade) {
        this.cidade = cidade
    }

    String getFormacaoMinima() {
        return formacaoMinima
    }

    void setFormacaoMinima(String formacaoMinima) {
        this.formacaoMinima = formacaoMinima
    }

    String getExperienciaMinima() {
        return experienciaMinima
    }

    void setExperienciaMinima(String experienciaMinima) {
        this.experienciaMinima = experienciaMinima
    }

    List<VagaCompetencia> getCompetencias() {
        return competencias
    }

    void setCompetencias(List<VagaCompetencia> competencias) {
        this.competencias = competencias
    }
}
