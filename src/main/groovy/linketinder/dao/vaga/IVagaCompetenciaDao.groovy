package linketinder.dao.vaga


import linketinder.model.VagaCompetencia
import linketinder.model.dto.CompetenciaDTO

import java.sql.SQLException

interface IVagaCompetenciaDao {
    void adicionarVagaCompetencia(VagaCompetencia vagaCompetencia) throws SQLException;
    void atualizarNivelVagaCompetencia(VagaCompetencia vagaCompetencia) throws SQLException;
    void excluirVagaCompetencia(Integer idVagaCompetencia) throws SQLException;
    List<CompetenciaDTO> listarCompetenciasPorVaga(Integer idVaga) throws SQLException;
    VagaCompetencia buscarCompetenciaDaVagaporId(Integer id)
}