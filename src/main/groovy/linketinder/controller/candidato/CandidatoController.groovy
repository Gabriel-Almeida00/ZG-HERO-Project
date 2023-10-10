package linketinder.controller.candidato

import linketinder.model.Candidato
import linketinder.model.dto.CandidatoDTO
import linketinder.service.candidato.ICandidatoService

class CandidatoController {
    private ICandidatoService candidatoService

    CandidatoController(ICandidatoService candidatoService) {
        this.candidatoService = candidatoService
    }

    List<CandidatoDTO> listarCandidatos(){
        return candidatoService.listarCandidatos()
    }

    void criarCandidato(Candidato candidato){
        candidatoService.cadastrarCandidato(candidato)
    }

    void atualizarCandidato(Candidato candidato){
        candidatoService.atualizarCandidato(candidato)
    }

    void excluirCandidato(Integer candidatoId){
        candidatoService.deletarCandidato(candidatoId)
    }
}
