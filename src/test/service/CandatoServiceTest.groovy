package test.service

import db.DatabaseConnection
import db.IDatabaseConnection
import entity.Candidato
import org.junit.jupiter.api.BeforeEach
import org.mockito.Mock

import static org.mockito.Mockito.*;
import java.sql.*;
import org.junit.jupiter.api.Test;
import service.CandidatoService;


class CandatoServiceTest {

    @Test
    void testListarCandidatos() throws SQLException {
        // Crie um mock da IDatabaseConnection (usando a interface)
        IDatabaseConnection databaseConnectionMock = mock(IDatabaseConnection.class);

        // Crie mocks de PreparedStatement e ResultSet
        PreparedStatement statementMock = mock(PreparedStatement.class);
        ResultSet resultSetMock = mock(ResultSet.class);

        // Defina o comportamento dos mocks
        when(databaseConnectionMock.prepareStatement(anyString())).thenReturn(statementMock);
        when(statementMock.executeQuery()).thenReturn(resultSetMock);
        when(resultSetMock.next()).thenReturn(true, true, false);
        when(resultSetMock.getString("nome")).thenReturn("João", "Maria");
        when(resultSetMock.getString("sobrenome")).thenReturn("Silva", "Silva");
        // Defina outros comportamentos, se necessário

        // Crie uma instância de CandidatoService usando o mock da IDatabaseConnection
        CandidatoService candidatoService = new CandidatoService(databaseConnectionMock);

        // Chame o método a ser testado
        List<Candidato> candidatos = candidatoService.listarCandidatos();

        // Verifique as assertivas
        assert   candidatos.size() == 2;
        assert candidatos.get(0).getNome() == "João";
        assert candidatos.get(1).getNome() == "Maria";

        // Verifique as interações com os mocks
        verify(databaseConnectionMock).prepareStatement(anyString());
        verify(statementMock).executeQuery();
    }
}
