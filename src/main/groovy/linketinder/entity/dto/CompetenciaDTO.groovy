package linketinder.entity.dto

class CompetenciaDTO {
    Integer id
    String nome
    String nivel

    CompetenciaDTO(String nome, String nivel) {
        this.nome = nome
        this.nivel = nivel
    }

    String getNome() {
        return nome
    }

    void setNome(String nome) {
        this.nome = nome
    }

    String getNivel() {
        return nivel
    }

    Integer getId() {
        return id
    }

    void setId(Integer id) {
        this.id = id
    }

    void setNivel(String nivel) {
        this.nivel = nivel
    }

    @Override
    String toString() {
        return nome + " - " + nivel
    }
}
