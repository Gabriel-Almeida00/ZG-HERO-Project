package dao.vaga

import db.IDatabaseConnection
import entity.Vaga
import entity.dto.CompetenciaDTO
import entity.dto.VagaDTO

import java.sql.Connection
import java.sql.PreparedStatement
import java.sql.ResultSet
import java.sql.SQLException

class VagaDao implements IVagaDao{

    private final IDatabaseConnection databaseConnection;

    VagaDao(IDatabaseConnection databaseConnection) {
        this.databaseConnection = databaseConnection;
    }

    @Override
    List<VagaDTO> listarTodasVagas() throws SQLException {
        List<VagaDTO> vagaDTOs = new ArrayList<>();

        String sql = "SELECT" +
                "    v.id AS id_vaga," +
                "    v.nome AS nome_vaga," +
                "    v.descricao," +
                "    v.cidade," +
                "    v.formacaoMinima," +
                "    v.experienciaMinina," +
                "    c.nome AS nome_competencia," +
                "    vc.nivel" +
                " FROM " +
                "    vagas v" +
                " INNER JOIN" +
                "    vaga_competencia vc ON v.id = vc.idVaga" +
                " INNER JOIN" +
                "    competencias c ON vc.idCompetencia = c.id;";

        try (PreparedStatement statement = databaseConnection.prepareStatement(sql);
             ResultSet resultSet = statement.executeQuery()) {

            Map<Integer, VagaDTO> vagaDTOMap = new HashMap<>();

            while (resultSet.next()) {
                Integer idVaga = resultSet.getInt("id_vaga");
                String nomeVaga = resultSet.getString("nome_vaga");

                VagaDTO vagaDTO = vagaDTOMap.get(idVaga);
                if (vagaDTO == null) {
                    vagaDTO = new VagaDTO(
                            idVaga,
                            nomeVaga,
                            resultSet.getString("cidade"),
                            resultSet.getString("descricao"),
                            resultSet.getString("formacaoMinima"),
                            resultSet.getString("experienciaMinina"),
                            new ArrayList<>()
                    );
                    vagaDTOMap.put(idVaga, vagaDTO);
                }

                CompetenciaDTO competenciaDTO = new CompetenciaDTO(
                        resultSet.getString("nome_competencia"),
                        resultSet.getString("nivel")
                );

                vagaDTO.getCompetencias().add(competenciaDTO);
            }

            vagaDTOs.addAll(vagaDTOMap.values());
        }

        return vagaDTOs;
    }

    @Override
    List<VagaDTO> listarVagasDaEmpresa(int idEmpresa) throws SQLException {
        List<VagaDTO> vagaDTOs = new ArrayList<>();

        String sql = "SELECT " +
                "    v.id AS id_vaga," +
                "    v.nome AS nome_vaga," +
                "    v.descricao," +
                "    v.cidade," +
                "    v.formacaoMinima," +
                "    v.experienciaMinina" +
                " FROM " +
                "    vagas v" +
                " JOIN" +
                "    empresas e ON v.idEmpresa = e.id" +
                " WHERE" +
                "    v.idEmpresa = ?"

        try (Connection connection = databaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setInt(1, idEmpresa);

            try (ResultSet resultSet = statement.executeQuery()) {
                Map<Integer, VagaDTO> vagaDTOMap = new HashMap<>();

                while (resultSet.next()) {
                    Integer idVaga = resultSet.getInt("id_vaga");
                    String nomeVaga = resultSet.getString("nome_vaga");

                    VagaDTO vagaDTO = vagaDTOMap.get(idVaga);
                    if (vagaDTO == null) {
                        vagaDTO = new VagaDTO(
                                idVaga,
                                nomeVaga,
                                resultSet.getString("cidade"),
                                resultSet.getString("descricao"),
                                resultSet.getString("formacaoMinima"),
                                resultSet.getString("experienciaMinina"),
                                new ArrayList<>()
                        );
                        vagaDTOMap.put(idVaga, vagaDTO);
                    }

                    vagaDTOs.add(vagaDTO);
                }
            }
        }

        return vagaDTOs;
    }

    @Override
    Vaga buscarVagaPorId(Integer idVaga) throws SQLException {
        String sql = "SELECT idEmpresa, nome, descricao, cidade, formacaoMinima, experienciaMinina FROM vagas WHERE id = ?";

        try (PreparedStatement statement = databaseConnection.prepareStatement(sql)) {
            statement.setInt(1, idVaga);

            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    Integer idEmpresa = resultSet.getInt("idEmpresa");
                    String nome = resultSet.getString("nome");
                    String descricao = resultSet.getString("descricao");
                    String cidade = resultSet.getString("cidade");
                    String formacaoMinima = resultSet.getString("formacaoMinima");
                    String experienciaMinima = resultSet.getString("experienciaMinina");

                    Vaga vaga = new Vaga(idEmpresa, nome, descricao, cidade, formacaoMinima, experienciaMinima);
                    vaga.setId(idVaga);

                    return vaga;
                }
            }
        }
        return null;
    }


    @Override
    void adicionarVaga(Vaga vaga) throws SQLException {
        String sql = "INSERT INTO vagas (idEmpresa, nome, descricao, cidade, formacaoMinima, experienciaMinina) " +
                "VALUES (?, ?, ?, ?, ?, ?)";

        try (PreparedStatement statement = databaseConnection.prepareStatement(sql)) {
            statement.setInt(1, vaga.getIdEmpresa());
            statement.setString(2, vaga.getNome());
            statement.setString(3, vaga.getDescricao());
            statement.setString(4, vaga.getCidade());
            statement.setString(5, vaga.getFormacaoMinima());
            statement.setString(6, vaga.getExperienciaMinima());

            statement.executeUpdate();
        }
    }


    @Override
    void atualizarVaga(Vaga vaga) throws SQLException {
        String sql = "UPDATE vagas SET idEmpresa = ?, nome = ?, descricao = ?, cidade = ?, formacaoMinima = ?, experienciaMinina = ? " +
                "WHERE id = ?";

        try (PreparedStatement statement = databaseConnection.prepareStatement(sql)) {
            statement.setInt(1, vaga.getIdEmpresa());
            statement.setString(2, vaga.getNome());
            statement.setString(3, vaga.getDescricao());
            statement.setString(4, vaga.getCidade());
            statement.setString(5, vaga.getFormacaoMinima());
            statement.setString(6, vaga.getExperienciaMinima());
            statement.setInt(7, vaga.getId());

            statement.executeUpdate();
        }
    }

    @Override
    void excluirVaga(Integer idVaga) throws SQLException {
        String sql = "DELETE FROM vagas WHERE id = ?";

        try (PreparedStatement statement = databaseConnection.prepareStatement(sql)) {
            statement.setInt(1, idVaga);
            statement.executeUpdate();
        }
    }
}
