package linketinder.dao.curtida

import linketinder.db.IDatabaseConnection
import linketinder.entity.dto.CandidatoQueCurtiuVagaDTO
import linketinder.entity.dto.EmpresaDTO

import java.sql.Connection
import java.sql.PreparedStatement
import java.sql.ResultSet
import java.sql.SQLException

class CurtidaDao implements ICurtidaDao {

    private IDatabaseConnection databaseConnection

    CurtidaDao(IDatabaseConnection databaseConnection) {
        this.databaseConnection = databaseConnection
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

    List<EmpresaDTO> listarEmpresasQueCurtiramCandidato(Integer idCandidato) throws SQLException {
        List<EmpresaDTO> empresasCurtidasDTO = new ArrayList<>()

        String sql = "SELECT e.id, e.pais, e.descricao " +
                        "FROM empresas e " +
                        "INNER JOIN curtidas c ON e.id = c.idEmpresa " +
                        "WHERE c.idCandidato = ?"

        try (Connection connection = databaseConnection.getConnection()
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setInt(1, idCandidato)

            try (ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    Integer idEmpresa = resultSet.getInt("id")
                    String pais = resultSet.getString("pais")
                    String descricaoEmpresa = resultSet.getString("descricao")

                    EmpresaDTO empresa = new EmpresaDTO( pais, descricaoEmpresa)
                    empresa.setId(idEmpresa)
                    empresasCurtidasDTO.add(empresa)
                }
            }
        }

        return empresasCurtidasDTO
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
    List<CandidatoQueCurtiuVagaDTO> listarCandidatosQueCurtiramVaga(Integer idVaga) throws SQLException {
        List<CandidatoQueCurtiuVagaDTO> candidatosCurtiramDTO = new ArrayList<>();

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
                "GROUP BY c.id, c.descricao";

        try (Connection connection = databaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setInt(1, idVaga);

            try (ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    Integer idCandidato = resultSet.getInt("id_candidato");
                    String descricao = resultSet.getString("descricao_candidato");
                    String nomesCompetencia = resultSet.getString("nomes_competencia");

                    List<String> nomesCompetenciaList = Arrays.asList(nomesCompetencia.split(", "));

                    CandidatoQueCurtiuVagaDTO candidatoDTO = new CandidatoQueCurtiuVagaDTO(
                            idCandidato,
                            descricao,
                            nomesCompetenciaList
                    );

                    candidatosCurtiramDTO.add(candidatoDTO);
                }
            }
        }

        return candidatosCurtiramDTO;
    }
}
