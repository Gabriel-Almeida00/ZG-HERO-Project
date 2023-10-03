package linketinder.service.curtida


import linketinder.dao.curtida.ICurtidaDao
import linketinder.entity.Candidato
import linketinder.entity.Empresa
import linketinder.entity.Vaga
import linketinder.entity.dto.CandidatoQueCurtiuVagaDTO
import linketinder.entity.dto.EmpresaDTO
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

import java.sql.SQLException

import static org.mockito.ArgumentMatchers.anyInt
import static org.mockito.Mockito.*

class CurtidaServiceTest {

    private ICurtidaService curtidaService
    private ICurtidaDao curtidaDao

    @BeforeEach
    void setup() {
        curtidaService = mock(ICurtidaService.class)
        curtidaDao = mock(ICurtidaDao.class)
    }

    @Test
    void testCurtirVaga_ComSucesso() throws SQLException {
        Integer idCandidato = 2
        Integer idVaga = 2

        Candidato candidato = new Candidato(
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
        candidato.setId(idCandidato)

        Vaga vaga = new Vaga(
                2,
                "desenvolvedor",
                "SP",
                "tech descricao",
                1,
                2
        )
        vaga.setId(idVaga)


        curtidaService.curtirVaga(idCandidato, idVaga)

        verify(curtidaService).curtirVaga(idCandidato, idVaga)
    }

    @Test
    void testAtualizarCurtida_ComSucesso() throws SQLException {
        Integer idCandidato = 2
        Integer idVaga = 2
        Integer empresaId = 1

        Candidato candidato = new Candidato(
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
        candidato.setId(idCandidato)

        Vaga vaga = new Vaga(
                empresaId,
                "desenvolvedor",
                "SP",
                "tech descricao",
                1,
                2
        )
        vaga.setId(idVaga)


        when(curtidaDao.verificaCurtidaDaEmpresa(anyInt(), anyInt())).thenReturn(empresaId)

        curtidaDao.AtualizarCurtidaComIdVaga(idVaga, empresaId, idCandidato)

        verify(curtidaDao).AtualizarCurtidaComIdVaga(idVaga, empresaId, idCandidato)
    }


    @Test
    void testListarEmpresasQueCurtiramCandidato() {
        Integer id = 1
        Candidato candidato = new Candidato(
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
        candidato.setId(id)

        List<EmpresaDTO> empresas = new ArrayList<>()
        empresas.add(new EmpresaDTO("São Paulo", "Empresa A"))
        empresas.add(new EmpresaDTO("Rio de Janeiro", "Empresa B"))


        when(curtidaService.listarEmpresasQueCurtiramCandidato(id)).thenReturn(empresas)
        List<EmpresaDTO> empresasDTO = curtidaService.listarEmpresasQueCurtiramCandidato(id)


        verify(curtidaService).listarEmpresasQueCurtiramCandidato(id)

        assert empresas == empresasDTO
    }

    @Test
    void testListarCandidatosQueCurtiramVaga() throws SQLException {
        Integer idVaga = 1


        List<CandidatoQueCurtiuVagaDTO> resultadosEsperados = Arrays.asList(
                new CandidatoQueCurtiuVagaDTO(1, "Descrição Candidato 1", Arrays.asList("Java", "Python")),
                new CandidatoQueCurtiuVagaDTO(2, "Descrição Candidato 2", Arrays.asList("Groovy", "Python"))
        )
        when(curtidaService.listarCandidatosQueCurtiramVaga(idVaga)).thenReturn(resultadosEsperados)

        List<CandidatoQueCurtiuVagaDTO> resultado = curtidaService.listarCandidatosQueCurtiramVaga(idVaga)

        assert resultadosEsperados == resultado

        verify(curtidaService, times(1)).listarCandidatosQueCurtiramVaga(idVaga)
    }

    @Test
    void testCurtirCandidato_ComSucesso() throws SQLException {
        Integer idCandidato = 2
        Integer idEmpresa = 4

        Candidato candidato = new Candidato(
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
        candidato.setId(idCandidato)

        Empresa empresa = new Empresa(
                "Empresa 1",
                "empresa1@gmail.com",
                "brasil",
                "321123",
                "descrição empresa 1",
                "321teste",
                "1233"
        )
        empresa.setId(idEmpresa)


        when(curtidaDao.verificaCurtidaDaEmpresa(idEmpresa, idCandidato)).thenReturn(null)

        curtidaService.curtirCandidato(idCandidato, idEmpresa)

        verify(curtidaService).curtirCandidato(idCandidato, idEmpresa)
    }

    @Test
    void testCurtirCandidato_AtualizarCurtida() throws SQLException {
        Integer idCandidato = 2
        Integer idEmpresa = 4
        Integer idVaga = 7

        Candidato candidato = new Candidato(
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
        candidato.setId(idCandidato)

        Empresa empresa = new Empresa(
                "Empresa 1",
                "empresa1@gmail.com",
                "brasil",
                "321123",
                "descrição empresa 1",
                "321teste",
                "1233"
        )
        empresa.setId(idEmpresa)


        when(curtidaDao.verificaCurtidaDoCandidato(idCandidato)).thenReturn(idVaga)

        curtidaService.curtirCandidato(idCandidato, idEmpresa)

        verify(curtidaService).curtirCandidato(idCandidato, idEmpresa)
    }
}
