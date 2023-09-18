package linketinder.entity

import linketinder.entity.enums.CurtidaStatus

class Curtida {
    private Integer id
    private Integer idCandidato
    private Integer idVaga
    private Integer idEmpresa
    private CurtidaStatus curtidaStatus

    Curtida(Integer idCandidato, Integer idVaga, Integer idEmpresa, CurtidaStatus curtidaStatus) {
        this.idCandidato = idCandidato
        this.idVaga = idVaga
        this.idEmpresa = idEmpresa
        this.curtidaStatus = curtidaStatus
    }

    void setId(Integer id) {
        this.id = id
    }
}
