package test.service


import dao.competencia.ICompetenciaDao
import entity.Competencias
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import service.CompetenciaService

import java.sql.SQLException

import static org.mockito.Mockito.*;

class CompetenciaServiceTest {

    private ICompetenciaDao competenciaDao;
    private CompetenciaService competenciaService;

    @BeforeEach
    void setup() {
        competenciaDao = mock(ICompetenciaDao.class);
        competenciaService = new CompetenciaService(competenciaDao);
    }

    @Test
    void testListarCompetencias() throws SQLException {
        List<Competencias> competenciasMock = new ArrayList<>();
        competenciasMock.add(new Competencias("Java", "Avançado"));
        competenciasMock.add(new Competencias("SQL", "Intermediário"));

        when(competenciaDao.listarTodasCompetencias()).thenReturn(competenciasMock);

        List<Competencias> result = competenciaService.listarCompetencias();

        verify(competenciaDao).listarTodasCompetencias();

        assert competenciasMock.size() == result.size();
        assert competenciasMock == result;
    }

    @Test
    void testBuscarCompetenciaPorId() throws SQLException {
        Integer idCompetencia = 1;
        Competencias competenciaMock = new Competencias("Java", "Avançado");
        competenciaMock.setId(idCompetencia);

        when(competenciaDao.buscarCompetenciaPorId(idCompetencia)).thenReturn(competenciaMock);

        Competencias result = competenciaService.buscarCompetenciaPorId(idCompetencia);

        verify(competenciaDao).buscarCompetenciaPorId(idCompetencia);

        assert competenciaMock  == result;
    }

    @Test
    void testAdicionarCompetencia() throws SQLException {
        Competencias competencia = new Competencias("Java", "Avançado");

        competenciaService.adicionarCompetencia(competencia);

        verify(competenciaDao).adicionarCompetencia(competencia);
    }

    @Test
    void testAtualizarCompetencia() throws SQLException {
        Competencias competencia = new Competencias("Java", "Intermediário");
        competencia.setId(1);

        when(competenciaDao.buscarCompetenciaPorId(competencia.getId())).thenReturn(competencia);

        competenciaService.atualizarCompetencia(competencia);

        verify(competenciaDao).atualizarCompetencia(competencia);
    }

    @Test
    void testExcluirCompetencia() throws SQLException {
        Integer idCompetencia = 1;

        when(competenciaDao.buscarCompetenciaPorId(idCompetencia)).thenReturn(new Competencias("Java", "Intermediário"));

        competenciaService.excluirCompetencia(idCompetencia);

        verify(competenciaDao).excluirCompetencia(idCompetencia);
    }

}
