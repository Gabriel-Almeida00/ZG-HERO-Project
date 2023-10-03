package linketinder.entity.dto

class VagaDTO {
    private Integer id
    private String nome
    private String descricao
    private List<String> nomeCompetencia

    VagaDTO(Integer id, String nome, String descricao, List<String> nomeCompetencia) {
        this.id = id
        this.nome = nome
        this.descricao = descricao
        this.nomeCompetencia = nomeCompetencia
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

    List<String> getNomeCompetencia() {
        return nomeCompetencia
    }
}
