package service

import Exception.DataBaseException
import Exception.VagaNotFoundException
import dao.vaga.IVagaCompetenciaDao
import dao.vaga.IVagaDao
import dao.vaga.VagaDao
import entity.Competencias
import entity.Vaga
import entity.VagaCompetencia
import entity.dto.VagaDTO

import java.sql.SQLException

class VagaService implements IVagaService{

    private IVagaDao vagaDao
    private IVagaCompetenciaDao vagaCompetenciaDao

    VagaService(IVagaDao vagaDao, IVagaCompetenciaDao vagaCompetenciaDao){
        this.vagaDao = vagaDao
        this.vagaCompetenciaDao = vagaCompetenciaDao
    }

    @Override
    public List<VagaDTO> listarTodasVagas() {
        try {
            return vagaDao.listarTodasVagas();
        } catch (SQLException e) {
            throw new DataBaseException("Erro ao acessar o banco de dados: " + e.getMessage(), e);
        }
    }

    @Override
    public Vaga buscarVagaPorId(Integer idVaga) {
        try {
            return vagaDao.buscarVagaPorId(idVaga);
        } catch (SQLException e) {
            throw new DataBaseException("Erro ao acessar o banco de dados: " + e.getMessage(), e);
        }
    }

    @Override
    public void adicionarVaga(Vaga vaga) {
        try {
            vagaDao.adicionarVaga(vaga);
        } catch (SQLException e) {
            throw new DataBaseException("Erro ao acessar o banco de dados: " + e.getMessage(), e);
        }
    }

    @Override
    public void atualizarVaga(Vaga vaga) {
        try {
            Vaga idVaga = vagaDao.buscarVagaPorId(vaga.getId())
            if(idVaga == null)
                throw new VagaNotFoundException("Vaga não encontrada com ID :" + vaga.getId())
            vagaDao.atualizarVaga(vaga);
        } catch (SQLException e) {
            throw new DataBaseException("Erro ao acessar o banco de dados: " + e.getMessage(), e);
        }
    }

    @Override
    public void excluirVaga(Integer idVaga) {
        try {
            Vaga vaga = vagaDao.buscarVagaPorId(idVaga)
            if(vaga == null){
                throw new VagaNotFoundException("Vaga não encontada com ID : " + idVaga)
            }
            vagaDao.excluirVaga(idVaga);
        } catch (SQLException e) {
            throw new DataBaseException("Erro ao acessar o banco de dados: " + e.getMessage(), e);
        }
    }

    @Override
    public void adicionarVagaCompetencia(VagaCompetencia vagaCompetencia) {
        try {
            vagaCompetenciaDao.adicionarVagaCompetencia(vagaCompetencia);
        } catch (SQLException e) {
            throw new DataBaseException("Erro ao acessar o banco de dados: " + e.getMessage(), e);
        }
    }

    @Override
    public void atualizarNivelVagaCompetencia(VagaCompetencia vagaCompetencia) {
        try {
            vagaCompetenciaDao.atualizarNivelVagaCompetencia(vagaCompetencia);
        } catch (SQLException e) {
            throw new DataBaseException("Erro ao acessar o banco de dados: " + e.getMessage(), e);
        }
    }

    @Override
    public void excluirVagaCompetencia(Integer idVagaCompetencia) {
        try {
            vagaCompetenciaDao.excluirVagaCompetencia(idVagaCompetencia);
        } catch (SQLException e) {
            throw new DataBaseException("Erro ao acessar o banco de dados: " + e.getMessage(), e);
        }
    }

    @Override
    public List<Competencias> listarCompetenciasPorVaga(Integer idVaga) {
        try {
            Vaga vaga = vagaDao.buscarVagaPorId(idVaga)
            if(vaga == null){
                throw new VagaNotFoundException("Vaga não encontrada com ID: " + idVaga)
            }
            List<Competencias> vagaCompetenciaList = vagaCompetenciaDao.listarCompetenciasPorVaga(idVaga);
            return vagaCompetenciaList
        } catch (SQLException e) {
            throw new DataBaseException("Erro ao acessar o banco de dados: " + e.getMessage(), e);
        }
    }
}
