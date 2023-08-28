package service

import Interface.ICandidato
import db.IDatabaseConnection
import entity.Candidato

import java.sql.PreparedStatement
import java.sql.ResultSet
import java.sql.SQLException


class CandidatoService implements ICandidato {

    private final IDatabaseConnection databaseConnection;

    CandidatoService(IDatabaseConnection databaseConnection) {
        this.databaseConnection = databaseConnection;
    }


    @Override
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
}