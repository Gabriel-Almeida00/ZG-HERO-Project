package linketinder.service


import linketinder.dao.competencia.ICompetenciaDao
import linketinder.entity.Competencia
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

import java.sql.SQLException

import static org.mockito.Mockito.*

class CompetenciaServiceTest {

    private ICompetenciaDao competenciaDao
    private CompetenciaService competenciaService

    @BeforeEach
    void setup() {
        competenciaDao = mock(ICompetenciaDao.class)
        competenciaService = new CompetenciaService(competenciaDao)
    }

    @Test
    void testListarCompetencias() throws SQLException {
        List<Competencia> competenciasMock = new ArrayList<>()
        competenciasMock.add(new Competencia("Java"))
        competenciasMock.add(new Competencia("SQL"))

        when(competenciaDao.listarTodasCompetencias()).thenReturn(competenciasMock)

        List<Competencia> result = competenciaService.listarCompetencias()

        verify(competenciaDao).listarTodasCompetencias()

        assert competenciasMock.size() == result.size()
        assert competenciasMock == result
    }

    @Test
    void testBuscarCompetenciaPorId() throws SQLException {
        Integer idCompetencia = 1
        Competencia competenciaMock = new Competencia("Java")
        competenciaMock.setId(idCompetencia)

        when(competenciaDao.buscarCompetenciaPorId(idCompetencia)).thenReturn(competenciaMock)

        Competencia result = competenciaService.buscarCompetenciaPorId(idCompetencia)

        verify(competenciaDao).buscarCompetenciaPorId(idCompetencia)

        assert competenciaMock  == result
    }

    @Test
    void testAdicionarCompetencia() throws SQLException {
        Competencia competencia = new Competencia("Java")

        competenciaService.adicionarCompetencia(competencia)

        verify(competenciaDao).adicionarCompetencia(competencia)
    }

    @Test
    void testAtualizarCompetencia() throws SQLException {
        Competencia competencia = new Competencia("Java")
        competencia.setId(1)

        when(competenciaDao.buscarCompetenciaPorId(competencia.getId())).thenReturn(competencia)

        competenciaService.atualizarCompetencia(competencia)

        verify(competenciaDao).atualizarCompetencia(competencia)
    }

    @Test
    void testExcluirCompetencia() throws SQLException {
        Integer idCompetencia = 1

        when(competenciaDao.buscarCompetenciaPorId(idCompetencia)).thenReturn(new Competencia("Java"))

        competenciaService.excluirCompetencia(idCompetencia)

        verify(competenciaDao).excluirCompetencia(idCompetencia)
    }
}
