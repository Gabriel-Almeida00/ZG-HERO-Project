package entity

class CandidatoCurtido {
    Integer id
    Candidato candidato
    Empresa empresa

    CandidatoCurtido(Candidato candidato, Empresa empresa) {
        this.candidato = candidato
        this.empresa = empresa
    }

    Integer getId() {
        return id
    }

    void setId(Integer id) {
        this.id = id
    }

    Candidato getCandidato() {
        return candidato
    }

    void setCandidato(Candidato candidato) {
        this.candidato = candidato
    }

    Empresa getEmpresa() {
        return empresa
    }

    void setEmpresa(Empresa empresa) {
        this.empresa = empresa
    }
}
