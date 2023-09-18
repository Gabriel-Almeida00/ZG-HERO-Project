package linketinder.entity


class Formacao {

    Integer id
    Integer idCandidato
    String instituicao
    String curso
    String nivel
    String anoConclusao

    Formacao(Integer idCandidato, String instituicao, String curso, String nivel, String anoConclusao) {
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

    String getNivel() {
        return nivel
    }

    void setNivel(String nivel) {
        this.nivel = nivel
    }

    String getAnoConclusao() {
        return anoConclusao
    }
}
