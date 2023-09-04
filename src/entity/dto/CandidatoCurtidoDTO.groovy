package entity.dto

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

    void setNomeCandidato(String nomeCandidato) {
        this.nomeCandidato = nomeCandidato
    }

    String getDescricaoCandidato() {
        return descricaoCandidato
    }

    void setDescricaoCandidato(String descricaoCandidato) {
        this.descricaoCandidato = descricaoCandidato
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
