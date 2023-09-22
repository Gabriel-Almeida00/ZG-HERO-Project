package linketinder.entity.dto

class CandidatoDTO {
    private Integer id
    private String nome
    private String descricao
    private List<String> competencias

     CandidatoDTO(Integer id, String nome, String descricao, List<String> competencias) {
        this.id = id
        this.nome = nome
        this.descricao = descricao
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

    String getDescricao() {
        return descricao
    }

    void setDescricao(String descricao) {
        this.descricao = descricao
    }

    List<String> getCompetencias() {
        return competencias
    }

    void setCompetencias(List<String> competencias) {
        this.competencias = competencias
    }
}
