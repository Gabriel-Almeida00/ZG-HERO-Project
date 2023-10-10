package linketinder.model

class VagaCompetencia {

    private Integer id
    private Integer idVaga
    private Integer idCompetencia
    private Integer nivel

    VagaCompetencia(Integer idVaga, Integer idCompetencia, Integer nivel) {
        this.idVaga = idVaga
        this.idCompetencia = idCompetencia
        this.nivel = nivel
    }

    void setId(Integer id) {
        this.id = id
    }

    Integer getIdVaga() {
        return idVaga
    }

    Integer getIdCompetencia() {
        return idCompetencia
    }

    Integer getNivel() {
        return nivel
    }
}
