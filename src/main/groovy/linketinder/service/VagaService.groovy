package linketinder.service


import linketinder.Exception.DataBaseException
import linketinder.Exception.VagaNotFoundException
import linketinder.dao.curtida.ICurtidaDao
import linketinder.dao.vaga.IVagaCompetenciaDao
import linketinder.dao.vaga.IVagaDao
import linketinder.entity.Vaga
import linketinder.entity.VagaCompetencia
import linketinder.entity.dto.CandidatoQueCurtiuVagaDTO
import linketinder.entity.dto.CompetenciaDTO
import linketinder.entity.dto.VagaDTO

import java.sql.SQLException

class VagaService implements IVagaService {

    private IVagaDao vagaDao
    private IVagaCompetenciaDao vagaCompetenciaDao
    private ICurtidaDao curtidaDao

    VagaService(IVagaDao vagaDao, IVagaCompetenciaDao vagaCompetenciaDao, ICurtidaDao curtidaDao) {
        this.vagaDao = vagaDao
        this.vagaCompetenciaDao = vagaCompetenciaDao
        this.curtidaDao = curtidaDao
    }

    @Override
    List<VagaDTO> listarTodasVagas() {
        return vagaDao.listarTodasVagas()
    }

    @Override
    List<VagaDTO> listarVagasDaEmpresa(int idEmpresa) throws SQLException {
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


    @Override
    void adicionarVagaCompetencia(VagaCompetencia vagaCompetencia) {
        vagaCompetenciaDao.adicionarVagaCompetencia(vagaCompetencia)
    }

    @Override
    void atualizarNivelVagaCompetencia(VagaCompetencia vagaCompetencia) {
        vagaCompetenciaDao.atualizarNivelVagaCompetencia(vagaCompetencia)
    }

    @Override
    void excluirVagaCompetencia(Integer idVagaCompetencia) {
        vagaCompetenciaDao.excluirVagaCompetencia(idVagaCompetencia)

    }

    @Override
    List<CompetenciaDTO> listarCompetenciasPorVaga(Integer idVaga) {
        List<CompetenciaDTO> vagaCompetenciaList = vagaCompetenciaDao.listarCompetenciasPorVaga(idVaga)
        return vagaCompetenciaList

    }

    @Override
    List<CandidatoQueCurtiuVagaDTO> listarCandidatosQueCurtiramVaga(Integer idVaga) {
        List<CandidatoQueCurtiuVagaDTO> vagaCompetenciaList = curtidaDao.listarCandidatosQueCurtiramVaga(idVaga)
        return vagaCompetenciaList
    }
}
