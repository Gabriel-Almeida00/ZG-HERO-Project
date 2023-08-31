package service

import dao.vaga.VagaCompetenciaDao
import entity.Competencias
import entity.Vaga
import entity.VagaCompetencia
import entity.dto.VagaDTO

import java.sql.SQLException

interface IVagaService {
    List<VagaDTO> listarTodasVagas() throws SQLException;
    Vaga buscarVagaPorId(Integer idVaga) throws SQLException;
    void adicionarVaga(Vaga vaga) throws SQLException;
    void atualizarVaga(Vaga vaga) throws SQLException;
    void excluirVaga(Integer idVaga) throws SQLException;

    void adicionarVagaCompetencia(VagaCompetencia vagaCompetencia) throws SQLException;
    void atualizarNivelVagaCompetencia(VagaCompetencia vagaCompetencia) throws SQLException;
    void excluirVagaCompetencia(Integer idVagaCompetencia) throws SQLException;
    List<Competencias> listarCompetenciasPorVaga(Integer idVaga) throws SQLException;
}