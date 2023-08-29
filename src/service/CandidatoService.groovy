package service

import Interface.ICandidato
import db.IDatabaseConnection
import entity.Candidato
import entity.Experiencia
import entity.Formacao

import java.sql.PreparedStatement
import java.sql.ResultSet
import java.sql.SQLException


class CandidatoService implements ICandidato {

    private final IDatabaseConnection databaseConnection;

    CandidatoService(IDatabaseConnection databaseConnection) {
        this.databaseConnection = databaseConnection;
    }



    List<Candidato> listarCandidatos() throws SQLException {
        List<Candidato> candidatos = new ArrayList<>();
        String sql = "SELECT * FROM candidatos";
        try (PreparedStatement statement = databaseConnection.prepareStatement(sql);
             ResultSet resultSet = statement.executeQuery()) {
            while (resultSet.next()) {
                Candidato candidato = new Candidato(
                        resultSet.getString("nome"),
                        resultSet.getString("sobrenome"),
                        resultSet.getDate("dataNascimento"),
                        resultSet.getString("email"),
                        resultSet.getString("cpf"),
                        resultSet.getString("pais"),
                        resultSet.getString("cep"),
                        resultSet.getString("descricao"),
                        resultSet.getString("senha")
                );
                candidatos.add(candidato);
            }
        }
        return candidatos;
    }

    void cadastrarCandidato(Candidato candidato) throws SQLException {
        String sql = "INSERT INTO candidatos (nome, sobrenome, dataNascimento, email, cpf, pais, cep, descricao, senha) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
        try (PreparedStatement statement = databaseConnection.prepareStatement(sql)) {
            statement.setString(1, candidato.getNome())
            statement.setString(2, candidato.getSobrenome())
            statement.setDate(3, new java.sql.Date(candidato.getDataNascimento().time))
            statement.setString(4, candidato.getEmail())
            statement.setString(5, candidato.getCpf())
            statement.setString(6, candidato.getPais())
            statement.setString(7, candidato.getCep())
            statement.setString(8, candidato.getDescricao())
            statement.setString(9, candidato.getSenha())
            statement.executeUpdate()
        }
    }


    void atualizarCandidato(Candidato candidato) throws SQLException {
        String sql = "UPDATE candidatos SET nome=?, sobrenome=?, dataNascimento=?, email=?, cpf=?, pais=?, cep=?, descricao=?, senha=? WHERE id=?";
        try (PreparedStatement statement = databaseConnection.prepareStatement(sql)) {
            statement.setString(1, candidato.getNome());
            statement.setString(2, candidato.getSobrenome());
            statement.setDate(3, new java.sql.Date(candidato.getDataNascimento().getTime()));
            statement.setString(4, candidato.getEmail());
            statement.setString(5, candidato.getCpf());
            statement.setString(6, candidato.getPais());
            statement.setString(7, candidato.getCep());
            statement.setString(8, candidato.getDescricao());
            statement.setString(9, candidato.getSenha());
            statement.setInt(10, candidato.getId());

            statement.executeUpdate();
        }
    }


    void deletarCandidato(Integer candidatoId) throws SQLException {
        String sql = "DELETE FROM candidatos WHERE id=?";
        try (PreparedStatement statement = databaseConnection.prepareStatement(sql)) {
            statement.setInt(1, candidatoId);
            statement.executeUpdate();
        }
    }


    void adicionarFormacaoCandidato(Integer idCandidato, Formacao formacao) throws SQLException {
        String sql = "INSERT INTO formacoes (idCandidato, instituicao, curso, nivel, AnoConclusao) VALUES (?, ?, ?, ?, ?)";
        try (PreparedStatement statement = databaseConnection.prepareStatement(sql)) {
            statement.setInt(1, idCandidato);
            statement.setString(2, formacao.getInstituicao());
            statement.setString(3, formacao.getCurso());
            statement.setString(4, formacao.getNivel());
            statement.setString(5, formacao.getAnoConclusao());

            statement.executeUpdate();
        }
    }

    void adicionarExperienciaCandidato(Integer idCandidato, Experiencia experiencia) throws SQLException {
        String sql = "INSERT INTO experiencias (idCandidato, cargo, empresa, nivel) VALUES (?, ?, ?, ?)";
        try (PreparedStatement statement = databaseConnection.prepareStatement(sql)) {
            statement.setInt(1, idCandidato);
            statement.setString(2, experiencia.getCargo());
            statement.setString(3, experiencia.getEmpresa());
            statement.setString(4, experiencia.getNivel());

            statement.executeUpdate();
        }
    }

    void adicionarCompetencia(Integer idCandidato, Integer idCompetencia) throws SQLException {
        String sql = "INSERT INTO candidato_competencia (idCandidato, idCompetencia) VALUES (?, ?)";
        try (PreparedStatement statement = databaseConnection.prepareStatement(sql)) {
            statement.setInt(1, idCandidato);
            statement.setInt(2, idCompetencia);

            statement.executeUpdate();
        }
    }


    Candidato obterCandidatoPorId(Integer idCandidato) {
        String sql = "SELECT * FROM candidatos WHERE id = ?";
        try (PreparedStatement statement = databaseConnection.prepareStatement(sql)) {
            statement.setInt(1, idCandidato);
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    Candidato candidato = new Candidato(
                            resultSet.getString("nome"),
                            resultSet.getString("sobrenome"),
                            resultSet.getDate("dataNascimento"),
                            resultSet.getString("email"),
                            resultSet.getString("cpf"),
                            resultSet.getString("pais"),
                            resultSet.getString("cep"),
                            resultSet.getString("descricao"),
                            resultSet.getString("senha")
                    );
                    candidato.setId(resultSet.getInt("id"));
                    return candidato;
                } else {
                    throw new IllegalArgumentException("Candidato não encontrado.");
                }
            }
        } catch (SQLException e) {
            throw new Exception(e)
        }
    }
}