package test.service

import db.IDatabaseConnection
import entity.Candidato
import entity.Experiencia
import entity.Formacao
import org.junit.jupiter.api.BeforeEach

import static org.mockito.Mockito.*;
import java.sql.*;
import org.junit.jupiter.api.Test;
import service.CandidatoService;


class CandatoServiceTest {

    private IDatabaseConnection databaseConnectionMock;
    private Connection connectionMock;
    private PreparedStatement statementMock;
    private ResultSet resultSetMock;

    @BeforeEach
    void setup() {
        databaseConnectionMock = mock(IDatabaseConnection.class);
        connectionMock = mock(Connection.class);
        statementMock = mock(PreparedStatement.class);
        resultSetMock = mock(ResultSet.class);

        when(databaseConnectionMock.getConnection()).thenReturn(connectionMock);
    }

    @Test
    void testListarCandidatos() throws SQLException {

        when(databaseConnectionMock.prepareStatement(anyString())).thenReturn(statementMock);
        when(statementMock.executeQuery()).thenReturn(resultSetMock);
        when(resultSetMock.next()).thenReturn(true, true, false);
        when(resultSetMock.getString("nome")).thenReturn("João", "Maria");
        when(resultSetMock.getString("sobrenome")).thenReturn("Silva", "Silva");


        CandidatoService candidatoService = new CandidatoService(databaseConnectionMock);

        List<Candidato> candidatos = candidatoService.listarCandidatos();

        assert candidatos.size() == 2;
        assert candidatos.get(0).getNome() == "João";
        assert candidatos.get(1).getNome() == "Maria";

        verify(databaseConnectionMock).prepareStatement(anyString());
        verify(statementMock).executeQuery();
    }

    @Test
    void testCadastrarCandidato() throws SQLException {

        when(databaseConnectionMock.prepareStatement(anyString())).thenReturn(statementMock);

        CandidatoService candidatoService = new CandidatoService(databaseConnectionMock);

        Candidato candidato = new Candidato(
                "João",
                "Silva",
                new Date(System.currentTimeMillis()),
                "joao@example.com",
                "12345678900",
                "Brasil",
                "12345-678",
                "Descrição do candidato",
                "senha123");

        candidatoService.cadastrarCandidato(candidato);

        verify(databaseConnectionMock).prepareStatement(anyString());
        verify(statementMock).setString(1, candidato.getNome());
        verify(statementMock).setString(2, candidato.getSobrenome());
        verify(statementMock).executeUpdate();
    }

    @Test
    void testAtualizarCandidato() throws SQLException {


        Candidato candidatoAtualizado = new Candidato(
                "João",
                "Silva",
                new Date(System.currentTimeMillis()),
                "joao@example.com",
                "12345678900",
                "Brasil",
                "12345-678",
                "Descrição do candidato",
                "senha123"
        );
        candidatoAtualizado.setId(1)

        when(databaseConnectionMock.prepareStatement(anyString())).thenReturn(statementMock);

        CandidatoService candidatoService = new CandidatoService(databaseConnectionMock);

        candidatoService.atualizarCandidato(candidatoAtualizado);

        verify(databaseConnectionMock).prepareStatement(anyString());
        verify(statementMock).executeUpdate()
    }

    @Test
    void testDeletarCandidato() throws SQLException {

        when(databaseConnectionMock.prepareStatement(anyString())).thenReturn(statementMock);

        CandidatoService candidatoService = new CandidatoService(databaseConnectionMock);

        int candidatoId = 1
        candidatoService.deletarCandidato(candidatoId);

        verify(databaseConnectionMock).prepareStatement(anyString());
        verify(statementMock).executeUpdate();
    }

    @Test
    void testAdicionarFormacaoCandidato() throws SQLException {
        when(databaseConnectionMock.prepareStatement(anyString())).thenReturn(statementMock);

        CandidatoService candidatoService = new CandidatoService(databaseConnectionMock);

        Formacao formacao = new Formacao(
                1,
                "Nome da Instituição",
                "Nome do Curso",
                "Nível do Curso",
                "Ano de Conclusão"
        );

        candidatoService.adicionarFormacaoCandidato(1, formacao);

        verify(databaseConnectionMock).prepareStatement(anyString());


        verify(statementMock).setInt(eq(1), eq(1));
        verify(statementMock).setString(eq(2), eq("Nome da Instituição"));
        verify(statementMock).setString(eq(3), eq("Nome do Curso"));
        verify(statementMock).setString(eq(4), eq("Nível do Curso"));
        verify(statementMock).setString(eq(5), eq("Ano de Conclusão"));

        verify(statementMock).executeUpdate();
    }

    @Test
    void testAdicionarExperienciaCandidato() throws SQLException {
        when(databaseConnectionMock.prepareStatement(anyString())).thenReturn(statementMock);

        CandidatoService candidatoService = new CandidatoService(databaseConnectionMock);

        Experiencia experiencia = new Experiencia(
                1,
                "Desenvolvedor",
                "Empresa XYZ",
                "Júnior"
        );

        candidatoService.adicionarExperienciaCandidato(1, experiencia);

        verify(databaseConnectionMock).prepareStatement(anyString());

        verify(statementMock).setInt(eq(1), eq(1));
        verify(statementMock).setString(eq(2), eq("Desenvolvedor"));
        verify(statementMock).setString(eq(3), eq("Empresa XYZ"));
        verify(statementMock).setString(eq(4), eq("Júnior"));

        verify(statementMock).executeUpdate();
    }

    @Test
    void testAdicionarCompetencia() throws SQLException {
        when(databaseConnectionMock.prepareStatement(anyString())).thenReturn(statementMock);

        CandidatoService candidatoService = new CandidatoService(databaseConnectionMock);

        Integer idCandidato = 1;
        Integer idCompetencia = 2;

        candidatoService.adicionarCompetencia(idCandidato, idCompetencia);

        verify(databaseConnectionMock).prepareStatement(anyString());
        verify(statementMock).setInt(1, idCandidato);
        verify(statementMock).setInt(2, idCompetencia);
        verify(statementMock).executeUpdate();
    }


    @Test
    void testObterCandidatoPorId() throws Exception {
        Integer candidatoId = 1;

        when(databaseConnectionMock.prepareStatement(anyString())).thenReturn(statementMock);
        when(statementMock.executeQuery()).thenReturn(resultSetMock);
        when(resultSetMock.next()).thenReturn(true);
        when(resultSetMock.getInt("id")).thenReturn(candidatoId);
        when(resultSetMock.getString("nome")).thenReturn("João");

        CandidatoService candidatoService = new CandidatoService(databaseConnectionMock);

        Candidato candidato = candidatoService.obterCandidatoPorId(candidatoId);

        assert candidato != null;
        assert candidato.getNome() == ("João");
        assert candidato.getId() == candidatoId;

        verify(databaseConnectionMock).prepareStatement(anyString());
        verify(statementMock).executeQuery();
    }
}
