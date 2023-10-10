package linketinder.model

class Experiencia {

    private Integer id
    private Integer idCandidato
    private String cargo
    private String empresa
    private Integer nivel

    Experiencia(Integer idCandidato, String cargo, String empresa, Integer nivel) {
        this.idCandidato = idCandidato
        this.cargo = cargo
        this.empresa = empresa
        this.nivel = nivel
    }

    Integer getId() {
        return id
    }

    void setId(Integer id) {
        this.id = id
    }

    Integer getIdCandidato() {
        return idCandidato
    }

    String getCargo() {
        return cargo
    }

    String getEmpresa() {
        return empresa
    }

    Integer getNivel() {
        return nivel
    }
}
