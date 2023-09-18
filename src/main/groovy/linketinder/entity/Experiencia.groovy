package linketinder.entity

import linketinder.entity.enums.NivelExperiencia


class Experiencia {

    Integer id
    Integer idCandidato
    String cargo
    String empresa
    NivelExperiencia nivel

    Experiencia(Integer idCandidato, String cargo, String empresa, NivelExperiencia nivel) {
        this.idCandidato = idCandidato
        this.cargo = cargo
        this.empresa = empresa
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

    String getCargo() {
        return cargo
    }

    String getEmpresa() {
        return empresa
    }

    void setEmpresa(String empresa) {
        this.empresa = empresa
    }

    String getNivel() {
        return nivel
    }

    void setNivel(NivelExperiencia nivel) {
        this.nivel = nivel
    }
}
