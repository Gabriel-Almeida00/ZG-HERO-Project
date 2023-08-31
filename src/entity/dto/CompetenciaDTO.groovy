package entity.dto

class CompetenciaDTO {
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

    void setNivel(String nivel) {
        this.nivel = nivel
    }
}
