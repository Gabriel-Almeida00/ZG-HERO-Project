package entity

import entity.enums.NivelFormacao

class Formacao {

    Integer id
    String instituicao
    String curso
    String nivel
    String anoConclusao

    Formacao(String instituicao, String curso, String nivel, String anoConclusao) {
        this.instituicao = instituicao
        this.curso = curso
        this.nivel = nivel
        this.anoConclusao = anoConclusao
    }

    Integer getId() {
        return id
    }

    void setId(Integer id) {
        this.id = id
    }

    String getInstituicao() {
        return instituicao
    }

    void setInstituicao(String instituicao) {
        this.instituicao = instituicao
    }

    String getCurso() {
        return curso
    }

    void setCurso(String curso) {
        this.curso = curso
    }

    String getNivel() {
        return nivel
    }

    void setNivel(String nivel) {
        this.nivel = nivel
    }

    String getAnoConclusao() {
        return anoConclusao
    }

    void setAnoConclusao(String anoConclusao) {
        this.anoConclusao = anoConclusao
    }
}
