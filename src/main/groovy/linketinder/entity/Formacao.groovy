package linketinder.entity

import linketinder.entity.enums.NivelFormacao


class Formacao {

    Integer id
    Integer idCandidato
    String instituicao
    String curso
    Integer nivel
    String anoConclusao

    Formacao(Integer idCandidato, String instituicao, String curso, Integer nivel, String anoConclusao) {
        this.idCandidato = idCandidato
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

    Integer getIdCandidato() {
        return idCandidato
    }

    void setIdCandidato(Integer idCandidato) {
        this.idCandidato = idCandidato
    }

    String getInstituicao() {
        return instituicao
    }

    String getCurso() {
        return curso
    }

    Integer getNivel() {
        return nivel
    }

    void setNivel(Integer nivel) {
        this.nivel = nivel
    }

    String getAnoConclusao() {
        return anoConclusao
    }
}
