package linketinder.service


import linketinder.Exception.DataBaseException
import linketinder.dao.match.IMatchDao
import linketinder.entity.dto.MatchCandidatoDTO
import linketinder.entity.dto.MatchEmpresaDTO

import java.sql.SQLException

class MatchService implements IMatchService {

    private IMatchDao matchDao

    MatchService(IMatchDao matchDao) {
        this.matchDao = matchDao
    }

    @Override
    List<MatchCandidatoDTO> encontrarMatchesPelaEmpresa(Integer idEmpresa) {
       try{
           matchDao.encontrarMatchesPelaEmpresa(idEmpresa)
       } catch (SQLException e){
           throw new DataBaseException("Erro ao acessar o banco de dados: " + e.getMessage())
       }
    }

    @Override
    List<MatchEmpresaDTO> encontrarMatchesPeloCandidato(Integer idCandidato) {
        try{
            matchDao.encontrarMatchesPeloCandidato(idCandidato)
        } catch (SQLException e){
            throw new DataBaseException("Erro ao acessar o banco de dados: " + e.getMessage())
        }
    }
}

