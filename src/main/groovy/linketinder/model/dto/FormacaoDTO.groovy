package linketinder.model.dto

class FormacaoDTO {
    private String instituicaoFormacao
    private String cursoFormacao
    private String anoConclusaoFormacao

    FormacaoDTO(String instituicaoFormacao, String cursoFormacao, String anoConclusaoFormacao) {
        this.instituicaoFormacao = instituicaoFormacao
        this.cursoFormacao = cursoFormacao
        this.anoConclusaoFormacao = anoConclusaoFormacao
    }
}
