package linketinder.model.dto

class FormacaoDTO {
    private String instituicaoFormacao
    private String cursoFormacao
    private String anoConclusaoFormacao
    private Integer nivel

    FormacaoDTO(String instituicaoFormacao, String cursoFormacao, String anoConclusaoFormacao, Integer nivel) {
        this.instituicaoFormacao = instituicaoFormacao
        this.cursoFormacao = cursoFormacao
        this.anoConclusaoFormacao = anoConclusaoFormacao
        this.nivel = nivel
    }
}
