package linketinder.controller.empresa

import linketinder.model.Empresa
import linketinder.service.empresa.IEmpresaService

class EmpresaController {
    private IEmpresaService empresaService

    EmpresaController(IEmpresaService empresaService) {
        this.empresaService = empresaService
    }

    List<Empresa> listarTodasEmpresas() {
        return empresaService.listarTodasEmpresas()
    }

    Empresa obterEmpresaPorId(Integer id) {
        return empresaService.obterEmpresaPorId(id)
    }

    void adicionarEmpresa(Empresa empresa) {
        empresaService.adicionarEmpresa(empresa)
    }

    void atualizarEmpresa(Empresa empresa) {
        empresaService.atualizarEmpresa(empresa)
    }

    void excluirEmpresa(Integer id) {
        empresaService.excluirEmpresa(id)
    }
}
