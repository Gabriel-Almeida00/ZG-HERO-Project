package linketinder.entity.dto

class MatchEmpresaDTO {
    Integer idEmpresa
    String nomeEmpresa
    String descricaoEmpresa
    Integer idVaga
    String nomeVaga
    String descricaoVaga

    MatchEmpresaDTO(
            Integer idEmpresa,
            String nomeEmpresa,
            String descricaoEmpresa,
            Integer idVaga,
            String nomeVaga,
            String descricaoVaga
    ) {
        this.idEmpresa = idEmpresa
        this.nomeEmpresa = nomeEmpresa
        this.descricaoEmpresa = descricaoEmpresa
        this.idVaga = idVaga
        this.nomeVaga = nomeVaga
        this.descricaoVaga = descricaoVaga
    }
}
