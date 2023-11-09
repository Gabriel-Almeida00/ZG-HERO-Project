package linketinder.service.candidato


import linketinder.model.Candidato
import linketinder.model.dto.CandidatoDTO
import linketinder.model.dto.CompetenciaCandidatoDTO
import linketinder.model.dto.ExperienciaDTO
import linketinder.model.dto.FormacaoDTO
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

import java.sql.SQLException

import static org.mockito.Mockito.*

class CandidatoServiceTest {

    private ICandidatoService candidatoService

    @BeforeEach
    void setup() {
        candidatoService = mock(ICandidatoService.class)
    }

    @Test
    void testListarCandidatos() throws SQLException {
        List<CandidatoDTO> candidatosMock = new ArrayList<>()
        candidatosMock.add(
                new CandidatoDTO(1, "Candidato 1", "Descrição 1",
                        Arrays.asList("teste","teste","teste",1) as List<FormacaoDTO>,
                        Arrays.asList("teste","teste",1) as List<ExperienciaDTO>,
                        Arrays.asList("Competência 1", "Competência 2") as List<CompetenciaCandidatoDTO>))
        candidatosMock.add(
                new CandidatoDTO(2, "Candidato 2", "Descrição 2",
                        Arrays.asList("teste","teste","teste",1) as List<FormacaoDTO>,
                        Arrays.asList("teste","teste",1) as List<ExperienciaDTO>,
                        Arrays.asList("Competência 3", "Competência 4") as List<CompetenciaCandidatoDTO>))

        when(candidatoService.listarCandidatos()).thenReturn(candidatosMock)
        List<CandidatoDTO> resultado = candidatoService.listarCandidatos()

        verify(candidatoService).listarCandidatos()
        assert candidatosMock == resultado
    }

    @Test
    void testObterCandidatoPorId() throws SQLException {
        Integer candidatoId = 1
        Candidato candidatoMock = new Candidato(
                "gabriel",
                "gabrel@gmail.com",
                "brasil",
                "32132",
                "descrição",
                "123teste",
                "almeida",
                new Date(System.currentTimeMillis()),
                "teste",
                "111111",
                "321321311"

        )
        when(candidatoService.obterCandidatoPorId(candidatoId)).thenReturn(candidatoMock)
        Candidato result = candidatoService.obterCandidatoPorId(candidatoId)

        verify(candidatoService).obterCandidatoPorId(candidatoId)
        assert candidatoMock == result
    }



    @Test
    void testAtualizarCandidato() throws SQLException {

        Candidato candidatoMock = new Candidato(
                "gabriel",
                "gabrel@gmail.com",
                "brasil",
                "32132",
                "descrição",
                "123teste",
                "almeida",
                new Date(System.currentTimeMillis()),
                "tete",
                "11232323",
                "321321311"
        )
        candidatoMock.setId(1)

        candidatoService.atualizarCandidato(candidatoMock)

        verify(candidatoService).atualizarCandidato(candidatoMock)
    }

    @Test
    void testDeletarCandidato() throws SQLException {
        Candidato candidatoMock = new Candidato(
                "gabriel",
                "gabrel@gmail.com",
                "brasil",
                "32132",
                "descrição",
                "123teste",
                "almeida",
                new Date(System.currentTimeMillis()),
                "teste",
                "112323",
                "321321311"
        )
        Integer idCandidato = 1
        candidatoMock.setId(idCandidato)

        when(candidatoService.obterCandidatoPorId(idCandidato))
                .thenReturn(candidatoMock)

        candidatoService.deletarCandidato(idCandidato)

        verify(candidatoService).deletarCandidato(idCandidato)
    }
}
