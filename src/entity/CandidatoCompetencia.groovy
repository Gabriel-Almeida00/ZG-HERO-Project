package entity

class CandidatoCompetencia {

    Integer id
    Candidato candidato
    List<Competencias> competencia

    CandidatoCompetencia(Candidato candidato, List<Competencias> competencia) {
        this.candidato = candidato
        this.competencia = competencia
    }

    Integer getId() {
        return id
    }

    void setId(Integer id) {
        this.id = id
    }

    Candidato getCandidato() {
        return candidato
    }

    void setCandidato(Candidato candidato) {
        this.candidato = candidato
    }

    List<Competencias> getCompetencia() {
        return competencia
    }

    void setCompetencia(List<Competencias> competencia) {
        this.competencia = competencia
    }
}
