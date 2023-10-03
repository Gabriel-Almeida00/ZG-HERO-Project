package linketinder.service.vaga


import linketinder.entity.Vaga
import linketinder.entity.VagaCompetencia
import linketinder.entity.dto.CandidatoQueCurtiuVagaDTO
import linketinder.entity.dto.CompetenciaDTO
import linketinder.entity.dto.VagaDTO

import java.sql.SQLException

interface IVagaService {
    List<VagaDTO> listarTodasVagas() throws SQLException;
    List<VagaDTO> listarVagasDaEmpresa(Integer idEmpresa) throws SQLException;
    Vaga buscarVagaPorId(Integer idVaga) throws SQLException;
    Integer obterIdEmpresaPorIdVaga(Integer idVaga)

    void adicionarVaga(Vaga vaga) throws SQLException;
    void atualizarVaga(Vaga vaga) throws SQLException;
    void excluirVaga(Integer idVaga) throws SQLException;
}