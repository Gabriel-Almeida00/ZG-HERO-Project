package entity

class VagaCompetencia {

    Integer id
    Vaga vaga
    List<Competencias> competencias

    VagaCompetencia(Vaga vaga, List<Competencias> competencias) {
        this.vaga = vaga
        this.competencias = competencias
    }

    Integer getId() {
        return id
    }

    void setId(Integer id) {
        this.id = id
    }

    Vaga getVaga() {
        return vaga
    }

    void setVaga(Vaga vaga) {
        this.vaga = vaga
    }

    List<Competencias> getCompetencias() {
        return competencias
    }

    void setCompetencias(List<Competencias> competencias) {
        this.competencias = competencias
    }
}
