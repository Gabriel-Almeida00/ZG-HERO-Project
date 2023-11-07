package linketinder.service.vaga


import linketinder.model.Vaga
import linketinder.model.dto.VagaDTO

import java.sql.SQLException

interface IVagaService {
    List<VagaDTO> listarTodasVagas() throws SQLException;
    List<Vaga> listarVagasDaEmpresa(Integer idEmpresa) throws SQLException;
    Vaga buscarVagaPorId(Integer idVaga) throws SQLException;
    Integer obterIdEmpresaPorIdVaga(Integer idVaga)

    void adicionarVaga(Vaga vaga) throws SQLException;
    void atualizarVaga(Vaga vaga) throws SQLException;
    void excluirVaga(Integer idVaga) throws SQLException;
}