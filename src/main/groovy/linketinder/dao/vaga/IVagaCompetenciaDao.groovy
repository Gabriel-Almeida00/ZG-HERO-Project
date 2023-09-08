package linketinder.dao.vaga


import linketinder.entity.VagaCompetencia
import linketinder.entity.dto.CompetenciaDTO

import java.sql.SQLException

interface IVagaCompetenciaDao {
    void adicionarVagaCompetencia(VagaCompetencia vagaCompetencia) throws SQLException;
    void atualizarNivelVagaCompetencia(VagaCompetencia vagaCompetencia) throws SQLException;
    void excluirVagaCompetencia(Integer idVagaCompetencia) throws SQLException;
    List<CompetenciaDTO> listarCompetenciasPorVaga(Integer idVaga) throws SQLException;
}