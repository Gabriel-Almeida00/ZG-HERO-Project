package linketinder.entity.dto


class EmpresaDTO {
    Integer id
    String pais
    String descricaoEmpresa

    EmpresaDTO(String pais, String descricaoEmpresa) {
        this.pais = pais
        this.descricaoEmpresa = descricaoEmpresa
    }

    void setId(Integer id) {
        this.id = id
    }
}
