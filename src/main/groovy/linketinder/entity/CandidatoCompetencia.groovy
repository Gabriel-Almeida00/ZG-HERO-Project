package linketinder.entity

class CandidatoCompetencia {

    private Integer id
    private Integer idCandidato
    private Integer idCompetencia
    private Integer nivel



    CandidatoCompetencia(Integer idCandidato, Integer idCompetencia, Integer nivel) {
        this.idCandidato = idCandidato
        this.idCompetencia = idCompetencia
        this.nivel = nivel
    }

    Integer getId() {
        return id
    }

    void setId(Integer id) {
        this.id = id
    }

    Integer getIdCandidato() {
        return idCandidato
    }

    void setIdCandidato(Integer idCandidato) {
        this.idCandidato = idCandidato
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
