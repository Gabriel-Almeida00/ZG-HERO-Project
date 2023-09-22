package linketinder.entity.dto

class MatchEmpresaDTO {
    private Integer idEmpresa
    private String nomeEmpresa
    private String descricaoEmpresa
    private Integer idVaga
    private String nomeVaga
    private String descricaoVaga

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

    Integer getIdEmpresa() {
        return idEmpresa
    }

    void setIdEmpresa(Integer idEmpresa) {
        this.idEmpresa = idEmpresa
    }

    String getNomeEmpresa() {
        return nomeEmpresa
    }


    String getDescricaoEmpresa() {
        return descricaoEmpresa
    }

    Integer getIdVaga() {
        return idVaga
    }

    void setIdVaga(Integer idVaga) {
        this.idVaga = idVaga
    }

    String getNomeVaga() {
        return nomeVaga
    }

    String getDescricaoVaga() {
        return descricaoVaga
    }
}
