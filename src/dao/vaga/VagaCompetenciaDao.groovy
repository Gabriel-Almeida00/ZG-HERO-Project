package dao.vaga

import db.IDatabaseConnection
import entity.Competencias
import entity.VagaCompetencia

import java.sql.PreparedStatement
import java.sql.ResultSet
import java.sql.SQLException

class VagaCompetenciaDao implements IVagaCompetenciaDao{

    private IDatabaseConnection databaseConnection

     VagaCompetenciaDao(IDatabaseConnection databaseConnection) {
        this.databaseConnection = databaseConnection
    }

    @Override
     void adicionarVagaCompetencia(VagaCompetencia vagaCompetencia) throws SQLException {
        String sql = "INSERT INTO vaga_competencia (idVaga, idCompetencia, nivel) VALUES (?, ?, ?)"

        try (PreparedStatement statement = databaseConnection.prepareStatement(sql)) {
            statement.setInt(1, vagaCompetencia.getIdVaga())
            statement.setInt(2, vagaCompetencia.getIdCompetencia())
            statement.setString(3, vagaCompetencia.getNivel())

            statement.executeUpdate()
        }
    }

    @Override
     void atualizarNivelVagaCompetencia(VagaCompetencia vagaCompetencia) throws SQLException {
        String sql = "UPDATE vaga_competencia SET nivel = ? WHERE idVaga = ? AND idCompetencia = ?"

        try (PreparedStatement statement = databaseConnection.prepareStatement(sql)) {
            statement.setString(1, vagaCompetencia.getNivel());
            statement.setInt(2, vagaCompetencia.getIdVaga());
            statement.setInt(3, vagaCompetencia.getIdCompetencia());

            statement.executeUpdate()
        }
    }

    @Override
    public void excluirVagaCompetencia(Integer idVagaCompetencia) throws SQLException {
        String sql = "DELETE FROM vaga_competencia WHERE id = ?"

        try (PreparedStatement statement = databaseConnection.prepareStatement(sql)) {
            statement.setInt(1, idVagaCompetencia)
            statement.executeUpdate()
        }
    }

    @Override
     List<Competencias> listarCompetenciasPorVaga(Integer idVaga) throws SQLException {
        List<Competencias> vagaCompetencias = new ArrayList<>()
            String sql = "SELECT " +
                    "    c.nome AS nomeCompetencia," +
                    "    vc.nivel AS nivel" +
                    "FROM" +
                    "    vaga_competencia vc " +
                    "INNER JOIN " +
                    "    competencias c ON vc.idCompetencia = c.id " +
                    "WHERE " +
                    "    vc.idVaga = ?;"

        try (PreparedStatement statement = databaseConnection.prepareStatement(sql)) {
            statement.setInt(1, idVaga)

            try (ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    String nomeCompetencia = resultSet.getString("nomeCompetencia")
                    String nivel = resultSet.getString("nivel")

                    Competencias competencia = new Competencias(nomeCompetencia, nivel)
                    vagaCompetencias.add(competencia)
                }
            }
        }

        return vagaCompetencias
    }
}
