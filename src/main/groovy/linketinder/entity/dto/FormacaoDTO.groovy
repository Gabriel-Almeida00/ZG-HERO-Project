package linketinder.entity.dto

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

    String getAnoConclusao() {
        return anoConclusao
    }

    @Override
    String toString() {
        return curso + " - " + anoConclusao
    }
}
