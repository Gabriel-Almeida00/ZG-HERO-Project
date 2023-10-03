package linketinder.service.curtida

import linketinder.dao.curtida.CurtidaDao
import linketinder.dao.curtida.ICurtidaDao
import linketinder.dao.vaga.IVagaDao
import linketinder.entity.dto.CandidatoQueCurtiuVagaDTO
import linketinder.entity.dto.EmpresaDTO

class CurtidaService implements ICurtidaService{
    private final ICurtidaDao curtidaDao
    private final IVagaDao vagaDao

    CurtidaService(ICurtidaDao curtidaDao, IVagaDao vagaDao) {
        this.curtidaDao = curtidaDao
        this.vagaDao = vagaDao
    }

    @Override
    void curtirVaga(Integer idCandidato, Integer idVaga) {
        Integer idEmpresa = vagaDao.obterIdEmpresaPorIdVaga(idVaga)
        Integer empresaQueCurtiu = curtidaDao.verificaCurtidaDaEmpresa(idEmpresa, idCandidato)

        if (empresaQueCurtiu == CurtidaDao.CURTIDA_NAO_ENCONTRADA) {
            curtidaDao.curtirVaga(idCandidato, idVaga)
        } else {
            curtidaDao.AtualizarCurtidaComIdVaga(idVaga, idEmpresa, idCandidato)
        }
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

    @Override
    List<EmpresaDTO> listarEmpresasQueCurtiramCandidato(Integer idCandidato) {
        return curtidaDao.listarEmpresasQueCurtiramCandidato(idCandidato)
    }

    @Override
    List<CandidatoQueCurtiuVagaDTO> listarCandidatosQueCurtiramVaga(Integer idVaga) {
        List<CandidatoQueCurtiuVagaDTO> vagaCompetenciaList = curtidaDao.listarCandidatosQueCurtiramVaga(idVaga)
        return vagaCompetenciaList
    }
}
