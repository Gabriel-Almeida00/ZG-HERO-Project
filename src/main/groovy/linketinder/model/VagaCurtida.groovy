package linketinder.model

class VagaCurtida {
    private Integer idCandidata
    private Integer idVaga

    VagaCurtida(Integer idCandidata, Integer idVaga) {
        this.idCandidata = idCandidata
        this.idVaga = idVaga
    }

    Integer getIdCandidata() {
        return idCandidata
    }

    Integer getIdVaga() {
        return idVaga
    }
}
