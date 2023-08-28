package entity

import entity.enums.NivelExperiencia

class Experiencia {

    Integer id
    String cargo
    String empresa
    String nivel

    Experiencia(String cargo, String empresa, String nivel) {
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

    String getCargo() {
        return cargo
    }

    void setCargo(String cargo) {
        this.cargo = cargo
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

    void setNivel(String nivel) {
        this.nivel = nivel
    }
}
