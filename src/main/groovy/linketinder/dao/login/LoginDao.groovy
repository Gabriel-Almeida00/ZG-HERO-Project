package linketinder.dao.login

import linketinder.db.IDatabaseConnection
import linketinder.exception.DataBaseException
import linketinder.exception.LoginException
import linketinder.model.dto.LoginDTO

import java.sql.Connection
import java.sql.PreparedStatement
import java.sql.ResultSet
import java.sql.SQLException

class LoginDao implements ILoginDao{
    private IDatabaseConnection databaseConnection

    LoginDao(IDatabaseConnection databaseConnection) {
        this.databaseConnection = databaseConnection
    }

    @Override
    Integer loginCandidato(LoginDTO loginDTO) {
        String sql = "SELECT id FROM candidatos WHERE email=? AND senha=?"

        try (Connection connection = databaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, loginDTO.getEmail())
            statement.setString(2, loginDTO.getSenha())

            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    return resultSet.getInt("id")
                } else {
                    throw new LoginException("Email e/ou senha inválidos")
                }
            }
        } catch (SQLException e) {
            throw new DataBaseException("Erro ao acessar o banco de dados: " + e.getMessage())
        }
    }

    @Override
    Integer loginEmpresa(LoginDTO loginDTO) {
        String sql = "SELECT id FROM empresas WHERE email=? AND senha=?"

        try (Connection connection = databaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, loginDTO.getEmail())
            statement.setString(2, loginDTO.getSenha())

            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    return resultSet.getInt("id")
                } else {
                    throw new LoginException("Email e/ou senha inválidos")
                }
            }
        } catch (SQLException e) {
            throw new DataBaseException("Erro ao acessar o banco de dados: " + e.getMessage())
        }
    }
}
