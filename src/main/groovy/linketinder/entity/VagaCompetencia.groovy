package linketinder.entity

class VagaCompetencia {

    Integer id
    Integer idVaga
    Integer idCompetencia
    Integer nivel

    VagaCompetencia(Integer idVaga, Integer idCompetencia, Integer nivel) {
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

    Integer getNivel() {
        return nivel
    }

    void setNivel(Integer nivel) {
        this.nivel = nivel
    }
}
