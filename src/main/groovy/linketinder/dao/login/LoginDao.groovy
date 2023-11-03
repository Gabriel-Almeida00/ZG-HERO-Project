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
    boolean loginCandidato(LoginDTO loginDTO) {
        String sql = "SELECT email, senha FROM candidatos WHERE email=? AND senha=?"

        try (Connection connection = databaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, loginDTO.getEmail())
            statement.setString(2, loginDTO.getSenha())

            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    return true
                } else {
                    throw new LoginException("Email e/ou senha inválidos")
                }
            }
        } catch (SQLException e) {
            throw new DataBaseException("Erro ao acessar o banco de dados: " + e.getMessage())
        }
    }

    @Override
    boolean loginEmpresa(LoginDTO loginDTO) {
        String sql = "SELECT email, senha FROM empresas WHERE email=? AND senha=?"

        try (Connection connection = databaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, loginDTO.getEmail())
            statement.setString(2, loginDTO.getSenha())

            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    return true
                } else {
                    throw new LoginException("Email e/ou senha inválidos")
                }
            }
        } catch (SQLException e) {
            throw new DataBaseException("Erro ao acessar o banco de dados: " + e.getMessage())
        }
    }
}
