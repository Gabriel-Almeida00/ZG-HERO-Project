package linketinder.controller.vaga

import linketinder.model.Vaga
import linketinder.model.dto.VagaDTO
import linketinder.service.vaga.IVagaService

import java.sql.SQLException

class VagaController {
    private IVagaService vagaService

    VagaController(IVagaService vagaService) {
        this.vagaService = vagaService
    }

    List<VagaDTO> listarTodasVagas() {
        return vagaService.listarTodasVagas()
    }

    List<VagaDTO> listarVagasDaEmpresa(Integer idEmpresa) {
        return vagaService.listarVagasDaEmpresa(idEmpresa)
    }

    Integer obterIdEmpresaPorIdVaga(Integer idVaga) {
        return vagaService.obterIdEmpresaPorIdVaga(idVaga)
    }

    Vaga buscarVagaPorId(Integer idVaga) {
        return vagaService.buscarVagaPorId(idVaga)
    }

    void adicionarVaga(Vaga vaga) {
        vagaService.adicionarVaga(vaga)
    }

    void atualizarVaga(Vaga vaga) {
        vagaService.atualizarVaga(vaga)
    }

    void excluirVaga(Integer idVaga) {
        vagaService.excluirVaga(idVaga)
    }
}
