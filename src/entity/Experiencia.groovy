package entity

import entity.enums.NivelExperiencia

class Experiencia {
    String cargo
    String empresa
    NivelExperiencia nivel

    Experiencia(String cargo, String empresa, NivelExperiencia nivel) {
        this.cargo = cargo
        this.empresa = empresa
        this.nivel = nivel
    }
}
