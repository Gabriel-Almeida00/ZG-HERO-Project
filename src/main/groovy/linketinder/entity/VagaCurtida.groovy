package linketinder.entity

class VagaCurtida {
    Integer id
    Integer idCandidata
    Integer idVaga

    VagaCurtida(Integer idCandidata, Integer idVaga) {
        this.idCandidata = idCandidata
        this.idVaga = idVaga
    }

    Integer getId() {
        return id
    }

    void setId(Integer id) {
        this.id = id
    }

    Integer getIdCandidata() {
        return idCandidata
    }

    void setIdCandidata(Integer idCandidata) {
        this.idCandidata = idCandidata
    }

    Integer getIdVaga() {
        return idVaga
    }

    void setIdVaga(Integer idVaga) {
        this.idVaga = idVaga
    }
}
