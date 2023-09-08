package linketinder.dao.candidato


import linketinder.db.IDatabaseConnection
import linketinder.entity.Experiencia

import java.sql.PreparedStatement
import java.sql.ResultSet
import java.sql.SQLException

class ExperienciaDao implements IExperienciaDao{

    private final IDatabaseConnection databaseConnection;

    ExperienciaDao(IDatabaseConnection databaseConnection) {
        this.databaseConnection = databaseConnection;
    }

    @Override
    public void adicionarExperiencia(Experiencia experiencia) throws SQLException {
        String sql = "INSERT INTO experiencias (idCandidato, cargo, empresa, nivel) VALUES (?, ?, ?, ?)";
        try (PreparedStatement statement = databaseConnection.prepareStatement(sql)) {
            statement.setInt(1, experiencia.getIdCandidato());
            statement.setString(2, experiencia.getCargo());
            statement.setString(3, experiencia.getEmpresa());
            statement.setString(4, experiencia.getNivel());

            statement.executeUpdate();
        }
    }

    @Override
    public void atualizarExperiencia(Experiencia experiencia) throws SQLException {
        String sql = "UPDATE experiencias SET cargo=?, empresa=?, nivel=? WHERE id=?";
        try (PreparedStatement statement = databaseConnection.prepareStatement(sql)) {
            statement.setString(1, experiencia.getCargo());
            statement.setString(2, experiencia.getEmpresa());
            statement.setString(3, experiencia.getNivel());
            statement.setInt(4, experiencia.getId());

            statement.executeUpdate();
        }
    }

    @Override
    public List<Experiencia> listarExperienciasPorCandidato(Integer idCandidato) throws SQLException {
        List<Experiencia> experiencias = new ArrayList<>();
        String sql = "SELECT id, cargo, empresa, nivel FROM experiencias WHERE idCandidato=?";
        try (PreparedStatement statement = databaseConnection.prepareStatement(sql)) {
            statement.setInt(1, idCandidato);
            try (ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    String cargo = resultSet.getString("cargo");
                    String empresa = resultSet.getString("empresa");
                    String nivel = resultSet.getString("nivel");
                    Integer id = resultSet.getInt("id")

                    Experiencia experiencia = new Experiencia( idCandidato, cargo, empresa, nivel)
                    experiencia.setId(id)
                    experiencias.add(experiencia);
                }
            }
        }
        return experiencias;
    }

    @Override
    public void excluirExperiencia(Integer idExperiencia) throws SQLException {
        String sql = "DELETE FROM experiencias WHERE id=?";
        try (PreparedStatement statement = databaseConnection.prepareStatement(sql)) {
            statement.setInt(1, idExperiencia);
            statement.executeUpdate();
        }
    }

    @Override
    public Experiencia buscarExperienciaPorId(Integer idExperiencia) throws SQLException {
        String sql = "SELECT id, idCandidato, cargo, empresa, nivel FROM experiencias WHERE id = ?";

        try (PreparedStatement statement = databaseConnection.prepareStatement(sql)) {
            statement.setInt(1, idExperiencia);

            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    Integer idCandidato = resultSet.getInt("idCandidato");
                    String cargo = resultSet.getString("cargo");
                    String empresa = resultSet.getString("empresa");
                    String nivel = resultSet.getString("nivel");

                    return new Experiencia( idCandidato, cargo, empresa, nivel);
                }
            }
        }

        return null;
    }
}
