package linketinder.service.empresa


import linketinder.dao.empresa.IEmpresaDao
import linketinder.model.Empresa

class EmpresaService implements IEmpresaService {

    private final IEmpresaDao empresaDao

    EmpresaService(IEmpresaDao empresaDao) {
        this.empresaDao = empresaDao
    }

    @Override
    List<Empresa> listarTodasEmpresas() {
        return empresaDao.listarTodasEmpresas()
    }

    @Override
    Empresa obterEmpresaPorId(Integer id) {
        return empresaDao.buscarEmpresaPorId(id)
    }



    @Override
    void atualizarEmpresa(Empresa empresa) {
        empresaDao.atualizarEmpresa(empresa)
    }

    @Override
    void excluirEmpresa(Integer id) {
        empresaDao.excluirEmpresa(id)
    }
}
