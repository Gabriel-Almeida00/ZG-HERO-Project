package linketinder.service


import linketinder.Exception.CompetenciaNotFoundException
import linketinder.Exception.DataBaseException
import linketinder.dao.competencia.ICompetenciaDao
import linketinder.entity.Competencia

import java.sql.SQLException

class CompetenciaService implements ICompetenciaService {
    private ICompetenciaDao competenciaDao

    CompetenciaService(ICompetenciaDao competenciaDao) {
        this.competenciaDao = competenciaDao
    }

    @Override
    List<Competencia> listarCompetencias() {
        try {
            return competenciaDao.listarTodasCompetencias()
        } catch (SQLException e) {
            throw new DataBaseException("Erro ao acessar o banco de dados: " + e.getMessage())
        }
    }

    @Override
    Competencia buscarCompetenciaPorId(Integer idCompetencia) {
        try {
            return competenciaDao.buscarCompetenciaPorId(idCompetencia)
        } catch (SQLException e) {
            throw new DataBaseException("Erro ao acessar o banco de dados: " + e.getMessage())
        }
    }

    @Override
    void adicionarCompetencia(Competencia competencia) {
        try {
            competenciaDao.adicionarCompetencia(competencia)
        } catch (SQLException e) {
            throw new DataBaseException("Erro ao acessar o banco de dados: " + e.getMessage())
        }
    }

    @Override
    void atualizarCompetencia(Competencia competencia) {
        try {
            Competencia existingCompetencia = competenciaDao.buscarCompetenciaPorId(competencia.getId())
            if (existingCompetencia == null) {
                throw new CompetenciaNotFoundException("Competência não encontrada com ID: " + competencia.getId())
            }
            competenciaDao.atualizarCompetencia(competencia)
        } catch (SQLException e) {
            throw new DataBaseException("Erro ao acessar o banco de dados: " + e.getMessage())
        }
    }

    @Override
    void excluirCompetencia(Integer idCompetencia) {
        try {
            Competencia existingCompetencia = competenciaDao.buscarCompetenciaPorId(idCompetencia)
            if (existingCompetencia == null) {
                throw new CompetenciaNotFoundException("Competência não encontrada com ID: " + idCompetencia)
            }
            competenciaDao.excluirCompetencia(idCompetencia)
        } catch (SQLException e) {
            throw new DataBaseException("Erro ao acessar o banco de dados: " + e.getMessage())
        }
    }
}
