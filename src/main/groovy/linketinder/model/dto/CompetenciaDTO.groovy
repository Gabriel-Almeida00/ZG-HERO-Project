package linketinder.model.dto

class CompetenciaDTO {

    private Integer id
    private String nome
    private String nivel

    CompetenciaDTO(Integer id, String nome, String nivel) {
        this.id = id
        this.nome = nome
        this.nivel = nivel
    }

    void setId(Integer id) {
        this.id = id
    }

    Integer getId() {
        return id
    }

    String getNome() {
        return nome
    }

    String getNivel() {
        return nivel
    }
}
