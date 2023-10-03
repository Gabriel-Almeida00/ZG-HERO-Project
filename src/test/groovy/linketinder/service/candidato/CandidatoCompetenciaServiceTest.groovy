package linketinder.service.candidato


import linketinder.entity.Candidato
import linketinder.entity.CandidatoCompetencia
import linketinder.entity.dto.CompetenciaDTO
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

import java.sql.SQLException

import static org.mockito.Mockito.*

class CandidatoCompetenciaServiceTest {

    private ICandidatoCompetenciaService candidatoCompetenciaService

    @BeforeEach
    void setup() {
        candidatoCompetenciaService = mock(ICandidatoCompetenciaService.class)
    }

    @Test
    void testListarCompetenciasCandidato() throws SQLException {
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
        Integer idCandidato = 1
        candidatoMock.setId(idCandidato)

        List<CompetenciaDTO> competenciasMock = new ArrayList<>()
        competenciasMock.add(new CompetenciaDTO(1, "Java", "Intermediároo"))
        competenciasMock.add(new CompetenciaDTO(2, "SQL", "Avançado"))

        when(candidatoCompetenciaService.listarCompetenciasPorCandidato(idCandidato)).thenReturn(competenciasMock)
        List<CompetenciaDTO> result = candidatoCompetenciaService.listarCompetenciasPorCandidato(idCandidato)
        verify(candidatoCompetenciaService).listarCompetenciasPorCandidato(idCandidato)

        assert competenciasMock.size() == result.size()
        assert competenciasMock == result
    }

    @Test
    void testAdicionarCandidatoCompetencia() throws SQLException {
        Integer idCandidato = 1
        Integer idCompetencia = 2
        Integer nivel = 3

        CandidatoCompetencia candidatoCompetencia = new CandidatoCompetencia(idCandidato, idCompetencia, nivel)

        doNothing().when(candidatoCompetenciaService).adicionarCandidatoCompetencia(candidatoCompetencia)

        candidatoCompetenciaService.adicionarCandidatoCompetencia(candidatoCompetencia)

        verify(candidatoCompetenciaService, times(1)).adicionarCandidatoCompetencia(candidatoCompetencia)
    }

    @Test
    void testAtualizarNivelCompetenciaCandidato() throws SQLException {
        Integer id = 1
        Integer idCompetencia = 1
        Integer novoNivel = 2

        CandidatoCompetencia candidatoCompetencia = new CandidatoCompetencia(1, idCompetencia, novoNivel)
        candidatoCompetencia.setId(id)

        CompetenciaDTO competenciasMock = new CompetenciaDTO(idCompetencia, "Java", "Avançado")
        competenciasMock.setId(idCompetencia)

        doNothing().when(candidatoCompetenciaService).atualizarNivelCompetenciaCandidato(candidatoCompetencia)

        candidatoCompetenciaService.atualizarNivelCompetenciaCandidato(candidatoCompetencia)

        verify(candidatoCompetenciaService, times(1)).atualizarNivelCompetenciaCandidato(candidatoCompetencia)
    }

    @Test
    void testExcluirCompetenciaCandidato() throws SQLException {
        Integer idCompetencia = 2

        CompetenciaDTO competenciasMock = new CompetenciaDTO(idCompetencia, "Java", "Básico")
        competenciasMock.setId(idCompetencia)

        doNothing().when(candidatoCompetenciaService).excluirCompetenciaCandidato(idCompetencia)

        candidatoCompetenciaService.excluirCompetenciaCandidato(idCompetencia)

        verify(candidatoCompetenciaService, times(1)).excluirCompetenciaCandidato(idCompetencia)
    }
}
