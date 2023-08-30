package dao.vaga

import db.IDatabaseConnection
import entity.VagaCompetencia

import java.sql.PreparedStatement
import java.sql.ResultSet
import java.sql.SQLException

class VagaCompetenciaDao implements IVagaCompetenciaDao{

    private IDatabaseConnection databaseConnection;

    public VagaCompetenciaDao(IDatabaseConnection databaseConnection) {
        this.databaseConnection = databaseConnection;
    }

    @Override
    public void adicionarVagaCompetencia(VagaCompetencia vagaCompetencia) throws SQLException {
        String sql = "INSERT INTO vaga_competencia (idVaga, idCompetencia, nivel) VALUES (?, ?, ?)";

        try (PreparedStatement statement = databaseConnection.prepareStatement(sql)) {
            statement.setInt(1, vagaCompetencia.getIdVaga());
            statement.setInt(2, vagaCompetencia.getIdCompetencia());
            statement.setString(3, vagaCompetencia.getNivel());

            statement.executeUpdate();
        }
    }

    @Override
    public void atualizarNivelVagaCompetencia(VagaCompetencia vagaCompetencia) throws SQLException {
        String sql = "UPDATE vaga_competencia SET nivel = ? WHERE idVaga = ? AND idCompetencia = ?";

        try (PreparedStatement statement = databaseConnection.prepareStatement(sql)) {
            statement.setString(1, vagaCompetencia.getNivel());
            statement.setInt(2, vagaCompetencia.getIdVaga());
            statement.setInt(3, vagaCompetencia.getIdCompetencia());

            statement.executeUpdate();
        }
    }

    @Override
    public void excluirVagaCompetencia(Integer idVagaCompetencia) throws SQLException {
        String sql = "DELETE FROM vaga_competencia WHERE id = ?";

        try (PreparedStatement statement = databaseConnection.prepareStatement(sql)) {
            statement.setInt(1, idVagaCompetencia);
            statement.executeUpdate();
        }
    }

    @Override
    public List<VagaCompetencia> listarCompetenciasPorVaga(Integer idVaga) throws SQLException {
        List<VagaCompetencia> vagaCompetencias = new ArrayList<>();
        String sql = "SELECT id, idCompetencia, nivel FROM vaga_competencia WHERE idVaga = ?";

        try (PreparedStatement statement = databaseConnection.prepareStatement(sql)) {
            statement.setInt(1, idVaga);

            try (ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    Integer id = resultSet.getInt("id");
                    Integer idCompetencia = resultSet.getInt("idCompetencia");
                    String nivel = resultSet.getString("nivel");

                    VagaCompetencia vagaCompetencia = new VagaCompetencia(idVaga, idCompetencia, nivel);
                    vagaCompetencia.setId(id);
                    vagaCompetencias.add(vagaCompetencia);
                }
            }
        }

        return vagaCompetencias;
    }
}
