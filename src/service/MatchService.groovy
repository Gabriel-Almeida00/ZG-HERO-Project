package service

import Exception.DataBaseException
import dao.match.IMatchDao
import entity.dto.CandidatoCurtidoDTO
import entity.dto.VagaCurtidaDTO

import java.sql.SQLException

class MatchService implements IMatchService {

    private IMatchDao matchDao

    MatchService(IMatchDao matchDao) {
        this.matchDao = matchDao
    }

    @Override
    List<VagaCurtidaDTO> encontrarMatchesPelaVaga(Integer idCandidato, Integer idVaga) {
       try{
           matchDao.encontrarMatchesPelaVaga(idCandidato, idVaga)
       } catch (SQLException e){
           throw new DataBaseException("Erro ao acessar o banco de dados: " + e.getMessage(), e)
       }
    }

    @Override
    List<CandidatoCurtidoDTO> encontrarMatchesPeloCandidato(Integer idCandidato) {
        try{
            matchDao.encontrarMatchesPeloCandidato(idCandidato)
        } catch (SQLException e){
            throw new DataBaseException("Erro ao acessar o banco de dados: " + e.getMessage(), e)
        }
    }
}

