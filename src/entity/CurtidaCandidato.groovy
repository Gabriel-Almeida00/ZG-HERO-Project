package entity

class CurtidaCandidato extends Curtida {
    Empresa empresa

    CurtidaCandidato(Empresa empresa, Candidato candidato) {
        super(candidato)
        this.empresa = empresa
    }

    Candidato getCandidato() {
        return candidato
    }

    Empresa getEmpresa() {
        return empresa
    }
}
