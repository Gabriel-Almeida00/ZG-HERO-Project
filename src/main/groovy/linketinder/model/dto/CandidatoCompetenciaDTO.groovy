package linketinder.model.dto

class CandidatoCompetenciaDTO {

    private Integer id
    private Integer idCandidato
    private Integer idCompetencia
    private Integer idNivelCompetencia

    CandidatoCompetenciaDTO(Integer id, Integer idCandidato, Integer idCompetencia, Integer idNivelCompetencia) {
        this.id = id
        this.idCandidato = idCandidato
        this.idCompetencia = idCompetencia
        this.idNivelCompetencia = idNivelCompetencia
    }

    Integer getId() {
        return id
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
