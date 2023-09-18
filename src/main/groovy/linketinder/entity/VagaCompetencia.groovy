package linketinder.entity

class VagaCompetencia {

    Integer id
    Integer idVaga
    Integer idCompetencia
    NivelCompetencia nivel

    VagaCompetencia(Integer idVaga, Integer idCompetencia, NivelCompetencia nivel) {
        this.idVaga = idVaga
        this.idCompetencia = idCompetencia
        this.nivel = nivel
    }

    Integer getId() {
        return id
    }

    void setId(Integer id) {
        this.id = id
    }

    Integer getIdVaga() {
        return idVaga
    }

    void setIdVaga(Integer idVaga) {
        this.idVaga = idVaga
    }

    Integer getIdCompetencia() {
        return idCompetencia
    }

    void setIdCompetencia(Integer idCompetencia) {
        this.idCompetencia = idCompetencia
    }

    String getNivel() {
        return nivel
    }

    void setNivel(NivelCompetencia nivel) {
        this.nivel = nivel
    }
}
