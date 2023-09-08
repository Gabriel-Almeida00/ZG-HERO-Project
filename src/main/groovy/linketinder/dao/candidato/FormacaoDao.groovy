package linketinder.dao.candidato


import linketinder.db.IDatabaseConnection
import linketinder.entity.Formacao

import java.sql.PreparedStatement
import java.sql.ResultSet
import java.sql.SQLException

class FormacaoDao implements IFormacaoDao {

    private final IDatabaseConnection databaseConnection;

    FormacaoDao(IDatabaseConnection databaseConnection) {
        this.databaseConnection = databaseConnection;
    }

    void adicionarFormacao(Formacao formacao) throws SQLException {
        String sql = "INSERT INTO formacoes (idCandidato, instituicao, curso, nivel, anoConclusao) VALUES (?, ?, ?, ?, ?)";
        try (PreparedStatement statement = databaseConnection.prepareStatement(sql)) {
            statement.setInt(1, formacao.getIdCandidato());
            statement.setString(2, formacao.getInstituicao());
            statement.setString(3, formacao.getCurso());
            statement.setString(4, formacao.getNivel());
            statement.setString(5, formacao.getAnoConclusao());

            statement.executeUpdate();
        }
    }

    void atualizarFormacao(Formacao formacao) throws SQLException {
        String sql = "UPDATE formacoes SET instituicao = ?, curso = ?, nivel = ?, anoConclusao = ? WHERE id = ?";
        try (PreparedStatement statement = databaseConnection.prepareStatement(sql)) {
            statement.setString(1, formacao.getInstituicao());
            statement.setString(2, formacao.getCurso());
            statement.setString(3, formacao.getNivel());
            statement.setString(4, formacao.getAnoConclusao());
            statement.setInt(5, formacao.getId());

            statement.executeUpdate();
        }
    }

    void excluirFormacao(Integer idFormacao) throws SQLException {
        String sql = "DELETE FROM formacoes WHERE id = ?";
        try (PreparedStatement statement = databaseConnection.prepareStatement(sql)) {
            statement.setInt(1, idFormacao);
            statement.executeUpdate();
        }
    }

    List<Formacao> listarFormacoesPorCandidato(Integer idCandidato) throws SQLException {
        List<Formacao> formacoes = new ArrayList<>();
        String sql = "SELECT * FROM formacoes WHERE idCandidato = ?";
        try (PreparedStatement statement = databaseConnection.prepareStatement(sql)) {
            statement.setInt(1, idCandidato);
            try (ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    Integer id = resultSet.getInt("id");
                    String instituicao = resultSet.getString("instituicao");
                    String curso = resultSet.getString("curso");
                    String nivel = resultSet.getString("nivel");
                    String anoConclusao = resultSet.getString("anoConclusao");

                    Formacao formacao = new Formacao(idCandidato, instituicao, curso, nivel, anoConclusao);
                    formacao.setId(id);
                    formacoes.add(formacao);
                }
            }
        }
        return formacoes;
    }

    @Override
    public Formacao buscarFormacaoPorId(Integer idFormacao) throws SQLException {
        String sql = "SELECT id, idCandidato, instituicao, curso, nivel, anoConclusao FROM formacoes WHERE id = ?";

        try (PreparedStatement statement = databaseConnection.prepareStatement(sql)) {
            statement.setInt(1, idFormacao);

            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    Integer id = resultSet.getInt("id");
                    Integer idCandidato = resultSet.getInt("idCandidato");
                    String instituicao = resultSet.getString("instituicao");
                    String curso = resultSet.getString("curso");
                    String nivel = resultSet.getString("nivel");
                    String anoConclusao = resultSet.getString("anoConclusao");

                    Formacao formacao = new Formacao(idCandidato, instituicao, curso, nivel, anoConclusao);
                    formacao.setId(id)
                    return formacao
                }
            }
        }
        return null;
    }
}



