package linketinder.dao.candidato

import linketinder.Exception.DataBaseException
import linketinder.Exception.ExperienciaNotFoundException
import linketinder.db.IDatabaseConnection
import linketinder.entity.Experiencia

import java.sql.Connection
import java.sql.PreparedStatement
import java.sql.ResultSet
import java.sql.SQLException

class ExperienciaDao implements IExperienciaDao {

    private IDatabaseConnection databaseConnection
    private ICandidatoDao candidatoDao

    ExperienciaDao(IDatabaseConnection databaseConnection, ICandidatoDao candidatoDao) {
        this.databaseConnection = databaseConnection
        this.candidatoDao = candidatoDao
    }

    @Override
    void adicionarExperiencia(Experiencia experiencia) throws SQLException {
        String sql = "INSERT INTO experiencias (idCandidato, cargo, empresa, idNivelExperiencia) VALUES (?, ?, ?, ?)"
        try (PreparedStatement statement = databaseConnection.prepareStatement(sql)) {
            statement.setInt(1, experiencia.getIdCandidato())
            statement.setString(2, experiencia.getCargo())
            statement.setString(3, experiencia.getEmpresa())
            statement.setInt(4, experiencia.getNivel())

            statement.executeUpdate()
        } catch (SQLException e) {
            throw new DataBaseException("Erro ao acessar o banco de dados: " + e.getMessage())
        }
    }

    @Override
    void atualizarExperiencia(Experiencia experiencia) throws SQLException {
        buscarExperienciaPorId(experiencia.getId())

        String sql = "UPDATE experiencias SET cargo=?, empresa=?, idNivelExperiencia=? WHERE id=?"
        try (PreparedStatement statement = databaseConnection.prepareStatement(sql)) {
            statement.setString(1, experiencia.getCargo())
            statement.setString(2, experiencia.getEmpresa())
            statement.setInt(3, experiencia.getNivel())
            statement.setInt(4, experiencia.getId())

            statement.executeUpdate()
        } catch (SQLException e) {
            throw new DataBaseException("Erro ao acessar o banco de dados: " + e.getMessage())
        }
    }

    @Override
    List<Experiencia> listarExperienciasPorCandidato(Integer idCandidato) throws SQLException {
        candidatoDao.obterCandidatoPorId(idCandidato)

        String sql = "SELECT id, cargo, empresa, idNivelExperiencia FROM experiencias WHERE idCandidato=?"
        try (Connection connection = databaseConnection.getConnection()
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, idCandidato)

            try (ResultSet resultSet = statement.executeQuery()) {
                return retornarExperienciasResultSet(resultSet, idCandidato)
            }
        }
    }

    private List<Experiencia> retornarExperienciasResultSet(ResultSet resultSet, Integer idCandidato) throws SQLException {
        List<Experiencia> experienciasList = new ArrayList<>()

        try {
            while (resultSet.next()) {
                String cargo = resultSet.getString("cargo")
                String empresa = resultSet.getString("empresa")
                Integer nivel = resultSet.getInt("idNivelExperiencia")
                Integer id = resultSet.getInt("id")

                Experiencia experiencia = new Experiencia(idCandidato, cargo, empresa, nivel)
                experiencia.setId(id)

                experienciasList.add(experiencia)
            }
        } catch (SQLException e) {
            throw new DataBaseException("Erro ao extrair experiências do banco de dados: " + e.getMessage())
        }
        return experienciasList
    }

    @Override
    void excluirExperiencia(Integer idExperiencia) throws SQLException {
        buscarExperienciaPorId(idExperiencia)

        String sql = "DELETE FROM experiencias WHERE id=?"
        try (PreparedStatement statement = databaseConnection.prepareStatement(sql)) {
            statement.setInt(1, idExperiencia)
            statement.executeUpdate()
        }
        catch (SQLException e) {
            throw new DataBaseException("Erro ao acessar o banco de dados: " + e.getMessage())
        }
    }

    @Override
    Experiencia buscarExperienciaPorId(Integer idExperiencia) throws SQLException {
        String sql = "SELECT idCandidato, cargo, empresa, idNivelExperiencia FROM experiencias WHERE id = ?"

        try (Connection connection = databaseConnection.getConnection()
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, idExperiencia)

            try (ResultSet resultSet = statement.executeQuery()) {
                return retornarIdExperienciaResultSet(resultSet)
            }
        } catch (SQLException e) {
            throw new DataBaseException("Erro ao acessar o banco de dados: " + e.getMessage())
        }
    }

    private Experiencia retornarIdExperienciaResultSet(ResultSet resultSet) throws SQLException {
        if (resultSet.next()) {
            Integer idCandidato = resultSet.getInt("idCandidato")
            String cargo = resultSet.getString("cargo")
            String empresa = resultSet.getString("empresa")
            Integer nivel = resultSet.getInt("idNivelExperiencia")

            return new Experiencia(idCandidato, cargo, empresa, nivel)
        } else {
            throw new ExperienciaNotFoundException("Experiencia não encontrada")
        }
    }
}
