package linketinder.dao.candidato


import linketinder.db.IDatabaseConnection
import linketinder.entity.Candidato
import linketinder.entity.dto.CandidatoDTO
import linketinder.entity.dto.CompetenciaDTO
import linketinder.entity.dto.ExperienciaDTO
import linketinder.entity.dto.FormacaoDTO

import java.lang.reflect.Array
import java.sql.Connection
import java.sql.PreparedStatement
import java.sql.ResultSet

class CandidatoDao implements ICandidatoDao {

    private final IDatabaseConnection databaseConnection

    CandidatoDao(IDatabaseConnection databaseConnection) {
        this.databaseConnection = databaseConnection
    }

    @Override
    Candidato obterCandidatoPorId(Integer id) {
        Candidato candidato = null
        String sql = "SELECT * FROM candidatos WHERE id = ?"

        try (Connection connection = databaseConnection.getConnection()
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, id)

            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    candidato = new Candidato(
                            resultSet.getString("nome"),
                            resultSet.getString("sobrenome"),
                            resultSet.getDate("dataNascimento"),
                            resultSet.getString("email"),
                            resultSet.getString("cpf"),
                            resultSet.getString("pais"),
                            resultSet.getString("cep"),
                            resultSet.getString("descricao"),
                            resultSet.getString("senha")
                    )
                    candidato.setId(resultSet.getInt("id"))
                }
            }
        }

        return candidato
    }

    @Override
    List<CandidatoDTO> listarCandidatos() {
        List<CandidatoDTO> candidatosDTO = new ArrayList<>();

        String sql = "SELECT " +
                "    c.id, " +
                "    c.nome, " +
                "    c.descricao, " +
                "    ARRAY_AGG(DISTINCT comp.nome) AS competencias " +
                "FROM " +
                "    candidatos c " +
                "LEFT JOIN " +
                "    candidato_competencia cc ON c.id = cc.idCandidato " +
                "LEFT JOIN " +
                "    competencias comp ON cc.idCompetencia = comp.id " +
                "GROUP BY c.id, c.nome, c.descricao;"

        try (Connection connection = databaseConnection.getConnection()
             PreparedStatement statement = connection.prepareStatement(sql)
             ResultSet resultSet = statement.executeQuery()) {

            while (resultSet.next()) {
                int candidatoId = resultSet.getInt("id")
                String nome = resultSet.getString("nome")
                String descricao = resultSet.getString("descricao")
                String nomesCompetencia = resultSet.getString("competencias");

                List<String> nomesCompetenciaList = Arrays.asList(nomesCompetencia.split(", "));

                CandidatoDTO candidatoDTO = new CandidatoDTO(candidatoId, nome, descricao, nomesCompetenciaList)

                candidatosDTO.add(candidatoDTO)
            }
        }
        return candidatosDTO
    }


    @Override
    void adicionarCandidato(Candidato candidato) {
        String sql = "INSERT INTO candidatos (nome, sobrenome, dataNascimento, email, cpf, pais, cep, descricao, senha) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)"
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



    @Override
    void atualizarCandidato(Candidato candidato) {
        String sql = "UPDATE candidatos SET nome=?, sobrenome=?, dataNascimento=?, email=?, cpf=?, pais=?, cep=?, descricao=?, senha=? WHERE id=?"

        try (Connection connection = databaseConnection.getConnection()
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, candidato.getNome())
            statement.setString(2, candidato.getSobrenome())
            statement.setDate(3, new java.sql.Date(candidato.getDataNascimento().getTime()))
            statement.setString(4, candidato.getEmail())
            statement.setString(5, candidato.getCpf())
            statement.setString(6, candidato.getPais())
            statement.setString(7, candidato.getCep())
            statement.setString(8, candidato.getDescricao())
            statement.setString(9, candidato.getSenha())
            statement.setInt(10, candidato.getId())

            statement.executeUpdate()
        }
    }

    @Override
    void deletarCandidato(Integer id) {
        String sql = "DELETE FROM candidatos WHERE id=?"
        try (PreparedStatement statement = databaseConnection.prepareStatement(sql)) {
            statement.setInt(1, id)
            statement.executeUpdate()
        }
    }
}
