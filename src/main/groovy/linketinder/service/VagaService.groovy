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
        try {
            return vagaDao.listarTodasVagas()
        } catch (SQLException e) {
            throw new DataBaseException("Erro ao acessar o banco de dados: " + e.getMessage())
        }
    }

    @Override
    List<VagaDTO> listarVagasDaEmpresa(int idEmpresa) throws SQLException {
        try {
            return vagaDao.listarVagasDaEmpresa(idEmpresa)
        } catch (SQLException e) {
            throw new DataBaseException("Erro ao acessar o banco de dados: " + e.getMessage())
        }
    }

    @Override
    Integer obterIdEmpresaPorIdVaga(Integer idVaga) {
        try{
            Vaga id = vagaDao.buscarVagaPorId(idVaga)
            if(id == null){
                throw new VagaNotFoundException("Vaga não encontrada com ID :" + idVaga)
            }
            return vagaDao.obterIdEmpresaPorIdVaga(idVaga)
        }catch (SQLException e) {
            throw new DataBaseException("Erro ao acessar o banco de dados: " + e.getMessage())
        }
    }

    @Override
    Vaga buscarVagaPorId(Integer idVaga) {
        try {
            return vagaDao.buscarVagaPorId(idVaga)
        } catch (SQLException e) {
            throw new DataBaseException("Erro ao acessar o banco de dados: " + e.getMessage())
        }
    }

    @Override
    void adicionarVaga(Vaga vaga) {
        try {
            vagaDao.adicionarVaga(vaga)
        } catch (SQLException e) {
            throw new DataBaseException("Erro ao acessar o banco de dados: " + e.getMessage())
        }
    }

    @Override
    void atualizarVaga(Vaga vaga) {
        try {
            Vaga idVaga = vagaDao.buscarVagaPorId(vaga.getId())
            if (idVaga == null)
                throw new VagaNotFoundException("Vaga não encontrada com ID :" + vaga.getId())
            vagaDao.atualizarVaga(vaga)
        } catch (SQLException e) {
            throw new DataBaseException("Erro ao acessar o banco de dados: " + e.getMessage())
        }
    }

    @Override
    void excluirVaga(Integer idVaga) {
        try {
            Vaga vaga = vagaDao.buscarVagaPorId(idVaga)
            if (vaga == null) {
                throw new VagaNotFoundException("Vaga não encontada com ID : " + idVaga)
            }
            vagaDao.excluirVaga(idVaga)
        } catch (SQLException e) {
            throw new DataBaseException("Erro ao acessar o banco de dados: " + e.getMessage())
        }
    }


    @Override
    void adicionarVagaCompetencia(VagaCompetencia vagaCompetencia) {
        try {
            vagaCompetenciaDao.adicionarVagaCompetencia(vagaCompetencia)
        } catch (SQLException e) {
            throw new DataBaseException("Erro ao acessar o banco de dados: " + e.getMessage())
        }
    }

    @Override
    void atualizarNivelVagaCompetencia(VagaCompetencia vagaCompetencia) {
        try {
            vagaCompetenciaDao.atualizarNivelVagaCompetencia(vagaCompetencia)
        } catch (SQLException e) {
            throw new DataBaseException("Erro ao acessar o banco de dados: " + e.getMessage())
        }
    }

    @Override
    void excluirVagaCompetencia(Integer idVagaCompetencia) {
        try {
            vagaCompetenciaDao.excluirVagaCompetencia(idVagaCompetencia)
        } catch (SQLException e) {
            throw new DataBaseException("Erro ao acessar o banco de dados: " + e.getMessage())
        }
    }

    @Override
    List<CompetenciaDTO> listarCompetenciasPorVaga(Integer idVaga) {
        try {
            Vaga vaga = vagaDao.buscarVagaPorId(idVaga)
            if (vaga == null) {
                throw new VagaNotFoundException("Vaga não encontrada com ID: " + idVaga)
            }
            List<CompetenciaDTO> vagaCompetenciaList = vagaCompetenciaDao.listarCompetenciasPorVaga(idVaga)
            return vagaCompetenciaList
        } catch (SQLException e) {
            throw new DataBaseException("Erro ao acessar o banco de dados: " + e.getMessage())
        }
    }

    @Override
    List<CandidatoQueCurtiuVagaDTO> listarCandidatosQueCurtiramVaga(Integer idVaga) {
        try {
            Vaga vaga = vagaDao.buscarVagaPorId(idVaga)
            if (vaga == null) {
                throw new VagaNotFoundException("Vaga não encontrada com ID: " + idVaga)
            }
            List<CandidatoQueCurtiuVagaDTO> vagaCompetenciaList = curtidaDao.listarCandidatosQueCurtiramVaga(idVaga)
            return vagaCompetenciaList
        } catch (SQLException e) {
            throw new DataBaseException("Erro ao acessar o banco de dados: " + e.getMessage())
        }
    }
}
