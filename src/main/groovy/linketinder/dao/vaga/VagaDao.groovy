package linketinder.dao.vaga

import linketinder.exception.DataBaseException
import linketinder.exception.EmpresasNotFoundException
import linketinder.exception.VagaNotFoundException
import linketinder.db.IDatabaseConnection
import linketinder.model.Vaga
import linketinder.model.dto.CompetenciaCandidatoDTO
import linketinder.model.dto.ExperienciaVagaDTO
import linketinder.model.dto.FormacaoVagaDTO
import linketinder.model.dto.VagaDTO

import java.sql.Connection
import java.sql.PreparedStatement
import java.sql.ResultSet
import java.sql.SQLException

class VagaDao implements IVagaDao {

    private IDatabaseConnection databaseConnection

    VagaDao(IDatabaseConnection databaseConnection) {
        this.databaseConnection = databaseConnection
    }

    @Override
    List<VagaDTO> listarTodasVagas() {
        String sql = "SELECT " +
                "    v.id AS id_vaga, " +
                "    v.nome AS nome_vaga, " +
                "    v.descricao AS descricao_vaga, " +
                "    v.cidade, " +
                "    nf.id AS idNivelFormacao, " +
                "    nf.nivel AS formacao, " +
                "    ne.id as idNivelExperiencia,"+
                "    ne.nivel AS experiencia, " +
                "    c.nome AS competencia, " +
                "    vc.idnivelcompetencia AS nivel_competencia " +
                "FROM " +
                "    vagas v " +
                "        JOIN nivel_formacao nf ON v.idNivelFormacao = nf.id " +
                "        JOIN nivel_experiencia ne ON v.idNivelExperiencia = ne.id " +
                "        LEFT JOIN vaga_competencia vc ON v.id = vc.idVaga " +
                "        LEFT JOIN competencias c ON vc.idCompetencia = c.id " +
                "        LEFT JOIN nivel_competencia nc ON vc.idNivelCompetencia = nc.id;"

        try (Connection connection = databaseConnection.getConnection()
             PreparedStatement statement = connection.prepareStatement(sql)
             ResultSet resultSet = statement.executeQuery()) {

            return retornarVagasResultSet(resultSet)
        } catch (SQLException e) {
            throw new DataBaseException("Erro ao acessar o banco de dados: " + e.getMessage())
        }
    }

    private List<VagaDTO> retornarVagasResultSet(ResultSet resultSet) throws SQLException {
        List<VagaDTO> vagasList = new ArrayList<>()

        while (resultSet.next()) {
            Integer id = resultSet.getInt("id_vaga")
            String nome = resultSet.getString("nome_vaga")
            String descricao = resultSet.getString("descricao_vaga")
            String cidade = resultSet.getString("cidade")


            List<CompetenciaCandidatoDTO> competencias = new ArrayList<>()
            ExperienciaVagaDTO experiencia = new ExperienciaVagaDTO()
            FormacaoVagaDTO formacao = new FormacaoVagaDTO()

            do {
                String competencia = resultSet.getString("competencia")
                Integer nivel = resultSet.getInt("nivel_competencia")

                Integer idFormacao = resultSet.getInt("idnivelformacao")
                String nomeFormacao = resultSet.getString("formacao")

                Integer idExperiencia = resultSet.getInt("idnivelexperiencia")
                String nomeExperiencia = resultSet.getString("experiencia")

                if (competencia != null && nivel != null) {
                    competencias.add(new CompetenciaCandidatoDTO(competencia, nivel))
                }
                if(idFormacao != null && nomeFormacao != null){
                    formacao.setNivel(idFormacao)
                    formacao.setNome(nomeFormacao)
                }
                if(idExperiencia != null && nomeExperiencia != null){
                    experiencia.setNivel(idExperiencia)
                    experiencia.setNome(nomeFormacao)
                }
            } while (resultSet.next() && resultSet.getInt("id_vaga") == id)

            VagaDTO vagaDTO = new VagaDTO(
                    id,
                    nome,
                    descricao,
                    cidade,
                    formacao,
                    experiencia,
                    competencias
            )
            vagasList.add(vagaDTO)
        }
        return vagasList
    }

    @Override
    Integer obterIdEmpresaPorIdVaga(Integer idVaga) {
        buscarVagaPorId(idVaga)

        String sql = "SELECT idEmpresa FROM vagas WHERE id = ?"
        try (Connection connection = databaseConnection.getConnection()
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, idVaga)

            try (ResultSet resultSet = statement.executeQuery()) {
                return retornarIdEmpresaResultSet(resultSet)
            }
        } catch (SQLException e) {
            throw new DataBaseException("Erro ao acessar o banco de dados: " + e.getMessage())
        }
    }

    private Integer retornarIdEmpresaResultSet(ResultSet resultSet) throws SQLException {
        if (resultSet.next()) {
            return resultSet.getInt("idEmpresa")
        } else {
            throw new EmpresasNotFoundException("Empresa não encontrada")
        }
    }

    @Override
    List<Vaga> listarVagasDaEmpresa(int idEmpresa) throws SQLException {
        String sql = "select * from vagas where idempresa =?"

        try (Connection connection = databaseConnection.getConnection()
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setInt(1, idEmpresa)

            return retornarVagasDtoResultSet(statement.executeQuery())
        } catch (SQLException e) {
            throw new DataBaseException("Erro ao acessar o banco de dados: " + e.getMessage())
        }
    }

    private List<Vaga> retornarVagasDtoResultSet(ResultSet resultSet) throws SQLException {
        List<Vaga> vagaList = new ArrayList<>()

        while (resultSet.next()) {
            Integer id = resultSet.getInt("id")
            Integer idEmpresa = resultSet.getInt("idempresa")
            String nome = resultSet.getString("nome")
            String descricao = resultSet.getString("descricao")
            String cidade = resultSet.getString("cidade")
            Integer nivelFormacao = resultSet.getInt("idnivelformacao")
            Integer nivelExperiencia = resultSet.getInt("idnivelexperiencia")

            Vaga vaga = new Vaga(
                    idEmpresa,
                    nome,
                    descricao,
                    cidade,
                    nivelFormacao,
                    nivelExperiencia
            )
            vaga.setId(id)
            vagaList.add(vaga)
        }
        return vagaList
    }


    @Override
    Vaga buscarVagaPorId(Integer idVaga) throws SQLException {
        String sql = "SELECT idEmpresa, nome, descricao, cidade, idNivelFormacao, idNivelExperiencia FROM vagas WHERE id = ?"

        try (PreparedStatement statement = databaseConnection.prepareStatement(sql)) {
            statement.setInt(1, idVaga)

            return extrairVaga(statement.executeQuery(), idVaga)
        } catch (SQLException e) {
            throw new DataBaseException("Erro ao acessar o banco de dados: " + e.getMessage())
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
        } else {
            throw new VagaNotFoundException("Vaga não encontrada com ID : " + idVaga)
        }

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
        } catch (SQLException e) {
            throw new DataBaseException("Erro ao acessar o banco de dados: " + e.getMessage())
        }
    }


    @Override
    void atualizarVaga(Vaga vaga) throws SQLException {
        buscarVagaPorId(vaga.getId())

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
        } catch (SQLException e) {
            throw new DataBaseException("Erro ao acessar o banco de dados: " + e.getMessage())
        }
    }

    @Override
    void excluirVaga(Integer idVaga) throws SQLException {
        buscarVagaPorId(idVaga)

        String sql = "DELETE FROM vagas WHERE id = ?"
        try (PreparedStatement statement = databaseConnection.prepareStatement(sql)) {
            statement.setInt(1, idVaga)
            statement.executeUpdate()
        } catch (SQLException e) {
            throw new DataBaseException("Erro ao acessar o banco de dados: " + e.getMessage())
        }
    }
}
