package linketinder.service.candidato


import linketinder.model.Candidato
import linketinder.model.Formacao
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

import java.sql.SQLException

import static org.mockito.Mockito.*

class CandidatoFormacaoServiceTest {
    private ICandidatoFormacaoService candidatoFormacaoService

    @BeforeEach
    void setup() {
        candidatoFormacaoService = mock(ICandidatoFormacaoService.class)
    }

    @Test
    void testAdicionarFormacao() throws SQLException {
        Formacao formacao = new Formacao(
                1,
                "Instituição",
                "Curso",
                2,
                "2023")

        doNothing().when(candidatoFormacaoService).adicionarFormacao(formacao)

        candidatoFormacaoService.adicionarFormacao(formacao)

        verify(candidatoFormacaoService, times(1)).adicionarFormacao(formacao)
    }

    @Test
    void testAtualizarFormacao() throws SQLException {
        Formacao formacao = new Formacao(
                1,
                "Instituição",
                "Curso",
                1,
                "2023")

        doNothing().when(candidatoFormacaoService).atualizarFormacao(formacao)

        candidatoFormacaoService.atualizarFormacao(formacao)

        verify(candidatoFormacaoService, times(1)).atualizarFormacao(formacao)
    }

    @Test
    void testExcluirFormacao() throws SQLException {
        Integer idFormacao = 1


        doNothing().when(candidatoFormacaoService).excluirFormacao(idFormacao)

        candidatoFormacaoService.excluirFormacao(idFormacao)

        verify(candidatoFormacaoService, times(1)).excluirFormacao(idFormacao)
    }

    @Test
    void testListarFormacoesPorCandidato() throws SQLException {
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

        List<Formacao> formacoes = new ArrayList<>()
        formacoes.add(new Formacao(idCandidato, "Instituição 1", "Curso 1", 1, "Ano 2021"))
        formacoes.add(new Formacao(idCandidato, "Instituição 2", "Curso 2", 2, "Ano 2022"))

        when(candidatoFormacaoService.listarFormacoesPorCandidato(idCandidato)).thenReturn(formacoes)

        List<Formacao> result = candidatoFormacaoService.listarFormacoesPorCandidato(idCandidato)

        verify(candidatoFormacaoService, times(1)).listarFormacoesPorCandidato(idCandidato)
        assert formacoes == result
    }
}
