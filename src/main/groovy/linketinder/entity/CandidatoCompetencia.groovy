package linketinder.entity

import linketinder.entity.enums.NivelCompetencia

class CandidatoCompetencia {

    Integer id
    Integer idCandidato
    Integer idCompetencia
    NivelCompetencia nivel

    CandidatoCompetencia(Integer idCandidato, Integer idCompetencia, NivelCompetencia nivel) {
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

    void setNivel(NivelCompetencia nivel) {
        this.nivel = nivel
    }
}
