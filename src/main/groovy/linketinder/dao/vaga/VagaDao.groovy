package linketinder.dao.vaga

import linketinder.Exception.DataBaseException
import linketinder.Exception.EmpresasNotFoundException
import linketinder.config.Config
import linketinder.db.DatabaseConnection
import linketinder.db.IDatabaseConnection
import linketinder.entity.Vaga
import linketinder.entity.dto.VagaDTO

import java.sql.Connection
import java.sql.PreparedStatement
import java.sql.ResultSet
import java.sql.SQLException

class VagaDao implements IVagaDao {

    private IDatabaseConnection databaseConnection

    VagaDao() {
        Config config = new Config()
        databaseConnection = new DatabaseConnection(config)
    }

    @Override
    List<VagaDTO> listarTodasVagas() {
        String sql = "SELECT v.id AS id_vaga, v.nome AS nome_vaga, v.descricao AS descricao_vaga, " +
                "STRING_AGG(comp.nome, ', ') AS nomes_competencia " +
                "FROM vagas v " +
                "INNER JOIN vaga_competencia cv ON v.id = cv.idVaga " +
                "INNER JOIN competencias comp ON cv.idCompetencia = comp.id " +
                "GROUP BY v.id, v.nome, v.descricao"

        try (Connection connection = databaseConnection.getConnection()
             PreparedStatement statement = connection.prepareStatement(sql)
             ResultSet resultSet = statement.executeQuery()) {

            return extrairVagas(resultSet)
        }
    }

    private List<VagaDTO> extrairVagas(ResultSet resultSet) throws SQLException {
        List<VagaDTO> vagasList = new ArrayList<>()

        while (resultSet.next()) {
            Integer id = resultSet.getInt("id_vaga")
            String nome = resultSet.getString("nome_vaga")
            String descricao = resultSet.getString("descricao_vaga")
            String nomesCompetencia = resultSet.getString("nomes_competencia")

            List<String> nomeCompetencia = Arrays.asList(nomesCompetencia.split(", "))

            VagaDTO vagaDTO = new VagaDTO(id, nome, descricao, nomeCompetencia)
            vagasList.add(vagaDTO)
        }
        return vagasList
    }

    @Override
    Integer obterIdEmpresaPorIdVaga(Integer idVaga) {
        String sql = "SELECT idEmpresa FROM vagas WHERE id = ?"

        try (Connection connection = databaseConnection.getConnection()
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, idVaga)

            try (ResultSet resultSet = statement.executeQuery()) {
                return extrairIdEmpresa(resultSet)
            }
        } catch (SQLException e) {
            throw new DataBaseException("Erro ao acessar o banco de dados: " + e.getMessage())
        }
    }

    private Integer extrairIdEmpresa(ResultSet resultSet) throws SQLException {
        if (resultSet.next()) {
            return resultSet.getInt("idEmpresa")
        } else {
            throw new EmpresasNotFoundException("Empresa não encontrada")
        }
    }

    @Override
    List<VagaDTO> listarVagasDaEmpresa(int idEmpresa) throws SQLException {
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

            return extrairVagasDTO(statement.executeQuery())
        }
    }

    private List<VagaDTO> extrairVagasDTO(ResultSet resultSet) throws SQLException {
        List<VagaDTO> vagaDTOsList = new ArrayList<>()

        while (resultSet.next()) {
            Integer idVaga = resultSet.getInt("id_vaga")
            String nomeVaga = resultSet.getString("nome_vaga")
            String descricao = resultSet.getString("descricao")
            String nomesCompetencia = resultSet.getString("nomes_competencia")

            List<String> nomeCompetenciaList = extrairNomesCompetencia(nomesCompetencia)

            VagaDTO vagaDTO = new VagaDTO(
                    idVaga,
                    nomeVaga,
                    descricao,
                    nomeCompetenciaList
            )
            vagaDTOsList.add(vagaDTO)
        }
        return vagaDTOsList
    }

    private List<String> extrairNomesCompetencia(String nomesCompetencia) {
        if (nomesCompetencia != null) {
            return Arrays.asList(nomesCompetencia.split(", "))
        } else {
            return Collections.emptyList()
        }
    }

    @Override
    Vaga buscarVagaPorId(Integer idVaga) throws SQLException {
        String sql = "SELECT idEmpresa, nome, descricao, cidade, idNivelFormacao, idNivelExperiencia FROM vagas WHERE id = ?"

        try (PreparedStatement statement = databaseConnection.prepareStatement(sql)) {
            statement.setInt(1, idVaga)

            return extrairVaga(statement.executeQuery(), idVaga)
        }
    }

    private Vaga extrairVaga(ResultSet resultSet, Integer idVaga) throws SQLException {
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
