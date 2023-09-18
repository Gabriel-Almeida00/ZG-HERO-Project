package linketinder.entity.dto

class CompetenciaDTO {
    Integer id
    String nome
    Integer nivel

    CompetenciaDTO(String nome, Integer nivel) {
        this.nome = nome
        this.nivel = nivel
    }

    String getNome() {
        return nome
    }

    void setNome(String nome) {
        this.nome = nome
    }

    Integer getNivel() {
        return nivel
    }

    Integer getId() {
        return id
    }

    void setId(Integer id) {
        this.id = id
    }

    void setNivel(Integer nivel) {
        this.nivel = nivel
    }

    @Override
    String toString() {
        return nome + " - " + nivel
    }
}
