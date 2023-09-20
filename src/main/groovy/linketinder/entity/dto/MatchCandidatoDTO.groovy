package linketinder.entity.dto

class MatchCandidatoDTO {
    Integer idCandidato
    String nomeCandidato
    String descricaoCandidato
    Integer idVaga
    String nomeVaga
    String descricaoVaga

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
}
