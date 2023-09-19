package linketinder.entity.dto

class CompetenciaDTO {
    Integer id
    String nome
    List<Integer> nivel

    CompetenciaDTO(String nome, List<Integer> nivel) {
        this.nome = nome
        this.nivel = nivel
    }

    @Override
    public String toString() {
        return "CompetenciaDTO{" +
                "id=" + id +
                ", nome='" + nome + '\'' +
                ", nivel=" + nivel +
                '}';
    }
}
