package dao.vaga

import entity.Competencias
import entity.VagaCompetencia

import java.sql.SQLException

interface IVagaCompetenciaDao {
    void adicionarVagaCompetencia(VagaCompetencia vagaCompetencia) throws SQLException;
    void atualizarNivelVagaCompetencia(VagaCompetencia vagaCompetencia) throws SQLException;
    void excluirVagaCompetencia(Integer idVagaCompetencia) throws SQLException;
    List<Competencias> listarCompetenciasPorVaga(Integer idVaga) throws SQLException;
}