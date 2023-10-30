package linketinder.service.curtida

import linketinder.dao.curtida.CurtidaDao
import linketinder.dao.curtida.ICurtidaDao
import linketinder.dao.vaga.IVagaDao
import linketinder.model.CandidatoCurtido
import linketinder.model.VagaCurtida
import linketinder.model.dto.CandidatoQueCurtiuVagaDTO
import linketinder.model.dto.EmpresaDTO

class CurtidaService implements ICurtidaService{
    private final ICurtidaDao curtidaDao
    private final IVagaDao vagaDao

    CurtidaService(ICurtidaDao curtidaDao, IVagaDao vagaDao) {
        this.curtidaDao = curtidaDao
        this.vagaDao = vagaDao
    }

    @Override
    void curtirVaga(VagaCurtida vagaCurtida) {
        Integer idEmpresa = vagaDao.obterIdEmpresaPorIdVaga(vagaCurtida.idVaga)
        Integer empresaQueCurtiu = curtidaDao.verificaCurtidaDaEmpresa(idEmpresa, vagaCurtida.idCandidata)

        if (empresaQueCurtiu == CurtidaDao.CURTIDA_NAO_ENCONTRADA) {
            curtidaDao.curtirVaga(vagaCurtida)
        } else {
            curtidaDao.AtualizarCurtidaComIdVaga(vagaCurtida.idVaga, idEmpresa, vagaCurtida.idCandidata)
        }
    }

    @Override
    void curtirCandidato(CandidatoCurtido candidatoCurtido) {
        Integer idVaga = curtidaDao.verificaCurtidaDoCandidato(candidatoCurtido.idCandidato)
        if (idVaga == CurtidaDao.CURTIDA_NAO_ENCONTRADA) {
            curtidaDao.curtirCandidato(candidatoCurtido)
        } else {
            curtidaDao.AtualizarCurtidaComIdEmpresa(idVaga, candidatoCurtido.idEmpresa, candidatoCurtido.idCandidato)
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
