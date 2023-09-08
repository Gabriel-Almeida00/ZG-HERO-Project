package linketinder.entity

class CandidatoCompetencia {

    Integer id;
    Integer idCandidato;
    Integer idCompetencia;
    String nivel;

    CandidatoCompetencia(Integer idCandidato, Integer idCompetencia, String nivel) {
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

    String getNivel() {
        return nivel
    }

    void setNivel(String nivel) {
        this.nivel = nivel
    }
}
