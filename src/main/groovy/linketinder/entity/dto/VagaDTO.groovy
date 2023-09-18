package linketinder.entity.dto

class VagaDTO {
    Integer id
    String nome
    String cidade
    String descricao
    Integer formacaoMinima
    Integer experienciaMinima
    List<CompetenciaDTO> competencias

    VagaDTO(
            Integer id,
            String nome,
            String cidade,
            String descricao,
            Integer formacaoMinima,
            Integer experienciaMinima,
            List<CompetenciaDTO> competencias
    ) {
        this.id = id
        this.nome = nome
        this.cidade = cidade
        this.descricao = descricao
        this.formacaoMinima = formacaoMinima
        this.experienciaMinima = experienciaMinima
        this.competencias = competencias
    }

    Integer getId() {
        return id
    }

    void setId(Integer id) {
        this.id = id
    }

    String getNome() {
        return nome
    }

    void setNome(String nome) {
        this.nome = nome
    }

    String getCidade() {
        return cidade
    }

    String getDescricao() {
        return descricao
    }

    void setDescricao(String descricao) {
        this.descricao = descricao
    }

    Integer getFormacaoMinima() {
        return formacaoMinima
    }

    Integer getExperienciaMinima() {
        return experienciaMinima
    }

    List<CompetenciaDTO> getCompetencias() {
        return competencias
    }

    void setCompetencias(List<CompetenciaDTO> competencias) {
        this.competencias = competencias
    }
}
