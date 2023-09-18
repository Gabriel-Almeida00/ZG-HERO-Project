package linketinder.entity.dto

class CandidatoDTO {
    Integer id
    String descricao
    List<CompetenciaDTO> competencias = []
    List<FormacaoDTO> formacoes = []
    List<ExperienciaDTO> experiencias = []

    CandidatoDTO(
            Integer id,
            String descricao,
            List<CompetenciaDTO> competencias,
            List<FormacaoDTO> formacao,
            List<ExperienciaDTO> experiencias
    ) {
        this.id = id
        this.descricao = descricao
        this.competencias = competencias
        this.formacoes = formacao
        this.experiencias = experiencias
    }

    Integer getId() {
        return id
    }

    void setId(Integer id) {
        this.id = id
    }

    List<CompetenciaDTO> getCompetencias() {
        return competencias
    }

    void setCompetencias(List<CompetenciaDTO> competencias) {
        this.competencias = competencias
    }

    List<FormacaoDTO> getFormacoes() {
        return formacoes
    }

    List<ExperienciaDTO> getExperiencias() {
        return experiencias
    }

    String getDescricao() {
        return descricao
    }

    void setDescricao(String descricao) {
        this.descricao = descricao
    }
}
