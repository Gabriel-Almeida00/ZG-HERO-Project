package linketinder.model.dto

class MatchCandidatoDTO {
    private Integer idCandidato
    private String nomeCandidato
    private String descricaoCandidato
    private Integer idVaga
    private String nomeVaga
    private String descricaoVaga

    MatchCandidatoDTO(
            Integer idCandidato,
            String nomeCandidato,
            String descricaoCandidato,
            Integer idVaga,
            String nomeVaga,
            String descricaoVaga
    ) {
        this.idCandidato = idCandidato
        this.nomeCandidato = nomeCandidato
        this.descricaoCandidato = descricaoCandidato
        this.idVaga = idVaga
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
