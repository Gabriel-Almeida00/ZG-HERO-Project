package service

import dao.vaga.VagaCompetenciaDao
import entity.Vaga
import entity.VagaCompetencia

import java.sql.SQLException

interface IVagaService {
    List<Vaga> listarTodasVagas() throws SQLException;
    Vaga buscarVagaPorId(Integer idVaga) throws SQLException;
    void adicionarVaga(Vaga vaga) throws SQLException;
    void atualizarVaga(Vaga vaga) throws SQLException;
    void excluirVaga(Integer idVaga) throws SQLException;

    void adicionarVagaCompetencia(VagaCompetencia vagaCompetencia) throws SQLException;
    void atualizarNivelVagaCompetencia(VagaCompetencia vagaCompetencia) throws SQLException;
    void excluirVagaCompetencia(Integer idVagaCompetencia) throws SQLException;
    List<VagaCompetencia> listarCompetenciasPorVaga(Integer idVaga) throws SQLException;
}