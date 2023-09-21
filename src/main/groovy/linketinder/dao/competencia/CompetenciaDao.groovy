package linketinder.dao.competencia

import linketinder.Exception.CompetenciaNotFoundException
import linketinder.Exception.DataBaseException
import linketinder.config.Config
import linketinder.db.DatabaseConnection
import linketinder.db.IDatabaseConnection
import linketinder.entity.Competencia

import java.sql.Connection
import java.sql.PreparedStatement
import java.sql.ResultSet
import java.sql.SQLException

class CompetenciaDao implements ICompetenciaDao {

    private IDatabaseConnection databaseConnection

    CompetenciaDao() {
        Config config = new Config()
        databaseConnection = new DatabaseConnection(config)
    }

    void adicionarCompetencia(Competencia competencia) throws SQLException {
        String sql = "INSERT INTO competencias (nome) VALUES (?)"
        try (Connection connection = databaseConnection.getConnection()
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, competencia.getNome())

            statement.executeUpdate()
        }
        catch (SQLException e) {
            throw new DataBaseException("Erro ao acessar o banco de dados: " + e.getMessage())
        }
    }

    @Override
    Competencia buscarCompetenciaPorId(Integer id) throws SQLException {
        String sql = "SELECT nome FROM competencias WHERE id = ?"

        try (Connection connection = databaseConnection.getConnection()
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, id)

            try (ResultSet resultSet = statement.executeQuery()) {
                return criarCompetenciaAPartirDoResultSet(resultSet, id)
            }
        } catch (SQLException e) {
            throw new DataBaseException("Erro ao acessar o banco de dados: " + e.getMessage())
        }
    }

    private Competencia criarCompetenciaAPartirDoResultSet(ResultSet resultSet, Integer id) throws SQLException {
        if (resultSet.next()) {
            String nome = resultSet.getString("nome")
            Competencia competencia = new Competencia(nome)
            competencia.setId(id)
            return competencia
        } else {
            throw new CompetenciaNotFoundException("Competencia não encontrada com ID " + id)
        }
    }

    @Override
    List<Competencia> listarTodasCompetencias() throws SQLException {
        String sql = "SELECT id, nome FROM competencias"

        try (Connection connection = databaseConnection.getConnection()
             PreparedStatement statement = connection.prepareStatement(sql)
             ResultSet resultSet = statement.executeQuery()) {

            return criarListaCompetenciasAPartirDoResultSet(resultSet)
        } catch (SQLException e) {
            throw new DataBaseException("Erro ao acessar o banco de dados: " + e.getMessage())
        }
    }

    private List<Competencia> criarListaCompetenciasAPartirDoResultSet(ResultSet resultSet) throws SQLException {
        List<Competencia> competenciasList = new ArrayList<>()

        while (resultSet.next()) {
            Integer id = resultSet.getInt("id")
            String nome = resultSet.getString("nome")

            Competencia competencia = new Competencia(nome)
            competencia.setId(id)
            competenciasList.add(competencia)
        }

        return competenciasList
    }

    void atualizarCompetencia(Competencia competencia) throws SQLException {
        buscarCompetenciaPorId(competencia.getId())

        String sql = "UPDATE competencias SET nome = ? WHERE id = ?"
        try (Connection connection = databaseConnection.getConnection()
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setString(1, competencia.getNome())
            statement.setInt(2, competencia.getId())

            statement.executeUpdate()
        } catch (SQLException e) {
            throw new DataBaseException("Erro ao acessar o banco de dados: " + e.getMessage())
        }
    }

    void excluirCompetencia(Integer idCompetencia) throws SQLException {
        buscarCompetenciaPorId(idCompetencia)

        String sql = "DELETE FROM competencias WHERE id = ?"
        try (Connection connection = databaseConnection.getConnection()
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setInt(1, idCompetencia)

            statement.executeUpdate()
        }
        catch (SQLException e) {
            throw new DataBaseException("Erro ao acessar o banco de dados: " + e.getMessage())
        }
    }


}
