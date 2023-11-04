package linketinder.model

class CandidatoCompetencia {

    private Integer id
    private Integer idCandidato
    private Integer idCompetencia
    private Integer idNivelCompetencia



    CandidatoCompetencia(Integer idCandidato, Integer idCompetencia, Integer idNivelCompetencia) {
        this.idCandidato = idCandidato
        this.idCompetencia = idCompetencia
        this.idNivelCompetencia = idNivelCompetencia
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

    Integer getIdCompetencia() {
        return idCompetencia
    }

    Integer getIdNivelCompetencia() {
        return idNivelCompetencia
    }
}
