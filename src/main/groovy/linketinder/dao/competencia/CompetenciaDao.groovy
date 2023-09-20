package linketinder.dao.competencia


import linketinder.db.IDatabaseConnection
import linketinder.entity.Competencia

import java.sql.Connection
import java.sql.PreparedStatement
import java.sql.ResultSet
import java.sql.SQLException

class CompetenciaDao implements ICompetenciaDao {

    private final IDatabaseConnection databaseConnection

    CompetenciaDao(IDatabaseConnection databaseConnection) {
        this.databaseConnection = databaseConnection
    }

    void adicionarCompetencia(Competencia competencia) throws SQLException {
        String sql = "INSERT INTO competencias (nome) VALUES (?)"
        try (Connection connection = databaseConnection.getConnection()
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setString(1, competencia.getNome())


            statement.executeUpdate()
        }
    }

    Competencia buscarCompetenciaPorId(Integer id) throws SQLException {
        String sql = "SELECT * FROM competencias WHERE id = ?"
        try (Connection connection = databaseConnection.getConnection()
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setInt(1, id)

            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    String nome = resultSet.getString("nome")

                    Competencia competencias = new Competencia(nome)
                    competencias.setId(id)
                    return competencias
                }
            }
        }
        return null
    }

    List<Competencia> listarTodasCompetencias() throws SQLException {
        List<Competencia> competencias = new ArrayList<>()
        String sql = "SELECT * FROM competencias"

        try (Connection connection = databaseConnection.getConnection()
             PreparedStatement statement = connection.prepareStatement(sql)
             ResultSet resultSet = statement.executeQuery()) {

            while (resultSet.next()) {
                Integer id = resultSet.getInt("id")
                String nome = resultSet.getString("nome")

                Competencia competencia = new Competencia(nome)
                competencia.setId(id)
                competencias.add(competencia)
            }
        }
        return competencias
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
