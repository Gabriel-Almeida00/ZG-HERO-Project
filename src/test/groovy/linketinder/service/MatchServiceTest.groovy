package linketinder.service


import linketinder.dao.match.IMatchDao
import linketinder.entity.dto.CandidatoCurtidoDTO
import linketinder.entity.dto.VagaCurtidaDTO
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

import java.sql.SQLException

import static org.mockito.ArgumentMatchers.anyInt
import static org.mockito.Mockito.*

class MatchServiceTest {

    private IMatchDao matchDao
    private MatchService matchService

    @BeforeEach
    void setUp() {
        matchDao = mock(IMatchDao.class)
        matchService = new MatchService(matchDao)
    }

    @Test
     void testEncontrarMatchesPelaVaga() throws SQLException {
        List<VagaCurtidaDTO> resultadosEsperados = new ArrayList<>()
        resultadosEsperados.add(new VagaCurtidaDTO("Candidato1", "Descrição Candidato1", "Vaga1", "Descrição Vaga1"))
        resultadosEsperados.add(new VagaCurtidaDTO("Candidato2", "Descrição Candidato2", "Vaga2", "Descrição Vaga2"))

        when(matchDao.encontrarMatchesPelaVaga(anyInt(), anyInt())).thenReturn(resultadosEsperados)


        List<VagaCurtidaDTO> resultados = matchDao.encontrarMatchesPelaVaga(1, 2)

        assert resultadosEsperados == resultados

        verify(matchDao, times(1)).encontrarMatchesPelaVaga(1, 2)
    }

    @Test
     void testEncontrarMatchesPeloCandidato() throws SQLException {
        List<CandidatoCurtidoDTO> resultadosEsperados = new ArrayList<>()
        resultadosEsperados.add(new CandidatoCurtidoDTO("Empresa1", "Descrição Empresa1", "Vaga1", "Descrição Vaga1"))
        resultadosEsperados.add(new CandidatoCurtidoDTO("Empresa2", "Descrição Empresa2", "Vaga2", "Descrição Vaga2"))

        when(matchDao.encontrarMatchesPeloCandidato(anyInt())).thenReturn(resultadosEsperados)

        List<CandidatoCurtidoDTO> resultados = matchDao.encontrarMatchesPeloCandidato(1)

        assert resultadosEsperados == resultados

        verify(matchDao, times(1)).encontrarMatchesPeloCandidato(1)
    }
}
