package linketinder.entity

class Match {
    private Integer id
    private Integer idCandidato
    private Integer idVaga
    private Integer idEmpresa

    Match(Integer idCandidato, Integer idVaga, Integer idEmpresa) {
        this.idCandidato = idCandidato
        this.idVaga = idVaga
        this.idEmpresa = idEmpresa
    }

    void setId(Integer id) {
        this.id = id
    }
}
