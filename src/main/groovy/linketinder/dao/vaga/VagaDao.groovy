package linketinder.dao.vaga


import linketinder.db.IDatabaseConnection
import linketinder.entity.Vaga
import linketinder.entity.dto.CompetenciaDTO
import linketinder.entity.dto.VagaDTO

import java.sql.Connection
import java.sql.PreparedStatement
import java.sql.ResultSet
import java.sql.SQLException

class VagaDao implements IVagaDao{

    private final IDatabaseConnection databaseConnection

    VagaDao(IDatabaseConnection databaseConnection) {
        this.databaseConnection = databaseConnection
    }

    @Override
    List<VagaDTO> listarTodasVagas() throws SQLException {
        List<VagaDTO> vagaDTOs = new ArrayList<>()

        String sql = "SELECT" +
                "    v.id AS id_vaga," +
                "    v.nome AS nome_vaga," +
                "    v.descricao," +
                "    v.cidade," +
                "    v.idNivelFormacao," +
                "    v.idNivelExperiencia," +
                "    c.nome AS nome_competencia, " +
                "    vc.idNivelCompetencia " +
                " FROM " +
                "    vagas v" +
                " INNER JOIN" +
                "    vaga_competencia vc ON v.id = vc.idVaga" +
                " INNER JOIN" +
                "    competencias c ON vc.idCompetencia = c.id;"

        try (PreparedStatement statement = databaseConnection.prepareStatement(sql)
             ResultSet resultSet = statement.executeQuery()) {

            Map<Integer, VagaDTO> vagaDTOMap = new HashMap<>()

            while (resultSet.next()) {
                Integer idVaga = resultSet.getInt("id_vaga")
                String nomeVaga = resultSet.getString("nome_vaga")

                VagaDTO vagaDTO = vagaDTOMap.get(idVaga)
                if (vagaDTO == null) {
                    vagaDTO = new VagaDTO(
                            idVaga,
                            nomeVaga,
                            resultSet.getString("cidade"),
                            resultSet.getString("descricao"),
                            resultSet.getInt("idNivelFormacao"),
                            resultSet.getInt("idNivelExperiencia"),
                            new ArrayList<>()
                    )
                    vagaDTOMap.put(idVaga, vagaDTO)
                }

                CompetenciaDTO competenciaDTO = new CompetenciaDTO(
                        resultSet.getString("nome_competencia"),
                        resultSet.getInt("idNivelCompetencia")
                )

                vagaDTO.getCompetencias().add(competenciaDTO)
            }

            vagaDTOs.addAll(vagaDTOMap.values())
        }

        return vagaDTOs
    }

    @Override
    List<VagaDTO> listarVagasDaEmpresa(int idEmpresa) throws SQLException {
        List<VagaDTO> vagaDTOs = new ArrayList<>()

        String sql = "SELECT " +
                "    v.id AS id_vaga," +
                "    v.nome AS nome_vaga," +
                "    v.descricao," +
                "    v.cidade," +
                "    v.idNivelFormacao," +
                "    v.idNivelExperiencia" +
                " FROM " +
                "    vagas v" +
                " JOIN" +
                "    empresas e ON v.idEmpresa = e.id" +
                " WHERE" +
                "    v.idEmpresa = ?"

        try (Connection connection = databaseConnection.getConnection()
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setInt(1, idEmpresa)

            try (ResultSet resultSet = statement.executeQuery()) {
                Map<Integer, VagaDTO> vagaDTOMap = new HashMap<>()

                while (resultSet.next()) {
                    Integer idVaga = resultSet.getInt("id_vaga")
                    String nomeVaga = resultSet.getString("nome_vaga")

                    VagaDTO vagaDTO = vagaDTOMap.get(idVaga)
                    if (vagaDTO == null) {
                        vagaDTO = new VagaDTO(
                                idVaga,
                                nomeVaga,
                                resultSet.getString("cidade"),
                                resultSet.getString("descricao"),
                                resultSet.getInt("idNivelFormacao"),
                                resultSet.getInt("idNivelExperiencia"),
                                new ArrayList<>()
                        )
                        vagaDTOMap.put(idVaga, vagaDTO)
                    }

                    vagaDTOs.add(vagaDTO)
                }
            }
        }

        return vagaDTOs
    }

    @Override
    Vaga buscarVagaPorId(Integer idVaga) throws SQLException {
        String sql = "SELECT idEmpresa, nome, descricao, cidade, idNivelFormacao, idNivelExperiencia FROM vagas WHERE id = ?"

        try (PreparedStatement statement = databaseConnection.prepareStatement(sql)) {
            statement.setInt(1, idVaga)

            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    Integer idEmpresa = resultSet.getInt("idEmpresa")
                    String nome = resultSet.getString("nome")
                    String descricao = resultSet.getString("descricao")
                    String cidade = resultSet.getString("cidade")
                    Integer formacaoMinima = resultSet.getInt("idNivelFormacao")
                    Integer experienciaMinima = resultSet.getInt("idNivelExperiencia")

                    Vaga vaga = new Vaga(idEmpresa, nome, descricao, cidade, formacaoMinima, experienciaMinima)
                    vaga.setId(idVaga)

                    return vaga
                }
            }
        }
        return null
    }


    @Override
    void adicionarVaga(Vaga vaga) throws SQLException {
        String sql = "INSERT INTO vagas (idEmpresa, nome, descricao, cidade, idNivelFormacao, idNivelExperiencia) " +
                "VALUES (?, ?, ?, ?, ?, ?)"

        try (PreparedStatement statement = databaseConnection.prepareStatement(sql)) {
            statement.setInt(1, vaga.getIdEmpresa())
            statement.setString(2, vaga.getNome())
            statement.setString(3, vaga.getDescricao())
            statement.setString(4, vaga.getCidade())
            statement.setInt(5, vaga.getFormacaoMinima())
            statement.setInt(6, vaga.getExperienciaMinima())

            statement.executeUpdate()
        }
    }


    @Override
    void atualizarVaga(Vaga vaga) throws SQLException {
        String sql = "UPDATE vagas SET idEmpresa = ?, nome = ?, descricao = ?, cidade = ?, idNivelFormacao = ?, idNivelExperiencia = ? " +
                "WHERE id = ?"

        try (PreparedStatement statement = databaseConnection.prepareStatement(sql)) {
            statement.setInt(1, vaga.getIdEmpresa())
            statement.setString(2, vaga.getNome())
            statement.setString(3, vaga.getDescricao())
            statement.setString(4, vaga.getCidade())
            statement.setInt(5, vaga.getFormacaoMinima())
            statement.setInt(6, vaga.getExperienciaMinima())
            statement.setInt(7, vaga.getId())

            statement.executeUpdate()
        }
    }

    @Override
    void excluirVaga(Integer idVaga) throws SQLException {
        String sql = "DELETE FROM vagas WHERE id = ?"

        try (PreparedStatement statement = databaseConnection.prepareStatement(sql)) {
            statement.setInt(1, idVaga)
            statement.executeUpdate()
        }
    }
}
