package linketinder.model.dto

class CandidatoDTO {
    private Integer id
    private String nome
    private String descricao
    private List<FormacaoDTO> formacoes
    private List<ExperienciaDTO> experiencias
    private List<CompetenciaCandidatoDTO> competencias

    CandidatoDTO(Integer id, String nome, String descricao, List<FormacaoDTO> formacoes, List<ExperienciaDTO> experiencias, List<CompetenciaCandidatoDTO> competencias) {
        this.id = id
        this.nome = nome
        this.descricao = descricao
        this.formacoes = formacoes
        this.experiencias = experiencias
        this.competencias = competencias
    }

    void setId(Integer id) {
        this.id = id
    }

    void setNome(String nome) {
        this.nome = nome
    }

    void setDescricao(String descricao) {
        this.descricao = descricao
    }

    void setFormacoes(List<FormacaoDTO> formacoes) {
        this.formacoes = formacoes
    }

    void setExperiencias(List<ExperienciaDTO> experiencias) {
        this.experiencias = experiencias
    }

    void setCompetencias(List<String> competencias) {
        this.competencias = competencias
    }
}
