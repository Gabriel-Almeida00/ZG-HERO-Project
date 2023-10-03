package linketinder.entity

class CandidatoCurtido {
    private Integer id
    private Integer idCandidato
    private Integer idEmpresa

    CandidatoCurtido(Integer idCandidato, Integer idEmpresa) {
        this.idCandidato = idCandidato
        this.idEmpresa = idEmpresa
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

    Integer getIdEmpresa() {
        return idEmpresa
    }
}
