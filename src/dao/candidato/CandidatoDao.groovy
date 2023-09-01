package dao.candidato

import db.IDatabaseConnection
import entity.Candidato
import entity.dto.CandidatoDTO
import entity.dto.CompetenciaDTO
import entity.dto.ExperienciaDTO
import entity.dto.FormacaoDTO

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

        try (Connection connection = databaseConnection.getConnection();
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
                    );
                    candidato.setId(resultSet.getInt("id"));
                }
            }
        }

        return candidato;
    }

    @Override
    List<CandidatoDTO> listarCandidatos() {
        List<CandidatoDTO> candidatosDTO = new ArrayList<>();
        String sql = "SELECT " +
                "    c.id, " +
                "    c.descricao, " +
                "    ARRAY_AGG(DISTINCT comp.nome) AS competencias, " +
                "    ARRAY_AGG(DISTINCT cc.nivel) AS niveis_competencias, " +
                "    ARRAY_AGG(DISTINCT f.curso) AS formacoes, " +
                "    ARRAY_AGG(DISTINCT f.anoConclusao) AS anos_formacoes, " +
                "    ARRAY_AGG(DISTINCT e.cargo) AS cargos_experiencias, " +
                "    ARRAY_AGG(DISTINCT e.nivel) AS niveis_experiencias " +
                "FROM " +
                "    candidatos c " +
                "LEFT JOIN " +
                "    candidato_competencia cc ON c.id = cc.idCandidato " +
                "LEFT JOIN " +
                "    competencias comp ON cc.idCompetencia = comp.id " +
                "LEFT JOIN " +
                "    formacoes f ON c.id = f.idCandidato " +
                "LEFT JOIN " +
                "    experiencias e ON c.id = e.idCandidato " +
                "GROUP BY " +
                "    c.id, c.descricao;"

        try (Connection connection = databaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql);
             ResultSet resultSet = statement.executeQuery()) {

            Map<Integer, CandidatoDTO> candidatosMap = new HashMap<>();

            while (resultSet.next()) {
                int candidatoId = resultSet.getInt("id");
                String descricao = resultSet.getString("descricao");

                CandidatoDTO candidatoDTO = candidatosMap.getOrDefault(candidatoId, new CandidatoDTO(candidatoId, descricao, new ArrayList<>(), new ArrayList<>(), new ArrayList<>()));

                String nivelCompetencia = resultSet.getString("niveis_competencias");
                String nomeCompetencia = resultSet.getString("competencias");
                if (nomeCompetencia != null) {
                    candidatoDTO.getCompetencias().add(new CompetenciaDTO(nomeCompetencia, nivelCompetencia));
                }

                String cursoFormacao = resultSet.getString("formacoes");
                String anoConclusao = resultSet.getString("anos_formacoes");
                if (cursoFormacao != null) {
                    candidatoDTO.getFormacoes().add(new FormacaoDTO(cursoFormacao, anoConclusao));
                } else {
                    candidatoDTO.getFormacoes().add(new FormacaoDTO("", ""));
                }

                String cargoExperiencia = resultSet.getString("cargos_experiencias");
                String nivelExperiencia = resultSet.getString("niveis_experiencias");
                if (cargoExperiencia != null) {
                    candidatoDTO.getExperiencias().add(new ExperienciaDTO(cargoExperiencia, nivelExperiencia));
                } else {
                    candidatoDTO.getExperiencias().add(new ExperienciaDTO("", ""));
                }

                candidatosMap.put(candidatoId, candidatoDTO);
            }

            candidatosDTO.addAll(candidatosMap.values());
        }
        return candidatosDTO;
    }

    @Override
    void adicionarCandidato(Candidato candidato) {
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



    @Override
    void atualizarCandidato(Candidato candidato) {
        String sql = "UPDATE candidatos SET nome=?, sobrenome=?, dataNascimento=?, email=?, cpf=?, pais=?, cep=?, descricao=?, senha=? WHERE id=?";

        try (Connection connection = databaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
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

    @Override
    void deletarCandidato(Integer id) {
        String sql = "DELETE FROM candidatos WHERE id=?";
        try (PreparedStatement statement = databaseConnection.prepareStatement(sql)) {
            statement.setInt(1, id);
            statement.executeUpdate();
        }
    }
}
