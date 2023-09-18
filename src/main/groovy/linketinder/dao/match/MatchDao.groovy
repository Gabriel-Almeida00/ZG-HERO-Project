package linketinder.dao.match


import linketinder.db.IDatabaseConnection
import linketinder.entity.dto.CandidatoCurtidoDTO
import linketinder.entity.dto.VagaCurtidaDTO

import java.sql.Connection
import java.sql.PreparedStatement
import java.sql.ResultSet
import java.sql.SQLException

class MatchDao implements IMatchDao {

    private IDatabaseConnection databaseConnection

    MatchDao(IDatabaseConnection databaseConnection) {
        this.databaseConnection = databaseConnection
    }

    List<VagaCurtidaDTO> encontrarMatchesPelaVaga(Integer idCandidato, Integer idVaga) throws SQLException {
        String sql = "SELECT DISTINCT e.nome AS nome_empresa, " +
                "e.descricao AS descricao_empresa, " +
                "v.nome AS nome_vaga, " +
                "v.descricao AS descricao_vaga " +
                "FROM vagas_curtidas vc_candidato " +
                "INNER JOIN vagas_curtidas vc_empresa ON vc_candidato.idCandidato = vc_empresa.idVaga " +
                "INNER JOIN vagas v ON vc_candidato.idVaga = v.id " +
                "INNER JOIN empresas e ON v.idEmpresa = e.id " +
                "WHERE vc_candidato.idCandidato = ? " +
                "AND vc_empresa.idVaga = ?"

        List<VagaCurtidaDTO> matches = new ArrayList<>()

        try (PreparedStatement statement = databaseConnection.prepareStatement(sql)) {
            statement.setInt(1, idCandidato)
            statement.setInt(2, idVaga)
            try (ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    String nomeEmpresa = resultSet.getString("nome_empresa")
                    String descricaoEmpresa = resultSet.getString("descricao_empresa")
                    String nomeVaga = resultSet.getString("nome_vaga")
                    String descricaoVaga = resultSet.getString("descricao_vaga")

                    VagaCurtidaDTO matchDTO = new VagaCurtidaDTO(nomeEmpresa, descricaoEmpresa, nomeVaga, descricaoVaga)
                    matches.add(matchDTO)
                }
            }
        }

        return matches
    }

    List<CandidatoCurtidoDTO> encontrarMatchesPeloCandidato(Integer idCandidato) throws SQLException {
        List<CandidatoCurtidoDTO> resultados = new ArrayList<>()

        String sql = "SELECT " +
                "    c.nome AS nome_candidato, " +
                "    c.descricao AS descricao_candidato, " +
                "    v.nome AS nome_vaga, " +
                "    v.descricao AS descricao_vaga " +
                "FROM " +
                "    candidatos c " +
                "INNER JOIN " +
                "    candidatos_curtidos cc ON c.id = cc.idCandidato " +
                "INNER JOIN " +
                "    vagas v ON cc.idEmpresa = v.idEmpresa " +
                "              AND v.id IN (SELECT idVaga FROM vagas_curtidas WHERE idCandidato = c.id) " +
                "WHERE " +
                "    c.id = ?"

        try (Connection connection = databaseConnection.getConnection()
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, idCandidato)

            try (ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    String nomeCandidato = resultSet.getString("nome_candidato")
                    String descricaoCandidato = resultSet.getString("descricao_candidato")
                    String nomeVaga = resultSet.getString("nome_vaga")
                    String descricaoVaga = resultSet.getString("descricao_vaga")

                    CandidatoCurtidoDTO candidatoCurtidoDTO = new CandidatoCurtidoDTO(nomeCandidato, descricaoCandidato, nomeVaga, descricaoVaga)
                    resultados.add(candidatoCurtidoDTO)
                }
            }
        }

        return resultados
    }
}
