package entity.dto

class FormacaoDTO {
    String curso
    String anoConclusao

    FormacaoDTO(String curso, String anoConclusao) {
        this.curso = curso
        this.anoConclusao = anoConclusao
    }

    String getCurso() {
        return curso
    }

    void setCurso(String curso) {
        this.curso = curso
    }

    String getAnoConclusao() {
        return anoConclusao
    }

    void setAnoConclusao(String anoConclusao) {
        this.anoConclusao = anoConclusao
    }
}
