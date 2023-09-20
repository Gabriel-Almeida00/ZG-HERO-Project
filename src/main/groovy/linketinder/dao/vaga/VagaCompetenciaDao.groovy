package linketinder.dao.vaga


import linketinder.db.IDatabaseConnection
import linketinder.entity.VagaCompetencia
import linketinder.entity.dto.CompetenciaDTO

import java.sql.PreparedStatement
import java.sql.ResultSet
import java.sql.SQLException

class VagaCompetenciaDao implements IVagaCompetenciaDao{

    private IDatabaseConnection databaseConnection

     VagaCompetenciaDao(IDatabaseConnection databaseConnection) {
        this.databaseConnection = databaseConnection
    }

    @Override
     void adicionarVagaCompetencia(VagaCompetencia vagaCompetencia) throws SQLException {
        String sql = "INSERT INTO vaga_competencia (idVaga, idCompetencia, idNivelCompetencia) VALUES (?, ?, ?)"

        try (PreparedStatement statement = databaseConnection.prepareStatement(sql)) {
            statement.setInt(1, vagaCompetencia.getIdVaga())
            statement.setInt(2, vagaCompetencia.getIdCompetencia())
            statement.setInt(3, vagaCompetencia.getNivel())

            statement.executeUpdate()
        }
    }

    @Override
     void atualizarNivelVagaCompetencia(VagaCompetencia vagaCompetencia) throws SQLException {
        String sql = "UPDATE vaga_competencia SET idNivelCompetencia = ? WHERE idVaga = ? AND idCompetencia = ?"

        try (PreparedStatement statement = databaseConnection.prepareStatement(sql)) {
            statement.setInt(1, vagaCompetencia.getNivel())
            statement.setInt(2, vagaCompetencia.getIdVaga())
            statement.setInt(3, vagaCompetencia.getIdCompetencia())

            statement.executeUpdate()
        }
    }

    @Override
     void excluirVagaCompetencia(Integer idVagaCompetencia) throws SQLException {
        String sql = "DELETE FROM vaga_competencia WHERE id = ?"

        try (PreparedStatement statement = databaseConnection.prepareStatement(sql)) {
            statement.setInt(1, idVagaCompetencia)
            statement.executeUpdate()
        }
    }

    @Override
     List<CompetenciaDTO> listarCompetenciasPorVaga(Integer idVaga) throws SQLException {
        List<CompetenciaDTO> vagaCompetencias = new ArrayList<>()
            String sql = "SELECT vc.id, c.nome AS nomeCompetencia, vc.idNivelCompetencia AS nivel " +
                    "FROM vaga_competencia vc " +
                    "INNER JOIN competencias c ON vc.idCompetencia = c.id " +
                    "WHERE vc.idVaga = ?;"

        try (PreparedStatement statement = databaseConnection.prepareStatement(sql)) {
            statement.setInt(1, idVaga)

            try (ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    Integer id = resultSet.getInt("id")
                    String nomeCompetencia = resultSet.getString("nomeCompetencia")
                    Integer nivel = resultSet.getInt("nivel")



                    CompetenciaDTO competencia = new CompetenciaDTO(nomeCompetencia, nivel)
                    competencia.setId(id)
                    vagaCompetencias.add(competencia)
                }
            }
        }

        return vagaCompetencias
    }
}
