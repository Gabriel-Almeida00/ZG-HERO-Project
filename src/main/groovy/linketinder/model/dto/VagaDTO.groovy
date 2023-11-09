package linketinder.model.dto

class VagaDTO {
    private Integer idVaga
    private String nome
    private String descricao
    private String cidade
    private List<FormacaoVagaDTO> formacao
    private List<ExperienciaVagaDTO> experiencia
    private List<CompetenciaCandidatoDTO> competencias

    VagaDTO(
            Integer idVaga,
            String nome,
            String descricao,
            String cidade,
            List<FormacaoVagaDTO> formacao,
            List<ExperienciaVagaDTO> experiencia,
            List<CompetenciaCandidatoDTO> competencias
    ) {
        this.idVaga = idVaga
        this.nome = nome
        this.descricao = descricao
        this.cidade = cidade
        this.formacao = formacao
        this.experiencia = experiencia
        this.competencias = competencias
    }
}
