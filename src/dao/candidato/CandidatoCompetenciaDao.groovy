package dao.candidato

import db.IDatabaseConnection
import entity.Competencias

import java.sql.Connection
import java.sql.PreparedStatement
import java.sql.ResultSet
import java.sql.SQLException

class CandidatoCompetenciaDao implements ICandidatoCompetenciaDao{

    private final IDatabaseConnection databaseConnection;

    CandidatoCompetenciaDao(IDatabaseConnection databaseConnection) {
        this.databaseConnection = databaseConnection;
    }

    @Override
    public List<Competencias> listarCompetenciasPorCandidato(Integer idCandidato) throws SQLException {
        List<Competencias> competencias = new ArrayList<>();
        String sql = "SELECT c.id, c.nome, c.nivel FROM candidato_competencia cc " +
                "JOIN competencias c ON cc.idCompetencia = c.id " +
                "WHERE cc.idCandidato = ?";
        try (PreparedStatement statement = databaseConnection.prepareStatement(sql)) {
            statement.setInt(1, idCandidato);
            try (ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    Competencias competencia = new Competencias(
                            resultSet.getString("nome"),
                            resultSet.getString("nivel")
                    );
                    competencia.setId(resultSet.getInt("id"))
                    competencias.add(competencia);
                }
            }
        }
        return competencias;
    }

    @Override
    public void adicionarCandidatoCompetencia(Integer idCandidato, Integer idCompetencia) throws SQLException {
        String sql = "INSERT INTO candidato_competencia (idCandidato, idCompetencia) VALUES (?, ?)";
        try (PreparedStatement statement = databaseConnection.prepareStatement(sql)) {
            statement.setInt(1, idCandidato);
            statement.setInt(2, idCompetencia);

            statement.executeUpdate();
        }
    }

    @Override
    public void atualizarNivelCompetenciaCandidato(Integer idCandidato, Integer idCompetencia, String novoNivel) throws SQLException {
        String updateSql = "UPDATE candidato_competencia SET nivel = ? WHERE idCandidato = ? AND idCompetencia = ?";

        try (Connection connection = databaseConnection.getConnection();
             PreparedStatement updateStatement = connection.prepareStatement(updateSql)) {

            updateStatement.setString(1, novoNivel);
            updateStatement.setInt(2, idCandidato);
            updateStatement.setInt(3, idCompetencia);

            updateStatement.executeUpdate();
        }
    }

    @Override
    public void excluirCompetenciaCandidato(Integer idCandidato, Integer idCompetencia) throws SQLException {
        String deleteSql = "DELETE FROM candidato_competencia WHERE idCandidato = ? AND idCompetencia = ?";

        try (Connection connection = databaseConnection.getConnection();
             PreparedStatement deleteStatement = connection.prepareStatement(deleteSql)) {

            deleteStatement.setInt(1, idCandidato);
            deleteStatement.setInt(2, idCompetencia);

            deleteStatement.executeUpdate();
        }
    }

    @Override
    public Competencias buscarCompetenciaPorId(Integer idCompetencia) throws SQLException {
        String sql = "SELECT id, nome, nivel FROM competencias WHERE id = ?";

        try (PreparedStatement statement = databaseConnection.prepareStatement(sql)) {
            statement.setInt(1, idCompetencia);

            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    String nome = resultSet.getString("nome");
                    String nivel = resultSet.getString("nivel");
                    Integer id = resultSet.getInt("id")

                    Competencias competencias = new Competencias( nome, nivel);
                    competencias.setId(id);
                    return competencias
                }
            }
        }
        return null;
    }
}
