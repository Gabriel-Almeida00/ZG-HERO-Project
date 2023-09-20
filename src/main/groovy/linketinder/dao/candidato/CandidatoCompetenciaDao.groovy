package linketinder.dao.candidato

import linketinder.db.IDatabaseConnection
import linketinder.entity.CandidatoCompetencia
import linketinder.entity.dto.CompetenciaDTO

import java.sql.Connection
import java.sql.PreparedStatement
import java.sql.ResultSet
import java.sql.SQLException

class CandidatoCompetenciaDao implements ICandidatoCompetenciaDao {

    private final IDatabaseConnection databaseConnection

    CandidatoCompetenciaDao(IDatabaseConnection databaseConnection) {
        this.databaseConnection = databaseConnection
    }

    @Override
    List<CompetenciaDTO> listarCompetenciasPorCandidato(Integer idCandidato) throws SQLException {
        List<CompetenciaDTO> competenciasList = new ArrayList<>()
        String sql = "SELECT cc.id AS id, c.nome AS nome, nc.nivel AS nivel_competencia " +
                "FROM candidato_competencia cc " +
                "JOIN competencias c ON cc.idCompetencia = c.id " +
                "JOIN nivel_competencia nc ON cc.idNivelCompetencia = nc.id " +
                "WHERE cc.idCandidato =?"

        try (PreparedStatement statement = databaseConnection.prepareStatement(sql)) {
            statement.setInt(1, idCandidato)

            try (ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    Integer id = resultSet.getInt("id")
                    String nome = resultSet.getString("nome")
                    String nivel = resultSet.getString("nivel_competencia")

                    CompetenciaDTO competencias = new CompetenciaDTO(id,nome, nivel)
                    competenciasList.add(competencias)
                }
            }
        }
        return competenciasList
    }

    @Override
    void adicionarCandidatoCompetencia(CandidatoCompetencia candidatoCompetencia) throws SQLException {
        String sql = "INSERT INTO candidato_competencia (idCandidato, idCompetencia, idNivelCompetencia) VALUES (?, ?, ?)"
        try (PreparedStatement statement = databaseConnection.prepareStatement(sql)) {
            statement.setInt(1, candidatoCompetencia.getIdCandidato())
            statement.setInt(2, candidatoCompetencia.getIdCompetencia())
            statement.setInt(3, candidatoCompetencia.getNivel())

            statement.executeUpdate()
        }
    }



    @Override
    void atualizarNivelCompetenciaCandidato(CandidatoCompetencia candidatoCompetencia) throws SQLException {
        String updateSql = "UPDATE candidato_competencia SET idNivelCompetencia = ? WHERE id = ?"

        try (Connection connection = databaseConnection.getConnection()
             PreparedStatement updateStatement = connection.prepareStatement(updateSql)) {

            updateStatement.setInt(1, candidatoCompetencia.getNivel())
            updateStatement.setInt(2, candidatoCompetencia.getId())

            updateStatement.executeUpdate()
        }
    }

    @Override
    void excluirCompetenciaCandidato(Integer id) throws SQLException {
        String deleteSql = "DELETE FROM candidato_competencia WHERE id = ?"

        try (Connection connection = databaseConnection.getConnection()
             PreparedStatement deleteStatement = connection.prepareStatement(deleteSql)) {
            deleteStatement.setInt(1, id)
            deleteStatement.executeUpdate()
        }
    }

}
