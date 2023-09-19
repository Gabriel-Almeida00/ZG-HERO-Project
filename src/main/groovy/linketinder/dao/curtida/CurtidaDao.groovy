package linketinder.dao.curtida


import linketinder.db.IDatabaseConnection
import linketinder.entity.Empresa
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
}
