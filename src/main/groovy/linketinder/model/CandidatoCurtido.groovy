package linketinder.model

class CandidatoCurtido {
    private Integer idCandidato
    private Integer idEmpresa

    CandidatoCurtido(Integer idCandidato, Integer idEmpresa) {
        this.idCandidato = idCandidato
        this.idEmpresa = idEmpresa
    }



    Integer getIdCandidato() {
        return idCandidato
    }

    void setIdCandidato(Integer idCandidato) {
        this.idCandidato = idCandidato
    }

    Integer getIdEmpresa() {
        return idEmpresa
    }
}
