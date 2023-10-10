package linketinder.dao.vaga


import linketinder.model.Vaga
import linketinder.model.dto.VagaDTO

import java.sql.SQLException

interface IVagaDao {
    List<VagaDTO> listarTodasVagas() throws SQLException;
    List<VagaDTO> listarVagasDaEmpresa(int idEmpresa) throws SQLException;

    Integer obterIdEmpresaPorIdVaga(Integer idVaga)

    Vaga buscarVagaPorId(Integer idVaga) throws SQLException;
    void adicionarVaga(Vaga vaga) throws SQLException;
    void atualizarVaga(Vaga vaga) throws SQLException;
    void excluirVaga(Integer idVaga) throws SQLException;
}