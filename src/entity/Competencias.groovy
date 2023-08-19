package entity

import entity.enums.NivelCompetencia

class Competencias {
    String nome
    NivelCompetencia nivel

    Competencias(String nome, NivelCompetencia nivel) {
        this.nome = nome
        this.nivel = nivel
    }
}
