package linketinder.dao.competencia

import linketinder.config.Config
import linketinder.db.DatabaseConnection
import linketinder.db.IDatabaseConnection
import linketinder.entity.Competencia

import java.sql.Connection
import java.sql.PreparedStatement
import java.sql.ResultSet
import java.sql.SQLException

class CompetenciaDao implements ICompetenciaDao {

    private  IDatabaseConnection databaseConnection

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
        }
    }

    private Competencia criarCompetenciaAPartirDoResultSet(ResultSet resultSet, Integer id) throws SQLException {
        if (resultSet.next()) {
            String nome = resultSet.getString("nome")
            Competencia competencia = new Competencia(nome)
            competencia.setId(id)
            return competencia
        } else {
            return null
        }
    }

    @Override
    List<Competencia> listarTodasCompetencias() throws SQLException {
        String sql = "SELECT id, nome FROM competencias"

        try (Connection connection = databaseConnection.getConnection()
             PreparedStatement statement = connection.prepareStatement(sql)
             ResultSet resultSet = statement.executeQuery()) {

            return criarListaCompetenciasAPartirDoResultSet(resultSet)
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
        String sql = "UPDATE competencias SET nome = ? WHERE id = ?"

        try (Connection connection = databaseConnection.getConnection()
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setString(1, competencia.getNome())
            statement.setInt(2, competencia.getId())

            statement.executeUpdate()
        }
    }

    void excluirCompetencia(Integer idCompetencia) throws SQLException {
        String sql = "DELETE FROM competencias WHERE id = ?"

        try (Connection connection = databaseConnection.getConnection()
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setInt(1, idCompetencia)

            statement.executeUpdate()
        }
    }


}
