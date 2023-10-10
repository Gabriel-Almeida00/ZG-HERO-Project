package linketinder.service.vaga

import linketinder.model.VagaCompetencia
import linketinder.model.dto.CompetenciaDTO
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

import java.sql.SQLException

import static org.mockito.Mockito.*

class VagaCompetenciaServiceTest {
    private IVagaCompetenciaService vagaCompetenciaService


    @BeforeEach
    void setup() {
        vagaCompetenciaService = mock(IVagaCompetenciaService.class)
    }

    @Test
    void testAdicionarVagaCompetencia() {
        VagaCompetencia vagaCompetenciaMock = new VagaCompetencia(1, 2, 1)

        doNothing().when(vagaCompetenciaService).adicionarVagaCompetencia(vagaCompetenciaMock)

        vagaCompetenciaService.adicionarVagaCompetencia(vagaCompetenciaMock)

        verify(vagaCompetenciaService, times(1)).adicionarVagaCompetencia(vagaCompetenciaMock)
    }

    @Test
    void testAtualizarNivelVagaCompetencia() {
        VagaCompetencia vagaCompetenciaMock = new VagaCompetencia(1, 2, 2)

        doNothing().when(vagaCompetenciaService).atualizarNivelVagaCompetencia(vagaCompetenciaMock)

        vagaCompetenciaService.atualizarNivelVagaCompetencia(vagaCompetenciaMock)

        verify(vagaCompetenciaService, times(1)).atualizarNivelVagaCompetencia(vagaCompetenciaMock)
    }

    @Test
    void testExcluirVagaCompetencia() {
        Integer idVagaCompetencia = 1

        doNothing().when(vagaCompetenciaService).excluirVagaCompetencia(idVagaCompetencia)

        vagaCompetenciaService.excluirVagaCompetencia(idVagaCompetencia)

        verify(vagaCompetenciaService, times(1)).excluirVagaCompetencia(idVagaCompetencia)
    }

    @Test
    void testListarCompetenciasPorVaga() throws SQLException {
        Integer idVaga = 1

        List<CompetenciaDTO> vagaCompetenciaListMock = new ArrayList<>()
        vagaCompetenciaListMock.add(new CompetenciaDTO(
                1,
                "java",
                "básico"
        ))
        vagaCompetenciaListMock.add(new CompetenciaDTO(
                2,
                "groovy",
                "avançado"
        ))

        when(vagaCompetenciaService.listarCompetenciasPorVaga(idVaga)).thenReturn(vagaCompetenciaListMock)

        List<CompetenciaDTO> result = vagaCompetenciaService.listarCompetenciasPorVaga(idVaga)

        verify(vagaCompetenciaService).listarCompetenciasPorVaga(idVaga)

        assert vagaCompetenciaListMock.size() == result.size()
        assert vagaCompetenciaListMock == result
    }
}
