package dao.candidato

import db.IDatabaseConnection
import entity.CandidatoCompetencia
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
        List<Competencias> competenciasList = new ArrayList<>();
        String sql = "SELECT c.nome, cc.nivel FROM candidato_competencia cc JOIN competencias c ON cc.idCompetencia = c.id WHERE cc.idCandidato = ?";

        try (PreparedStatement statement = databaseConnection.prepareStatement(sql)) {
            statement.setInt(1, idCandidato);

            try (ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    String nome = resultSet.getString("nome");
                    String nivel = resultSet.getString("nivel");

                    Competencias competencias = new Competencias(nome, nivel);
                    competenciasList.add(competencias);
                }
            }
        }
        return competenciasList;
    }

    @Override
    public void adicionarCandidatoCompetencia(CandidatoCompetencia candidatoCompetencia) throws SQLException {
        String sql = "INSERT INTO candidato_competencia (idCandidato, idCompetencia, nivel) VALUES (?, ?,?)";
        try (PreparedStatement statement = databaseConnection.prepareStatement(sql)) {
            statement.setInt(1, candidatoCompetencia.getIdCandidato());
            statement.setInt(2, candidatoCompetencia.getIdCompetencia());
            statement.setString(3,candidatoCompetencia.getNivel());

            statement.executeUpdate();
        }
    }

    @Override
    public void atualizarNivelCompetenciaCandidato(CandidatoCompetencia candidatoCompetencia) throws SQLException {
        String updateSql = "UPDATE candidato_competencia SET nivel = ? WHERE idCandidato = ? AND idCompetencia = ?";

        try (Connection connection = databaseConnection.getConnection();
             PreparedStatement updateStatement = connection.prepareStatement(updateSql)) {

            updateStatement.setInt(1, candidatoCompetencia.getIdCandidato());
            updateStatement.setInt(2, candidatoCompetencia.getIdCompetencia());
            updateStatement.setString(3, candidatoCompetencia.getNivel());

            updateStatement.executeUpdate();
        }
    }

    @Override
    public void excluirCompetenciaCandidato(Integer id) throws SQLException {
        String deleteSql = "DELETE FROM candidato_competencia WHERE id = ?";

        try (Connection connection = databaseConnection.getConnection();
             PreparedStatement deleteStatement = connection.prepareStatement(deleteSql)) {

            deleteStatement.setInt(0, id);

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
