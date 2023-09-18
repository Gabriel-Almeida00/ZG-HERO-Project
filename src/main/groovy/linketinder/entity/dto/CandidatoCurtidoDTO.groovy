package linketinder.entity.dto

class CandidatoCurtidoDTO {
    String nomeCandidato
    String descricaoCandidato
    String nomeVaga
    String descricaoVaga

    CandidatoCurtidoDTO(String nomeCandidato, String descricaoCandidato, String nomeVaga, String descricaoVaga) {
        this.nomeCandidato = nomeCandidato
        this.descricaoCandidato = descricaoCandidato
        this.nomeVaga = nomeVaga
        this.descricaoVaga = descricaoVaga
    }

    String getNomeCandidato() {
        return nomeCandidato
    }

    String getDescricaoCandidato() {
        return descricaoCandidato
    }

    String getNomeVaga() {
        return nomeVaga
    }

    String getDescricaoVaga() {
        return descricaoVaga
    }
}
