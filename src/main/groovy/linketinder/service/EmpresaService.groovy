package linketinder.service


import linketinder.dao.candidato.ICandidatoDao
import linketinder.dao.curtida.CurtidaDao
import linketinder.dao.curtida.ICurtidaDao
import linketinder.dao.empresa.IEmpresaDao
import linketinder.entity.Empresa

class EmpresaService implements IEmpresa {

    private final IEmpresaDao empresaDao
    private final ICandidatoDao candidatoDao
    private final ICurtidaDao curtidaDao

    EmpresaService(IEmpresaDao empresaDao, ICandidatoDao candidatoDao, ICurtidaDao curtidaDao) {
        this.empresaDao = empresaDao
        this.curtidaDao = curtidaDao
        this.candidatoDao = candidatoDao
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
    void adicionarEmpresa(Empresa empresa) {
        empresaDao.adicionarEmpresa(empresa)
    }

    @Override
    void atualizarEmpresa(Empresa empresa) {
        empresaDao.atualizarEmpresa(empresa)
    }

    @Override
    void excluirEmpresa(Integer id) {
        empresaDao.excluirEmpresa(id)
    }

    @Override
    void curtirCandidato(Integer idCandidato, Integer idEmpresa) {
        Integer idVaga = curtidaDao.verificaCurtidaDoCandidato(idCandidato)
        if (idVaga == CurtidaDao.CURTIDA_NAO_ENCONTRADA) {
            curtidaDao.curtirCandidato(idCandidato, idEmpresa)
        } else {
            curtidaDao.AtualizarCurtidaComIdEmpresa(idVaga, idEmpresa, idCandidato)
        }
    }
}
