package dao.competencia

import db.IDatabaseConnection
import entity.Competencias

import java.sql.Connection
import java.sql.PreparedStatement
import java.sql.ResultSet
import java.sql.SQLException

class CompetenciaDao implements  ICompetenciaDao{

    private final IDatabaseConnection databaseConnection;

    CompetenciaDao(IDatabaseConnection databaseConnection) {
        this.databaseConnection = databaseConnection;
    }

    public void adicionarCompetencia(Competencias competencia) throws SQLException {
        String sql = "INSERT INTO competencias (nome, nivel) VALUES (?, ?)";
        try (Connection connection = databaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setString(1, competencia.getNome());
            statement.setString(2, competencia.getNivel());

            statement.executeUpdate();
        }
    }

    public Competencias buscarCompetenciaPorId(Integer id) throws SQLException {
        String sql = "SELECT * FROM competencias WHERE id = ?";
        try (Connection connection = databaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setInt(1, id);

            try (  ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    String nome = resultSet.getString("nome");
                    String nivel = resultSet.getString("nivel");

                    Competencias competencias = new Competencias( nome, nivel);
                    competencias.setId(id)
                    return competencias
                }
            }
        }
        return null;
    }

    public List<Competencias> listarTodasCompetencias() throws SQLException {
        List<Competencias> competencias = new ArrayList<>();
        String sql = "SELECT * FROM competencias";

        try (Connection connection = databaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql);
             ResultSet resultSet = statement.executeQuery()) {

            while (resultSet.next()) {
                Integer id = resultSet.getInt("id");
                String nome = resultSet.getString("nome");
                String nivel = resultSet.getString("nivel");

                Competencias competencia = new Competencias(nome, nivel);
                competencia.setId(id)
                competencias.add(competencia);
            }
        }
        return competencias;
    }

    public void atualizarCompetencia(Competencias competencia) throws SQLException {
        String sql = "UPDATE competencias SET nome = ?, nivel = ? WHERE id = ?";

        try (Connection connection = databaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setString(1, competencia.getNome());
            statement.setString(2, competencia.getNivel());
            statement.setInt(3, competencia.getId());

            statement.executeUpdate();
        }
    }

    public void excluirCompetencia(Integer idCompetencia) throws SQLException {
        String sql = "DELETE FROM competencias WHERE id = ?";

        try (Connection connection = databaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setInt(1, idCompetencia);

            statement.executeUpdate();
        }
    }


}
