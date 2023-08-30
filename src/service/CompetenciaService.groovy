package service

import Exception.DataBaseException
import dao.competencia.ICompetenciaDao
import db.IDatabaseConnection
import entity.Competencias

import java.sql.SQLException

class CompetenciaService implements ICompetenciaService {
    private ICompetenciaDao competenciaDao;

    CompetenciaService(ICompetenciaDao competenciaDao) {
        this.competenciaDao = competenciaDao;
    }

    @Override
    List<Competencias> listarTodasCompetencias() {
       try{
           competenciaDao.listarTodasCompetencias();
       }
        catch (SQLException e){
            throw new DataBaseException("Erro ao acessar o banco de dados: " + e.getMessage(), e);
        }
    }

    @Override
    Competencias buscarCompetenciaPorId(Integer id) {
        return null
    }

    @Override
    void adicionarCompetencia(Competencias competencia) {

    }

    @Override
    void atualizarCompetencia(Competencias competencia) {

    }

    @Override
    void excluirCompetencia(Integer idCompetencia) {

    }
}
