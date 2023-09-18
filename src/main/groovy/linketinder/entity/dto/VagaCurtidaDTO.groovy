package linketinder.entity.dto

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

    String getDescricaoEmpresa() {
        return descricaoEmpresa
    }

    String getNomeVaga() {
        return nomeVaga
    }

    String getDescricaoVaga() {
        return descricaoVaga
    }
}
