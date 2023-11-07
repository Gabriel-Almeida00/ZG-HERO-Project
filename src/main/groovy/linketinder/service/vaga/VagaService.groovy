package linketinder.service.vaga


import linketinder.dao.vaga.IVagaDao
import linketinder.model.Vaga
import linketinder.model.dto.VagaDTO

import java.sql.SQLException

class VagaService implements IVagaService {

    private IVagaDao vagaDao

    VagaService(IVagaDao vagaDao) {
        this.vagaDao = vagaDao
    }

    @Override
    List<VagaDTO> listarTodasVagas() {
        return vagaDao.listarTodasVagas()
    }

    @Override
    List<Vaga> listarVagasDaEmpresa(Integer idEmpresa) throws SQLException {
        return vagaDao.listarVagasDaEmpresa(idEmpresa)
    }

    @Override
    Integer obterIdEmpresaPorIdVaga(Integer idVaga) {
        return vagaDao.obterIdEmpresaPorIdVaga(idVaga)
    }

    @Override
    Vaga buscarVagaPorId(Integer idVaga) {
        return vagaDao.buscarVagaPorId(idVaga)
    }

    @Override
    void adicionarVaga(Vaga vaga) {
        vagaDao.adicionarVaga(vaga)
    }

    @Override
    void atualizarVaga(Vaga vaga) {
        vagaDao.atualizarVaga(vaga)
    }

    @Override
    void excluirVaga(Integer idVaga) {
        vagaDao.excluirVaga(idVaga)
    }
}
