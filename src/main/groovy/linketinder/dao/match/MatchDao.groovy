package linketinder.dao.match

import linketinder.Exception.DataBaseException
import linketinder.db.IDatabaseConnection
import linketinder.entity.dto.MatchCandidatoDTO
import linketinder.entity.dto.MatchEmpresaDTO

import java.sql.Connection
import java.sql.PreparedStatement
import java.sql.ResultSet
import java.sql.SQLException

class MatchDao implements IMatchDao {

    private IDatabaseConnection databaseConnection

    MatchDao(IDatabaseConnection databaseConnection) {
        this.databaseConnection = databaseConnection
    }

    @Override
    List<MatchCandidatoDTO> encontrarMatchesPelaEmpresa(Integer idEmpresa) throws SQLException {
        String sql = "SELECT " +
                "    c.id AS id_candidato, " +
                "    c.nome AS nome_candidato, " +
                "    c.descricao AS descricao_candidato, " +
                "    v.id AS id_vaga, " +
                "    v.nome AS nome_vaga, " +
                "    v.descricao AS descricao_vaga " +
                "FROM " +
                "    curtidas cu " +
                "JOIN " +
                "    candidatos c ON cu.idCandidato = c.id " +
                "JOIN " +
                "    vagas v ON cu.idVaga = v.id " +
                "WHERE " +
                "    cu.idEmpresa = ? " +
                "    AND cu.idStatus = 2; "

        try (Connection connection = databaseConnection.getConnection()
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, idEmpresa)

            try (ResultSet resultSet = statement.executeQuery()) {
                return extrairMatchesCandidato(resultSet)
            }
        } catch (SQLException e) {
            throw new DataBaseException("Erro ao acessar o banco de dados: " + e.getMessage())
        }
    }

    private List<MatchCandidatoDTO> extrairMatchesCandidato(ResultSet resultSet) throws SQLException {
        List<MatchCandidatoDTO> matchesList = new ArrayList<>()

        while (resultSet.next()) {
            Integer idCandidato = resultSet.getInt("id_candidato")
            String nomeCandidato = resultSet.getString("nome_candidato")
            String descricaoCandidato = resultSet.getString("descricao_candidato")
            Integer idVaga = resultSet.getInt("id_vaga")
            String nomeVaga = resultSet.getString("nome_vaga")
            String descricaoVaga = resultSet.getString("descricao_vaga")

            MatchCandidatoDTO matchDTO = new MatchCandidatoDTO(
                    idCandidato,
                    nomeCandidato,
                    descricaoCandidato,
                    idVaga,
                    nomeVaga,
                    descricaoVaga
            )
            matchesList.add(matchDTO)
        }

        return matchesList
    }

    @Override
    List<MatchEmpresaDTO> encontrarMatchesPeloCandidato(Integer idCandidato) throws SQLException {
        String sql = "SELECT " +
                "    e.id AS id_empresa, " +
                "    e.nome AS nome_empresa, " +
                "    e.descricao AS descricao_empresa, " +
                "    v.id AS id_vaga, " +
                "    v.nome AS nome_vaga, " +
                "    v.descricao AS descricao_vaga " +
                "FROM " +
                "    curtidas c " +
                "JOIN " +
                "    empresas e ON c.idEmpresa = e.id " +
                "JOIN " +
                "    vagas v ON c.idVaga = v.id " +
                "WHERE " +
                "    c.idCandidato = ? AND c.idStatus = 2"

        try (Connection connection = databaseConnection.getConnection()
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, idCandidato)

            try (ResultSet resultSet = statement.executeQuery()) {
                return extrairMatchesEmpresa(resultSet)
            }
        } catch (SQLException e) {
            throw new DataBaseException("Erro ao acessar o banco de dados: " + e.getMessage())
        }
    }

    private List<MatchEmpresaDTO> extrairMatchesEmpresa(ResultSet resultSet) throws SQLException {
        List<MatchEmpresaDTO> matchesList = new ArrayList<>()

        while (resultSet.next()) {
            Integer idEmpresa = resultSet.getInt("id_empresa")
            String nomeEmpresa = resultSet.getString("nome_empresa")
            String descricaoEmpresa = resultSet.getString("descricao_empresa")
            Integer idVaga = resultSet.getInt("id_vaga")
            String nomeVaga = resultSet.getString("nome_vaga")
            String descricaoVaga = resultSet.getString("descricao_vaga")

            MatchEmpresaDTO match = new MatchEmpresaDTO(
                    idEmpresa,
                    nomeEmpresa,
                    descricaoEmpresa,
                    idVaga,
                    nomeVaga,
                    descricaoVaga
            )
            matchesList.add(match)
        }
        return matchesList
    }
}
