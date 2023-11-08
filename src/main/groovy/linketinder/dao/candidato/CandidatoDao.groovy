package linketinder.dao.candidato

import linketinder.exception.CandidatosNotFoundException
import linketinder.exception.DataBaseException
import linketinder.db.IDatabaseConnection
import linketinder.model.Candidato
import linketinder.model.dto.CandidatoDTO
import linketinder.model.dto.CompetenciaCandidatoDTO
import linketinder.model.dto.ExperienciaDTO
import linketinder.model.dto.FormacaoDTO

import java.sql.Connection
import java.sql.PreparedStatement
import java.sql.ResultSet
import java.sql.SQLException

class CandidatoDao implements ICandidatoDao {

    private IDatabaseConnection databaseConnection

    CandidatoDao(IDatabaseConnection databaseConnection) {
        this.databaseConnection = databaseConnection
    }

    @Override
    Candidato obterCandidatoPorId(Integer id) {
        String sql = "SELECT * FROM candidatos WHERE id = ?"

        try (Connection connection = databaseConnection.getConnection()
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, id)

            try (ResultSet resultSet = statement.executeQuery()) {
                return retornarCandidatoResultSet(resultSet)
            }
        } catch (SQLException e) {
            throw new DataBaseException("Erro ao acessar o banco de dados: " + e.getMessage())
        }
    }

    private Candidato retornarCandidatoResultSet(ResultSet resultSet) {
        if (resultSet.next()) {
            Candidato candidato = new Candidato(
                    resultSet.getString("nome"),
                    resultSet.getString("email"),
                    resultSet.getString("pais"),
                    resultSet.getString("cep"),
                    resultSet.getString("descricao"),
                    resultSet.getString("senha"),
                    resultSet.getString("sobrenome"),
                    resultSet.getDate("dataNascimento"),
                    resultSet.getString("redeSocial"),
                    resultSet.getString("telefone"),
                    resultSet.getString("cpf")
            )
            candidato.setId(resultSet.getInt("id"))
            return candidato
        } else {
            throw new CandidatosNotFoundException("Candidato não encontrado")
        }
    }

    @Override
    List<CandidatoDTO> listarCandidatos() {
        String sql = "SELECT " +
                "         c.id , " +
                "         c.nome, " +
                "         c.descricao, " +
                "         f.idnivelformacao as nivelFormacao, " +
                "         f.instituicao AS instituicao_formacao, " +
                "         f.curso AS curso_formacao, " +
                "         f.anoConclusao AS ano_conclusao_formacao, " +
                "         e.idnivelexperiencia as nivelExperiencia, " +
                "         e.cargo AS cargo_experiencia, " +
                "         e.empresa AS empresa_experiencia, " +
                "         vc.idnivelcompetencia as nivelCompetencia, " +
                "         comp.nome AS competencia " +
                "       FROM candidatos c " +
                "            LEFT JOIN formacoes f ON c.id = f.idCandidato " +
                "            LEFT JOIN experiencias e ON c.id = e.idCandidato " +
                "            LEFT JOIN candidato_competencia vc ON c.id = vc.idCandidato " +
                "            LEFT JOIN competencias comp ON vc.idCompetencia = comp.id;"

        try (Connection connection = databaseConnection.getConnection()
             PreparedStatement statement = connection.prepareStatement(sql)
             ResultSet resultSet = statement.executeQuery()) {
            return retornarCandidatosDTOResultSet(resultSet)

        } catch (SQLException e) {
            throw new DataBaseException("Erro ao acessar o banco de dados: " + e.getMessage())
        }
    }

    private List<CandidatoDTO> retornarCandidatosDTOResultSet(ResultSet resultSet) {
        List<CandidatoDTO> candidatosDTO = new ArrayList<>()

        while (resultSet.next()) {
            int candidatoId = resultSet.getInt("id")
            String nome = resultSet.getString("nome")
            String descricao = resultSet.getString("descricao")

            List<FormacaoDTO> formacoes = new ArrayList<>()
            List<ExperienciaDTO> experiencias = new ArrayList<>()
            List<CompetenciaCandidatoDTO> competencias = new ArrayList<>()

            do {
                Integer nivelFormacao = resultSet.getInt("nivelformacao")
                Integer nivelExperiencia = resultSet.getInt("nivelexperiencia")
                Integer nivelCompetencia = resultSet.getInt("nivelcompetencia")
                String instituicaoFormacao = resultSet.getString("instituicao_formacao")
                String cursoFormacao = resultSet.getString("curso_formacao")
                String anoConclusaoFormacao = resultSet.getString("ano_conclusao_formacao")
                String cargoExperiencia = resultSet.getString("cargo_experiencia")
                String empresaExperiencia = resultSet.getString("empresa_experiencia")
                String competencia = resultSet.getString("competencia")

                if (instituicaoFormacao != null) {
                    formacoes.add(new FormacaoDTO(instituicaoFormacao, cursoFormacao, anoConclusaoFormacao,nivelFormacao))
                }
                if (cargoExperiencia != null) {
                    experiencias.add(new ExperienciaDTO(cargoExperiencia, empresaExperiencia, nivelExperiencia))
                }
                if (competencia != null) {
                    competencias.add(new CompetenciaCandidatoDTO(competencia, nivelCompetencia))
                }
            } while (resultSet.next() && resultSet.getInt("id") == candidatoId)

            CandidatoDTO candidatoDTO = new CandidatoDTO(
                    candidatoId,
                    nome,
                    descricao,
                    formacoes,
                    experiencias,
                    competencias
            )

            candidatosDTO.add(candidatoDTO)
        }
        return candidatosDTO
    }



    @Override
    void adicionarCandidato(Candidato candidato) {
        String sql = "INSERT INTO candidatos (nome, sobrenome, dataNascimento, email,redeSocial, telefone, cpf, pais, cep, descricao, senha) " +
                "VALUES (?, ?,? ,?,?, ?, ?, ?, ?, ?, ?)"

        try (PreparedStatement statement = databaseConnection.prepareStatement(sql)) {
            statement.setString(1, candidato.getNome())
            statement.setString(2, candidato.getSobrenome())
            statement.setDate(3, new java.sql.Date(candidato.getDataNascimento().time))
            statement.setString(4, candidato.getEmail())
            statement.setString(5, candidato.getRedeSocial())
            statement.setString(6, candidato.getTelefone())
            statement.setString(7, candidato.getCpf())
            statement.setString(8, candidato.getPais())
            statement.setString(9, candidato.getCep())
            statement.setString(10, candidato.getDescricao())
            statement.setString(11, candidato.getSenha())
            statement.executeUpdate()

        } catch (SQLException e) {
            throw new DataBaseException("Erro ao acessar o banco de dados: " + e.getMessage())
        }
    }


    @Override
    void atualizarCandidato(Candidato candidato) {
        obterCandidatoPorId(candidato.getId())

        String sql = "UPDATE candidatos SET nome=?, sobrenome=?, dataNascimento=?, email=?,redeSocial=?,telefone=?, cpf=?, pais=?, cep=?, descricao=?, senha=? WHERE id=?"

        try (Connection connection = databaseConnection.getConnection()
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, candidato.getNome())
            statement.setString(2, candidato.getSobrenome())
            statement.setDate(3, new java.sql.Date(candidato.getDataNascimento().getTime()))
            statement.setString(4, candidato.getEmail())
            statement.setString(5, candidato.getRedeSocial())
            statement.setString(6, candidato.getTelefone())
            statement.setString(7, candidato.getCpf())
            statement.setString(8, candidato.getPais())
            statement.setString(9, candidato.getCep())
            statement.setString(10, candidato.getDescricao())
            statement.setString(11, candidato.getSenha())
            statement.setInt(12, candidato.getId())
            statement.executeUpdate()

        } catch (SQLException e) {
            throw new DataBaseException("Erro ao acessar o banco de dados: " + e.getMessage())
        }
    }

    @Override
    void deletarCandidato(Integer id) {
        obterCandidatoPorId(id)

        String sql = "DELETE FROM candidatos WHERE id=?"
        try (PreparedStatement statement = databaseConnection.prepareStatement(sql)) {
            statement.setInt(1, id)
            statement.executeUpdate()

        } catch (SQLException e) {
            throw new DataBaseException("Erro ao acessar o banco de dados: " + e.getMessage())
        }
    }
}
