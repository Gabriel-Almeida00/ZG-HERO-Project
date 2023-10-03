package linketinder.service.candidato


import linketinder.entity.Candidato
import linketinder.entity.Experiencia
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

import java.sql.SQLException

import static org.mockito.Mockito.*

class CandidatoExperienciaServiceTest {
    private ICandidatoExperienciaService candidatoExperienciaService

    @BeforeEach
    void setup() {
        candidatoExperienciaService = mock(ICandidatoExperienciaService.class)
    }

    @Test
    void testAdicionarExperiencia() throws SQLException {
        Experiencia experienciaMock = mock(Experiencia.class)

        doNothing().when(candidatoExperienciaService).adicionarExperiencia(experienciaMock)

        candidatoExperienciaService.adicionarExperiencia(experienciaMock)

        verify(candidatoExperienciaService, times(1)).adicionarExperiencia(experienciaMock)
    }

    @Test
    void testAtualizarExperiencia() throws SQLException {
        Experiencia experienciaMock = mock(Experiencia.class)

        doNothing().when(candidatoExperienciaService).atualizarExperiencia(experienciaMock)

        candidatoExperienciaService.atualizarExperiencia(experienciaMock)

        verify(candidatoExperienciaService, times(1)).atualizarExperiencia(experienciaMock)
    }

    @Test
    void testListarExperienciasPorCandidato() throws SQLException {
        Integer idCandidato = 1
        Candidato candidatoMock = new Candidato(
                "gabriel",
                "gabrel@gmail.com",
                "brasil",
                "32132",
                "descrição",
                "123teste",
                "almeida",
                new Date(System.currentTimeMillis()),
                "321321311"
        )
        candidatoMock.setId(idCandidato)
        List<Experiencia> experienciasMock = new ArrayList<>()

        when(candidatoExperienciaService.listarExperienciasPorCandidato(idCandidato)).thenReturn(experienciasMock)
        List<Experiencia> result = candidatoExperienciaService.listarExperienciasPorCandidato(idCandidato)

        assert experienciasMock == result
    }

    @Test
    void testExcluirExperiencia() throws SQLException {
        Integer idExperiencia = 1

        candidatoExperienciaService.excluirExperiencia(idExperiencia)

        verify(candidatoExperienciaService).excluirExperiencia(idExperiencia)
    }
}
