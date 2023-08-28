package entity.DTO

import entity.Candidato
import entity.Competencias
import entity.CandidatoCurtido
import entity.Experiencia
import entity.Formacao

class CandidatoDTO {
    List<Competencias> competencias = []
    List<Formacao> formacoes = []
    List<Experiencia> experiencias = []
    List<CandidatoCurtido> curtidas = []

    CandidatoDTO(Candidato candidato) {
        this.competencias = candidato.getCompetencias()
        this.formacoes = candidato.getFormacoes()
        this.experiencias = candidato.getExperiencias()
        this.curtidas = candidato.getCurtidas()
    }
}
