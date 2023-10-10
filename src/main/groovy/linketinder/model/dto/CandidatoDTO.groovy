package linketinder.model.dto

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

    String getNome() {
        return nome
    }

    String getDescricao() {
        return descricao
    }

    List<String> getCompetencias() {
        return competencias
    }

}
