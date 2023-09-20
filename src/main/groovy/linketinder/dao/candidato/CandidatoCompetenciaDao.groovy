package linketinder.dao.candidato

import linketinder.config.Config
import linketinder.db.DatabaseConnection
import linketinder.db.IDatabaseConnection
import linketinder.entity.CandidatoCompetencia
import linketinder.entity.dto.CompetenciaDTO

import java.sql.Connection
import java.sql.PreparedStatement
import java.sql.ResultSet
import java.sql.SQLException

class CandidatoCompetenciaDao implements ICandidatoCompetenciaDao {

    private  IDatabaseConnection databaseConnection

    CandidatoCompetenciaDao() {
        Config config = new Config()
        databaseConnection = new DatabaseConnection(config)
    }

    @Override
    List<CompetenciaDTO> listarCompetenciasPorCandidato(Integer idCandidato) throws SQLException {
        String sql = listarCompetenciasCandidatoQuery(idCandidato)
        List<CompetenciaDTO> competenciasList = mapearQueryParaCompetenciaDTO(sql)
        return competenciasList
    }

    private String listarCompetenciasCandidatoQuery(Integer idCandidato) {
        return "SELECT cc.id AS id, c.nome AS nome, nc.nivel AS nivel_competencia " +
                "FROM candidato_competencia cc " +
                "JOIN competencias c ON cc.idCompetencia = c.id " +
                "JOIN nivel_competencia nc ON cc.idNivelCompetencia = nc.id " +
                "WHERE cc.idCandidato = " + idCandidato
    }

    private List<CompetenciaDTO> mapearQueryParaCompetenciaDTO(String sql) throws SQLException {
        List<CompetenciaDTO> competenciasList = new ArrayList<>()

        try (PreparedStatement statement = databaseConnection.prepareStatement(sql)
             ResultSet resultSet = statement.executeQuery()) {
            while (resultSet.next()) {
                Integer id = resultSet.getInt("id")
                String nome = resultSet.getString("nome")
                String nivel = resultSet.getString("nivel_competencia")

                CompetenciaDTO competencias = new CompetenciaDTO(id, nome, nivel)
                competenciasList.add(competencias)
            }
        }

        return competenciasList
    }

    @Override
    void adicionarCandidatoCompetencia(CandidatoCompetencia candidatoCompetencia) throws SQLException {
        String sql = adicionarCandidatoCompetenciaQuery()
        executarQueryAdicionarCandidatoCompetencia(sql, candidatoCompetencia)
    }

    private String adicionarCandidatoCompetenciaQuery() {
        return "INSERT INTO candidato_competencia (idCandidato, idCompetencia, idNivelCompetencia) VALUES (?, ?, ?)"
    }

    private void executarQueryAdicionarCandidatoCompetencia(String sql, CandidatoCompetencia candidatoCompetencia) throws SQLException {
        try (PreparedStatement statement = databaseConnection.prepareStatement(sql)) {
            statement.setInt(1, candidatoCompetencia.getIdCandidato())
            statement.setInt(2, candidatoCompetencia.getIdCompetencia())
            statement.setInt(3, candidatoCompetencia.getNivel())

            statement.executeUpdate()
        }
    }


    @Override
    void atualizarNivelCompetenciaCandidato(CandidatoCompetencia candidatoCompetencia) throws SQLException {
        String updateSql = queryAtualizaNivelCompetencia()
        executarQueryAtualizaCompetencia(updateSql, candidatoCompetencia)
    }

    private String queryAtualizaNivelCompetencia() {
        return "UPDATE candidato_competencia SET idNivelCompetencia = ? WHERE id = ?"
    }

    private void executarQueryAtualizaCompetencia(String sql, CandidatoCompetencia candidatoCompetencia) throws SQLException {
        try (Connection connection = databaseConnection.getConnection()
             PreparedStatement updateStatement = connection.prepareStatement(sql)) {

            updateStatement.setInt(1, candidatoCompetencia.getNivel())
            updateStatement.setInt(2, candidatoCompetencia.getId())

            updateStatement.executeUpdate()
        }
    }

    @Override
    void excluirCompetenciaCandidato(Integer id) throws SQLException {
        String deleteSql = excluirCompetenciaCandidatoQuery()
        executarQueryExcluirCompetenciaCandidato(deleteSql, id)
    }

    private String excluirCompetenciaCandidatoQuery() {
        return "DELETE FROM candidato_competencia WHERE id = ?"
    }

    private void executarQueryExcluirCompetenciaCandidato(String sql, Integer id) throws SQLException {
        try (Connection connection = databaseConnection.getConnection()
             PreparedStatement deleteStatement = connection.prepareStatement(sql)) {

            deleteStatement.setInt(1, id)
            deleteStatement.executeUpdate()
        }
    }

}
