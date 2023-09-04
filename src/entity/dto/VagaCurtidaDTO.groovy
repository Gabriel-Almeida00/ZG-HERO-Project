package entity.dto

class VagaCurtidaDTO {
    String nomeEmpresa
    String descricaoEmpresa
    String nomeVaga
    String descricaoVaga

    VagaCurtidaDTO(String nomeEmpresa, String descricaoEmpresa, String nomeVaga, String descricaoVaga) {
        this.nomeEmpresa = nomeEmpresa
        this.descricaoEmpresa = descricaoEmpresa
        this.nomeVaga = nomeVaga
        this.descricaoVaga = descricaoVaga
    }

    String getNomeEmpresa() {
        return nomeEmpresa
    }

    void setNomeEmpresa(String nomeEmpresa) {
        this.nomeEmpresa = nomeEmpresa
    }

    String getDescricaoEmpresa() {
        return descricaoEmpresa
    }

    void setDescricaoEmpresa(String descricaoEmpresa) {
        this.descricaoEmpresa = descricaoEmpresa
    }

    String getNomeVaga() {
        return nomeVaga
    }

    void setNomeVaga(String nomeVaga) {
        this.nomeVaga = nomeVaga
    }

    String getDescricaoVaga() {
        return descricaoVaga
    }

    void setDescricaoVaga(String descricaoVaga) {
        this.descricaoVaga = descricaoVaga
    }
}
