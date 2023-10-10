package linketinder.model

class Formacao {

    private Integer id
    private Integer idCandidato
    private String instituicao
    private String curso
    private Integer nivel
    private String anoConclusao

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

    String getInstituicao() {
        return instituicao
    }

    String getCurso() {
        return curso
    }

    Integer getNivel() {
        return nivel
    }

    String getAnoConclusao() {
        return anoConclusao
    }
}
