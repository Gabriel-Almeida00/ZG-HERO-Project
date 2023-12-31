package linketinder.dao.candidato

import linketinder.db.IDatabaseConnection
import linketinder.exception.CompetenciaNotFoundException
import linketinder.exception.DataBaseException
import linketinder.model.CandidatoCompetencia
import linketinder.model.dto.CandidatoCompetenciaDTO
import linketinder.model.dto.CompetenciaDTO

import java.sql.Connection
import java.sql.PreparedStatement
import java.sql.ResultSet
import java.sql.SQLException

class CandidatoCompetenciaDao implements ICandidatoCompetenciaDao {

    private IDatabaseConnection databaseConnection
    private ICandidatoDao candidatoDao

    CandidatoCompetenciaDao(IDatabaseConnection databaseConnection, ICandidatoDao candidatoDao) {
        this.databaseConnection = databaseConnection
        this.candidatoDao = candidatoDao
    }

    @Override
    List<CompetenciaDTO> listarCompetenciasPorCandidato(Integer idCandidato) throws SQLException {
        candidatoDao.obterCandidatoPorId(idCandidato)

        String sql = listarCompetenciasPorCandidatoQuery(idCandidato)
        List<CompetenciaDTO> competenciasList = retornarCompetenciasResultSet(sql)
        return competenciasList
    }


    private String listarCompetenciasPorCandidatoQuery(Integer idCandidato) {
        return "SELECT cc.id AS id, c.nome AS nome, nc.nivel AS nivel_competencia " +
                "FROM candidato_competencia cc " +
                "JOIN competencias c ON cc.idCompetencia = c.id " +
                "JOIN nivel_competencia nc ON cc.idNivelCompetencia = nc.id " +
                "WHERE cc.idCandidato = " + idCandidato
    }

    private List<CompetenciaDTO> retornarCompetenciasResultSet(String sql) throws SQLException {
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
        } catch (SQLException e) {
            throw new DataBaseException("Erro ao acessar o banco de dados: " + e.getMessage())
        }
        return competenciasList
    }

    @Override
    CandidatoCompetenciaDTO buscarCompetenciaDoCandidatoPorId(Integer id){
        String sql =
                "SELECT * FROM candidato_competencia WHERE id=?"

        try (Connection connection = databaseConnection.getConnection()
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, id)

            try (ResultSet resultSet = statement.executeQuery()) {
                return retornarCompetenciaResultSet(resultSet)
            }
        } catch (SQLException e) {
            throw new DataBaseException("Erro ao acessar o banco de dados: " + e.getMessage())
        }
    }

    private CandidatoCompetenciaDTO retornarCompetenciaResultSet(ResultSet resultSet) {
        if (resultSet.next()) {
            CandidatoCompetenciaDTO competencia = new CandidatoCompetenciaDTO(
                    resultSet.getInt("id"),
                    resultSet.getInt("idCandidato"),
                    resultSet.getInt("idCompetencia"),
                    resultSet.getInt("idNivelCompetencia")
            )

            return competencia
        } else {
            throw new CompetenciaNotFoundException("Competencia não encontrado")
        }
    }



    @Override
    void adicionarCandidatoCompetencia(CandidatoCompetencia candidatoCompetencia) throws SQLException {
        String sql = "INSERT INTO candidato_competencia (idCandidato, idCompetencia, idNivelCompetencia) VALUES (?, ?, ?)"
        try (PreparedStatement statement = databaseConnection.prepareStatement(sql)) {
            statement.setInt(1, candidatoCompetencia.getIdCandidato())
            statement.setInt(2, candidatoCompetencia.getIdCompetencia())
            statement.setInt(3, candidatoCompetencia.getNivel())

            statement.executeUpdate()
        } catch (SQLException e) {
            throw new DataBaseException("Erro ao acessar o banco de dados: " + e.getMessage())
        }
    }


    @Override
    void atualizarNivelCompetenciaCandidato(CandidatoCompetencia candidatoCompetencia) throws SQLException {
        String updateSql = "UPDATE candidato_competencia SET idNivelCompetencia = ? WHERE id = ?"

        try (Connection connection = databaseConnection.getConnection()
             PreparedStatement updateStatement = connection.prepareStatement(updateSql)) {

            updateStatement.setInt(1, candidatoCompetencia.getIdNivelCompetencia())
            updateStatement.setInt(2, candidatoCompetencia.getId())

            updateStatement.executeUpdate()
        } catch (SQLException e) {
            throw new DataBaseException("Erro ao acessar o banco de dados: " + e.getMessage())
        }
    }

    @Override
    void excluirCompetenciaCandidato(Integer id) throws SQLException {
        String deleteSql = "DELETE FROM candidato_competencia WHERE id = ?"

        try (Connection connection = databaseConnection.getConnection()
             PreparedStatement deleteStatement = connection.prepareStatement(deleteSql)) {
            deleteStatement.setInt(1, id)

            deleteStatement.executeUpdate()
        } catch (SQLException e) {
            throw new DataBaseException("Erro ao acessar o banco de dados: " + e.getMessage())
        }
    }
}
