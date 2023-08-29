package entity

class CandidatoCompetencia {

    Integer id;
    Integer idCandidato;
    Integer idCompetencia;

    CandidatoCompetencia(Integer idCandidato, Integer idCompetencia) {
        this.idCandidato = idCandidato
        this.idCompetencia = idCompetencia
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
}
