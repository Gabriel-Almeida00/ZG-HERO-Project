package linketinder.entity

import linketinder.entity.enums.NivelExperiencia
import linketinder.entity.enums.NivelFormacao

class Vaga {

    Integer id
    Integer idEmpresa
    String nome
    String descricao
    String cidade
    NivelFormacao formacaoMinima
    NivelExperiencia experienciaMinima
    List<VagaCompetencia> competencias
    List<VagaCurtida> curtida


    Vaga(Integer idEmpresa,
         String nome,
         String descricao,
         String cidade,
         NivelFormacao formacaoMinima,
         NivelExperiencia experienciaMinima
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

    String getFormacaoMinima() {
        return formacaoMinima
    }

    String getExperienciaMinima() {
        return experienciaMinima
    }

    List<VagaCompetencia> getCompetencias() {
        return competencias
    }

    void setCompetencias(List<VagaCompetencia> competencias) {
        this.competencias = competencias
    }
}
