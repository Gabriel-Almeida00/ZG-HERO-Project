package linketinder.entity.dto

class CompetenciaDTO {
    Integer id
    String nome
    String nivel

    CompetenciaDTO(Integer id, String nome, String nivel) {
        this.id = id
        this.nome = nome
        this.nivel = nivel
    }
}
