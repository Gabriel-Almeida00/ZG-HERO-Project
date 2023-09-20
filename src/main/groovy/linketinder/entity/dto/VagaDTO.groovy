package linketinder.entity.dto

class VagaDTO {
    Integer id
    String nome
    String descricao
    List<String> nomeCompetencia

    VagaDTO(Integer id, String nome, String descricao, List<String> nomeCompetencia) {
        this.id = id
        this.nome = nome
        this.descricao = descricao
        this.nomeCompetencia = nomeCompetencia
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

    List<String> getNomeCompetencia() {
        return nomeCompetencia
    }

    void setNomeCompetencia(List<String> nomeCompetencia) {
        this.nomeCompetencia = nomeCompetencia
    }
}
