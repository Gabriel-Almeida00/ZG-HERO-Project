package linketinder.model.dto


class EmpresaDTO {
    private Integer id
    private String pais
    private String descricaoEmpresa

    EmpresaDTO(String pais, String descricaoEmpresa) {
        this.pais = pais
        this.descricaoEmpresa = descricaoEmpresa
    }

    void setId(Integer id) {
        this.id = id
    }

    String getPais() {
        return pais
    }

    String getDescricaoEmpresa() {
        return descricaoEmpresa
    }
}
