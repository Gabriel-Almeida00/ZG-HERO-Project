package linketinder.service


import linketinder.dao.match.IMatchDao
import linketinder.entity.dto.MatchCandidatoDTO
import linketinder.entity.dto.MatchEmpresaDTO
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
        List<MatchCandidatoDTO> resultadosEsperados = new ArrayList<>()
        resultadosEsperados.add(new MatchCandidatoDTO(1,"Candidato1", "Descrição Candidato1",1, "Vaga1", "Descrição Vaga1"))
        resultadosEsperados.add(new MatchCandidatoDTO(2,"Candidato2", "Descrição Candidato2",2, "Vaga2", "Descrição Vaga2"))

        when(matchDao.encontrarMatchesPelaEmpresa(anyInt())).thenReturn(resultadosEsperados)


        List<MatchCandidatoDTO> resultados = matchDao.encontrarMatchesPelaEmpresa(1)

        assert resultadosEsperados == resultados

        verify(matchDao, times(1)).encontrarMatchesPelaEmpresa(1)
    }

    @Test
     void testEncontrarMatchesPeloCandidato() throws SQLException {
        List<MatchEmpresaDTO> resultadosEsperados = new ArrayList<>()
        resultadosEsperados.add(new MatchEmpresaDTO(1,"empersa 1", "Descrição Empresa1",1, "Vaga1", "Descrição Vaga1"))
        resultadosEsperados.add(new MatchEmpresaDTO(1,"Empresa2", "Descrição Empresa2",1 , "Vaga2", "Descrição Vaga2"))

        when(matchDao.encontrarMatchesPeloCandidato(anyInt())).thenReturn(resultadosEsperados)

        List<MatchEmpresaDTO> resultados = matchDao.encontrarMatchesPeloCandidato(1)

        assert resultadosEsperados == resultados

        verify(matchDao, times(1)).encontrarMatchesPeloCandidato(1)
    }
}
