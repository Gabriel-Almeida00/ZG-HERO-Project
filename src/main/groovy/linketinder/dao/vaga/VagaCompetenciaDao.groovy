package linketinder.dao.vaga

import linketinder.exception.DataBaseException
import linketinder.db.IDatabaseConnection
import linketinder.model.VagaCompetencia
import linketinder.model.dto.CompetenciaDTO

import java.sql.Connection
import java.sql.PreparedStatement
import java.sql.ResultSet
import java.sql.SQLException

class VagaCompetenciaDao implements IVagaCompetenciaDao {

    private IDatabaseConnection databaseConnection
    private IVagaDao vagaDao

    VagaCompetenciaDao(IDatabaseConnection databaseConnection, IVagaDao vagaDao) {
        this.databaseConnection = databaseConnection
        this.vagaDao = vagaDao
    }

    @Override
    void adicionarVagaCompetencia(VagaCompetencia vagaCompetencia) throws SQLException {
        String sql = "INSERT INTO vaga_competencia (idVaga, idCompetencia, idNivelCompetencia) VALUES (?, ?, ?)"

        try (PreparedStatement statement = databaseConnection.prepareStatement(sql)) {
            statement.setInt(1, vagaCompetencia.getIdVaga())
            statement.setInt(2, vagaCompetencia.getIdCompetencia())
            statement.setInt(3, vagaCompetencia.getNivel())

            statement.executeUpdate()
        } catch (SQLException e) {
            throw new DataBaseException("Erro ao acessar o banco de dados: " + e.getMessage())
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
        } catch (SQLException e) {
            throw new DataBaseException("Erro ao acessar o banco de dados: " + e.getMessage())
        }
    }

    @Override
    void excluirVagaCompetencia(Integer idVagaCompetencia) throws SQLException {
        String sql = "DELETE FROM vaga_competencia WHERE id = ?"

        try (PreparedStatement statement = databaseConnection.prepareStatement(sql)) {
            statement.setInt(1, idVagaCompetencia)
            statement.executeUpdate()
        } catch (SQLException e) {
            throw new DataBaseException("Erro ao acessar o banco de dados: " + e.getMessage())
        }
    }

    @Override
    List<CompetenciaDTO> listarCompetenciasPorVaga(Integer idVaga) throws SQLException {
        vagaDao.buscarVagaPorId(idVaga)

        String sql =
                "SELECT vc.id, c.nome AS nomeCompetencia, nc.nivel AS nivel " +
                        "FROM vaga_competencia vc " +
                        "INNER JOIN competencias c ON vc.idCompetencia = c.id " +
                        "INNER JOIN nivel_competencia nc ON vc.idNivelCompetencia = nc.id " +
                        "WHERE vc.idVaga =?;"

        try (Connection connection = databaseConnection.getConnection()
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, idVaga)

            try (ResultSet resultSet = statement.executeQuery()) {
                return retornarCompetenciaResultset(resultSet)
            }
        } catch (SQLException e) {
            throw new DataBaseException("Erro ao acessar o banco de dados: " + e.getMessage())
        }
    }

    private List<CompetenciaDTO> retornarCompetenciaResultset(ResultSet resultSet) throws SQLException {
        List<CompetenciaDTO> vagaCompetenciasList = new ArrayList<>()

        while (resultSet.next()) {
            Integer id = resultSet.getInt("id")
            String nomeCompetencia = resultSet.getString("nomeCompetencia")
            String nivel = resultSet.getString("nivel")

            CompetenciaDTO competencia = new CompetenciaDTO(id, nomeCompetencia, nivel)
            vagaCompetenciasList.add(competencia)
        }
        return vagaCompetenciasList
    }
}
