package linketinder.service


import linketinder.Exception.CompetenciaNotFoundException
import linketinder.Exception.DataBaseException
import linketinder.dao.competencia.ICompetenciaDao
import linketinder.entity.Competencias

import java.sql.SQLException

class CompetenciaService implements ICompetenciaService {
    private ICompetenciaDao competenciaDao;

    CompetenciaService(ICompetenciaDao competenciaDao) {
        this.competenciaDao = competenciaDao;
    }

    @Override
    public List<Competencias> listarCompetencias() {
        try {
            return competenciaDao.listarTodasCompetencias();
        } catch (SQLException e) {
            throw new DataBaseException("Erro ao acessar o banco de dados: " + e.getMessage(), e);
        }
    }

    @Override
    public Competencias buscarCompetenciaPorId(Integer idCompetencia) {
        try {
            return competenciaDao.buscarCompetenciaPorId(idCompetencia);
        } catch (SQLException e) {
            throw new DataBaseException("Erro ao acessar o banco de dados: " + e.getMessage(), e);
        }
    }

    @Override
    public void adicionarCompetencia(Competencias competencia) {
        try {
            competenciaDao.adicionarCompetencia(competencia);
        } catch (SQLException e) {
            throw new DataBaseException("Erro ao acessar o banco de dados: " + e.getMessage(), e);
        }
    }

    @Override
    public void atualizarCompetencia(Competencias competencia) {
        try {
            Competencias existingCompetencia = competenciaDao.buscarCompetenciaPorId(competencia.getId());
            if (existingCompetencia == null) {
                throw new CompetenciaNotFoundException("Competência não encontrada com ID: " + competencia.getId());
            }
            competenciaDao.atualizarCompetencia(competencia);
        } catch (SQLException e) {
            throw new DataBaseException("Erro ao acessar o banco de dados: " + e.getMessage(), e);
        }
    }

    @Override
    public void excluirCompetencia(Integer idCompetencia) {
        try {
            Competencias existingCompetencia = competenciaDao.buscarCompetenciaPorId(idCompetencia);
            if (existingCompetencia == null) {
                throw new CompetenciaNotFoundException("Competência não encontrada com ID: " + idCompetencia);
            }
            competenciaDao.excluirCompetencia(idCompetencia);
        } catch (SQLException e) {
            throw new DataBaseException("Erro ao acessar o banco de dados: " + e.getMessage(), e);
        }
    }
}
