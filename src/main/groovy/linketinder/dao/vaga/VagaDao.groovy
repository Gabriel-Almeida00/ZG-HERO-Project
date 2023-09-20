package linketinder.dao.vaga


import linketinder.db.IDatabaseConnection
import linketinder.entity.Vaga
import linketinder.entity.dto.VagaDTO

import java.sql.Connection
import java.sql.PreparedStatement
import java.sql.ResultSet
import java.sql.SQLException

class VagaDao implements IVagaDao {

    private final IDatabaseConnection databaseConnection

    VagaDao(IDatabaseConnection databaseConnection) {
        this.databaseConnection = databaseConnection
    }

    @Override
    List<VagaDTO> listarTodasVagas() {
        String sql = "SELECT v.id AS id_vaga, v.nome AS nome_vaga, v.descricao AS descricao_vaga, " +
                "STRING_AGG(comp.nome, ', ') AS nomes_competencia " +
                "FROM vagas v " +
                "INNER JOIN vaga_competencia cv ON v.id = cv.idVaga " +
                "INNER JOIN competencias comp ON cv.idCompetencia = comp.id " +
                "GROUP BY v.id, v.nome, v.descricao"

        List<VagaDTO> vagas = new ArrayList<>()

        try (Connection connection = databaseConnection.getConnection()
             PreparedStatement statement = connection.prepareStatement(sql)
             ResultSet resultSet = statement.executeQuery()) {

            while (resultSet.next()) {
                Integer id = resultSet.getInt("id_vaga")
                String nome = resultSet.getString("nome_vaga")
                String descricao = resultSet.getString("descricao_vaga")
                String nomesCompetencia = resultSet.getString("nomes_competencia")

                List<String> nomeCompetencia = Arrays.asList(nomesCompetencia.split(", "))

                VagaDTO vagaDTO = new VagaDTO(id, nome, descricao, nomeCompetencia)
                vagas.add(vagaDTO)
            }

        }
        return vagas
    }

    @Override
    Integer obterIdEmpresaPorIdVaga(Integer idVaga) {
        String sql = "SELECT idEmpresa FROM vagas WHERE id = ?"

        try (Connection connection = databaseConnection.getConnection()
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setInt(1, idVaga)

            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    return resultSet.getInt("idEmpresa")
                }
            }
        }

        return null
    }

    @Override
    List<VagaDTO> listarVagasDaEmpresa(int idEmpresa) throws SQLException {
        List<VagaDTO> vagaDTOs = new ArrayList<>()

        String sql = "SELECT " +
                "    v.id AS id_vaga, " +
                "    v.nome AS nome_vaga, " +
                "    v.descricao, " +
                "    STRING_AGG(comp.nome, ', ') AS nomes_competencia " +
                "FROM " +
                "    vagas v " +
                "LEFT JOIN " +
                "    vaga_competencia cv ON v.id = cv.idVaga " +
                "LEFT JOIN " +
                "    competencias comp ON cv.idCompetencia = comp.id " +
                "WHERE " +
                "    v.idEmpresa = ? " +
                "GROUP BY " +
                "    v.id, v.nome, v.descricao;"

        try (Connection connection = databaseConnection.getConnection()
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setInt(1, idEmpresa)

            try (ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    Integer idVaga = resultSet.getInt("id_vaga")
                    String nomeVaga = resultSet.getString("nome_vaga")
                    String descricao = resultSet.getString("descricao")
                    String nomesCompetencia = resultSet.getString("nomes_competencia")

                    List<String> nomeCompetencia = nomesCompetencia != null ? Arrays.asList(nomesCompetencia.split(", ")) : Collections.emptyList() as List<String>

                    VagaDTO vagaDTO = new VagaDTO(
                            idVaga,
                            nomeVaga,
                            descricao,
                            nomeCompetencia
                    )

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
