package entity

import entity.enums.NivelFormacao

class Formacao {
    String instituicao
    String curso
    NivelFormacao nivel
    int anoConclusao

    Formacao(String instituicao, String curso, NivelFormacao nivel, int anoConclusao) {
        this.instituicao = instituicao
        this.curso = curso
        this.nivel = nivel
        this.anoConclusao = anoConclusao
    }
}
