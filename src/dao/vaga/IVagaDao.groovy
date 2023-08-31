package dao.vaga

import entity.Vaga
import entity.dto.VagaDTO

import java.sql.SQLException

interface IVagaDao {
    List<VagaDTO> listarTodasVagas() throws SQLException;
    Vaga buscarVagaPorId(Integer idVaga) throws SQLException;
    void adicionarVaga(Vaga vaga) throws SQLException;
    void atualizarVaga(Vaga vaga) throws SQLException;
    void excluirVaga(Integer idVaga) throws SQLException;
}