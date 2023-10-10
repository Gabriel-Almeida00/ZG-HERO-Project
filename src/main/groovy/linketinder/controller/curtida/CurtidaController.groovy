package linketinder.controller.curtida

import linketinder.model.dto.CandidatoQueCurtiuVagaDTO
import linketinder.model.dto.EmpresaDTO
import linketinder.service.curtida.ICurtidaService

class CurtidaController {
    private ICurtidaService curtidaService

    CurtidaController(ICurtidaService curtidaService) {
        this.curtidaService = curtidaService
    }

    void curtirVaga(Integer idCandidato, Integer idVaga){
        curtidaService.curtirVaga(idCandidato, idVaga)
    }

    void curtirCandidato(Integer idCandidato, Integer idEmpresa){
        curtidaService.curtirCandidato(idCandidato, idEmpresa)
    }

    List<EmpresaDTO> listarEmpresaQueCurtiramCandidato(Integer idCandidato){
        return curtidaService.listarEmpresasQueCurtiramCandidato(idCandidato)
    }

    List<CandidatoQueCurtiuVagaDTO> listarCandidatosQueCurtiramVaga(Integer idVaga) {
        return curtidaService.listarCandidatosQueCurtiramVaga(idVaga)
    }

}
