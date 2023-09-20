package linketinder.dao.curtida

import linketinder.config.Config
import linketinder.db.DatabaseConnection
import linketinder.db.IDatabaseConnection
import linketinder.entity.dto.CandidatoQueCurtiuVagaDTO
import linketinder.entity.dto.EmpresaDTO

import java.sql.Connection
import java.sql.PreparedStatement
import java.sql.ResultSet
import java.sql.SQLException

class CurtidaDao implements ICurtidaDao {

    private IDatabaseConnection databaseConnection

    CurtidaDao( ) {
        Config config = new Config()
        databaseConnection = new DatabaseConnection(config)
    }

    @Override
    void curtirVaga(Integer idCandidato, Integer idVaga) {
        String sql = "INSERT INTO curtidas (idCandidato, idVaga , idStatus) VALUES (?, ?, 1)"

        try (PreparedStatement statement = databaseConnection.prepareStatement(sql)) {
            statement.setInt(1, idCandidato)
            statement.setInt(2, idVaga)
            statement.executeUpdate()
        }
    }

    @Override
    Integer verificaCurtidaDaEmpresa(Integer idEmpresa, Integer idCandidato) {
        String sql = "SELECT idEmpresa FROM curtidas WHERE idEmpresa=? AND idCandidato=? AND idStatus=1"

        try (Connection connection = databaseConnection.getConnection()
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setInt(1, idEmpresa)
            statement.setInt(2, idCandidato)

            return obterIdEmpresaCurtiu(statement)
        }
    }

    private Integer obterIdEmpresaCurtiu(PreparedStatement statement) throws SQLException {
        try (ResultSet resultSet = statement.executeQuery()) {
            if (resultSet.next()) {
                return resultSet.getInt("idEmpresa")
            }
        }
        return null
    }

    @Override
    void AtualizarCurtidaComIdVaga(Integer idVaga, Integer idEmpresa, Integer idCandidato ) {
        String sql = " UPDATE curtidas SET idStatus = 2 , idVaga =? WHERE idEmpresa =? AND idCandidato =?"

        try (Connection connection = databaseConnection.getConnection()
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, idVaga)
            statement.setInt(2, idEmpresa)
            statement.setInt(3, idCandidato)

            statement.executeUpdate()
        }
    }

    @Override
    List<EmpresaDTO> listarEmpresasQueCurtiramCandidato(Integer idCandidato) throws SQLException {
        String sql = "SELECT e.id, e.pais, e.descricao " +
                "FROM empresas e " +
                "INNER JOIN curtidas c ON e.id = c.idEmpresa " +
                "WHERE c.idCandidato = ?"

        try (Connection connection = databaseConnection.getConnection()
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setInt(1, idCandidato)

            List<EmpresaDTO> empresasDtoList = obterEmpresasCurtiramCandidato(statement)
            return empresasDtoList
        }
    }

    private List<EmpresaDTO> obterEmpresasCurtiramCandidato(PreparedStatement statement) throws SQLException {
        List<EmpresaDTO> empresasDtoList = new ArrayList<>()

        try (ResultSet resultSet = statement.executeQuery()) {
            while (resultSet.next()) {
                Integer idEmpresa = resultSet.getInt("id")
                String pais = resultSet.getString("pais")
                String descricaoEmpresa = resultSet.getString("descricao")

                EmpresaDTO empresa = new EmpresaDTO(pais, descricaoEmpresa)
                empresa.setId(idEmpresa)
                empresasDtoList.add(empresa)
            }
        }
        return empresasDtoList
    }


    @Override
    void curtirCandidato(Integer idCandidato, Integer idEmpresa) {
        String sql = "INSERT INTO curtidas (idCandidato, idEmpresa, idStatus) VALUES (?, ?,  1)"

        try (PreparedStatement statement = databaseConnection.prepareStatement(sql)) {
            statement.setInt(1, idCandidato)
            statement.setInt(2, idEmpresa)
            statement.executeUpdate()
        }
    }

    @Override
    Integer verificaCurtidaDoCandidato(Integer idCandidato) {
        String sql = "SELECT idVaga FROM curtidas WHERE idCandidato=? AND idStatus=1"

        try (Connection connection = databaseConnection.getConnection()
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setInt(1, idCandidato)

            return obterVagaCurtida(statement)
        }
    }

    private Integer obterVagaCurtida(PreparedStatement statement) throws SQLException {
        try (ResultSet resultSet = statement.executeQuery()) {
            if (resultSet.next()) {
                return resultSet.getInt("idVaga")
            }
        }
        return null
    }

    @Override
    void AtualizarCurtidaComIdEmpresa(Integer idVaga, Integer idEmpresa, Integer idCandidato ) {
        String sql = " UPDATE curtidas SET idStatus = 2 , idEmpresa =? WHERE idVaga =? AND idCandidato =?"

        try (Connection connection = databaseConnection.getConnection()
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, idEmpresa)
            statement.setInt(2, idVaga)
            statement.setInt(3, idCandidato)

            statement.executeUpdate()
        }
    }


    @Override
    List<CandidatoQueCurtiuVagaDTO> listarCandidatosQueCurtiramVaga(Integer idVaga) throws SQLException {
        String sql = "SELECT " +
                "    c.id AS id_candidato, " +
                "    c.descricao AS descricao_candidato, " +
                "    STRING_AGG(comp.nome, ', ') AS nomes_competencia " +
                "FROM " +
                "    candidatos c " +
                "INNER JOIN " +
                "    curtidas ct ON c.id = ct.idCandidato " +
                "LEFT JOIN " +
                "    candidato_competencia cc ON c.id = cc.idCandidato " +
                "LEFT JOIN " +
                "    competencias comp ON cc.idCompetencia = comp.id " +
                "WHERE " +
                "    ct.idVaga = ? " +
                "GROUP BY c.id, c.descricao"

        try (Connection connection = databaseConnection.getConnection()
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setInt(1, idVaga)
            ResultSet resultSet = statement.executeQuery()

            return extrairCandidatosDTO(resultSet)
        }
    }

    private List<CandidatoQueCurtiuVagaDTO> extrairCandidatosDTO(ResultSet resultSet) throws SQLException {
        List<CandidatoQueCurtiuVagaDTO> candidatosList = new ArrayList<>()

        while (resultSet.next()) {
            Integer idCandidato = resultSet.getInt("id_candidato")
            String descricao = resultSet.getString("descricao_candidato")
            String nomesCompetencia = resultSet.getString("nomes_competencia")

            List<String> nomesCompetenciaList = Arrays.asList(nomesCompetencia.split(", "))

            CandidatoQueCurtiuVagaDTO candidatoDTO = new CandidatoQueCurtiuVagaDTO(
                    idCandidato,
                    descricao,
                    nomesCompetenciaList
            )
            candidatosList.add(candidatoDTO)
        }
        return candidatosList
    }
}
