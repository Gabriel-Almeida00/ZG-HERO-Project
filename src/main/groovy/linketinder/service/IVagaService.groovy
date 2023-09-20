package linketinder.service


import linketinder.entity.Vaga
import linketinder.entity.VagaCompetencia
import linketinder.entity.dto.CandidatoQueCurtiuVagaDTO
import linketinder.entity.dto.CompetenciaDTO
import linketinder.entity.dto.VagaDTO

import java.sql.SQLException

interface IVagaService {
    List<VagaDTO> listarTodasVagas() throws SQLException;
    List<VagaDTO> listarVagasDaEmpresa(int idEmpresa) throws SQLException;

    Integer obterIdEmpresaPorIdVaga(Integer idVaga)

    Vaga buscarVagaPorId(Integer idVaga) throws SQLException;
    void adicionarVaga(Vaga vaga) throws SQLException;
    void atualizarVaga(Vaga vaga) throws SQLException;
    void excluirVaga(Integer idVaga) throws SQLException;

    void adicionarVagaCompetencia(VagaCompetencia vagaCompetencia) throws SQLException;
    void atualizarNivelVagaCompetencia(VagaCompetencia vagaCompetencia) throws SQLException;
    void excluirVagaCompetencia(Integer idVagaCompetencia) throws SQLException;
    List<CompetenciaDTO> listarCompetenciasPorVaga(Integer idVaga) throws SQLException;

    List<CandidatoQueCurtiuVagaDTO> listarCandidatosQueCurtiramVaga(Integer idVaga)
}