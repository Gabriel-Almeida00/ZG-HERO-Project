package linketinder.dao.candidato

import linketinder.Exception.DataBaseException
import linketinder.Exception.FormacaoNotFoundException
import linketinder.config.Config
import linketinder.db.DatabaseConnection
import linketinder.db.IDatabaseConnection
import linketinder.entity.Formacao

import java.sql.Connection
import java.sql.PreparedStatement
import java.sql.ResultSet
import java.sql.SQLException

class FormacaoDao implements IFormacaoDao {

    private IDatabaseConnection databaseConnection
    private ICandidatoDao candidatoDao

    FormacaoDao() {
        Config config = new Config()
        databaseConnection = new DatabaseConnection(config)
        candidatoDao = new CandidatoDao()
    }

    void adicionarFormacao(Formacao formacao) throws SQLException {
        String sql = "INSERT INTO formacoes (idCandidato, instituicao, curso, idNivelFormacao, anoConclusao) VALUES (?, ?, ?, ?, ?)"
        try (PreparedStatement statement = databaseConnection.prepareStatement(sql)) {
            statement.setInt(1, formacao.getIdCandidato())
            statement.setString(2, formacao.getInstituicao())
            statement.setString(3, formacao.getCurso())
            statement.setInt(4, formacao.getNivel())
            statement.setString(5, formacao.getAnoConclusao())

            statement.executeUpdate()
        } catch (SQLException e) {
            throw new DataBaseException("Erro ao acessar o banco de dados: " + e.getMessage())
        }
    }

    void atualizarFormacao(Formacao formacao) throws SQLException {
        buscarFormacaoPorId(formacao.getId())

        String sql = "UPDATE formacoes SET instituicao = ?, curso = ?, idNivelFormacao = ?, anoConclusao = ? WHERE id = ?"
        try (PreparedStatement statement = databaseConnection.prepareStatement(sql)) {
            statement.setString(1, formacao.getInstituicao())
            statement.setString(2, formacao.getCurso())
            statement.setInt(3, formacao.getNivel())
            statement.setString(4, formacao.getAnoConclusao())
            statement.setInt(5, formacao.getId())

            statement.executeUpdate()
        } catch (SQLException e) {
            throw new DataBaseException("Erro ao acessar o banco de dados: " + e.getMessage())
        }
    }

    void excluirFormacao(Integer idFormacao) throws SQLException {
        buscarFormacaoPorId(idFormacao)

        String sql = "DELETE FROM formacoes WHERE id = ?"
        try (PreparedStatement statement = databaseConnection.prepareStatement(sql)) {
            statement.setInt(1, idFormacao)
            statement.executeUpdate()

        } catch (SQLException e) {
            throw new DataBaseException("Erro ao acessar o banco de dados: " + e.getMessage())
        }
    }


    @Override
    List<Formacao> listarFormacoesPorCandidato(Integer idCandidato) throws SQLException {
        candidatoDao.obterCandidatoPorId(idCandidato)

        String sql = "SELECT * FROM formacoes WHERE idCandidato = ?"
        try (Connection connection = databaseConnection.getConnection()
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, idCandidato)

            try (ResultSet resultSet = statement.executeQuery()) {
                return extrairFormacoes(idCandidato, resultSet)
            }
        }
    }

    private List<Formacao> extrairFormacoes(Integer idCandidato, ResultSet resultSet) throws SQLException {
        List<Formacao> formacoesList = new ArrayList<>()

        try {
            while (resultSet.next()) {
                Integer id = resultSet.getInt("id")
                String instituicao = resultSet.getString("instituicao")
                String curso = resultSet.getString("curso")
                Integer nivel = resultSet.getInt("idNivelFormacao")
                String anoConclusao = resultSet.getString("anoConclusao")

                Formacao formacao = new Formacao(idCandidato, instituicao, curso, nivel, anoConclusao)
                formacao.setId(id)
                formacoesList.add(formacao)
            }
        } catch (SQLException e) {
            throw new DataBaseException("Erro ao extrair formação do banco de dados: " + e.getMessage())
        }

        return formacoesList
    }

    @Override
    Formacao buscarFormacaoPorId(Integer idFormacao) throws SQLException {
        String sql = "SELECT id, idCandidato, instituicao, curso, idNivelFormacao, anoConclusao FROM formacoes WHERE id = ?"

        try (Connection connection = databaseConnection.getConnection()
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, idFormacao)

            try (ResultSet resultSet = statement.executeQuery()) {
                return criarFormacaoAPartirDoResultSet(resultSet)
            }
        }
    }

    private Formacao criarFormacaoAPartirDoResultSet(ResultSet resultSet) throws SQLException {
        if (resultSet.next()) {
            Integer id = resultSet.getInt("id")
            Integer idCandidato = resultSet.getInt("idCandidato")
            String instituicao = resultSet.getString("instituicao")
            String curso = resultSet.getString("curso")
            Integer nivel = resultSet.getInt("idNivelFormacao")
            String anoConclusao = resultSet.getString("anoConclusao")

            Formacao formacao = new Formacao(idCandidato, instituicao, curso, nivel, anoConclusao)
            formacao.setId(id)
            return formacao
        } else {
            throw new FormacaoNotFoundException("Formação não encontrada")
        }
    }
}



