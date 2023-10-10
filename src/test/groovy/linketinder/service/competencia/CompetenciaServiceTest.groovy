package linketinder.service.competencia


import linketinder.model.Competencia
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

import java.sql.SQLException

import static org.mockito.Mockito.*

class CompetenciaServiceTest {

    private ICompetenciaService competenciaService

    @BeforeEach
    void setup() {
        competenciaService = mock(ICompetenciaService.class)

    }

    @Test
    void testListarCompetencias() throws SQLException {
        List<Competencia> competenciasMock = new ArrayList<>()
        competenciasMock.add(new Competencia("Java"))
        competenciasMock.add(new Competencia("SQL"))

        when(competenciaService.listarCompetencias()).thenReturn(competenciasMock)

        List<Competencia> result = competenciaService.listarCompetencias()

        verify(competenciaService).listarCompetencias()

        assert competenciasMock.size() == result.size()
        assert competenciasMock == result
    }

    @Test
    void testBuscarCompetenciaPorId() throws SQLException {
        Integer idCompetencia = 1
        Competencia competenciaMock = new Competencia("Java")
        competenciaMock.setId(idCompetencia)

        when(competenciaService.buscarCompetenciaPorId(idCompetencia)).thenReturn(competenciaMock)

        Competencia result = competenciaService.buscarCompetenciaPorId(idCompetencia)

        verify(competenciaService).buscarCompetenciaPorId(idCompetencia)

        assert competenciaMock  == result
    }

    @Test
    void testAdicionarCompetencia() throws SQLException {
        Competencia competencia = new Competencia("Java")

        competenciaService.adicionarCompetencia(competencia)

        verify(competenciaService).adicionarCompetencia(competencia)
    }

    @Test
    void testAtualizarCompetencia() throws SQLException {
        Competencia competencia = new Competencia("Java")
        competencia.setId(1)

        when(competenciaService.buscarCompetenciaPorId(competencia.getId())).thenReturn(competencia)

        competenciaService.atualizarCompetencia(competencia)

        verify(competenciaService).atualizarCompetencia(competencia)
    }

    @Test
    void testExcluirCompetencia() throws SQLException {
        Integer idCompetencia = 1

        when(competenciaService.buscarCompetenciaPorId(idCompetencia)).thenReturn(new Competencia("Java"))

        competenciaService.excluirCompetencia(idCompetencia)

        verify(competenciaService).excluirCompetencia(idCompetencia)
    }
}
