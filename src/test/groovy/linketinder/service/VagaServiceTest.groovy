package linketinder.service


import linketinder.dao.curtida.ICurtidaDao
import linketinder.dao.vaga.IVagaCompetenciaDao
import linketinder.dao.vaga.IVagaDao
import linketinder.entity.Vaga
import linketinder.entity.VagaCompetencia
import linketinder.entity.dto.CandidatoQueCurtiuVagaDTO
import linketinder.entity.dto.CompetenciaDTO
import linketinder.entity.dto.VagaDTO
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

import java.sql.SQLException

import static org.mockito.Mockito.*

class VagaServiceTest {

    private IVagaDao vagaDao
    private IVagaCompetenciaDao vagaCompetenciaDao
    private ICurtidaDao curtidaDao
    private VagaService service

    @BeforeEach
    void setup() {
        vagaDao = mock(IVagaDao.class)
        vagaCompetenciaDao = mock(IVagaCompetenciaDao.class)
        curtidaDao = mock(ICurtidaDao.class)
        service = new VagaService(vagaDao, vagaCompetenciaDao, curtidaDao)
    }

    @Test
    void testListarTodasVagas() throws SQLException {

        List<VagaDTO> vagasMock = new ArrayList<>()
        vagasMock.add(new VagaDTO(
                1,
                "desenvolvedor",
                "tech descricao",
                new ArrayList<>()
        ))
        vagasMock.add(new VagaDTO(
                2,
                "desenvolvedor",
                "tech descricao",
                new ArrayList<>()
        ))

        when(vagaDao.listarTodasVagas()).thenReturn(vagasMock)

        List<VagaDTO> result = service.listarTodasVagas()

        verify(vagaDao, times(1)).listarTodasVagas()

        assert vagasMock == result
    }

    @Test
    void testListarVagasDaEmpresa() throws SQLException {
        Integer idEmpresa = 1
        List<VagaDTO> vagasMock = new ArrayList<>()
        vagasMock.add(new VagaDTO(
                1,
                "desenvolvedor",
                "tech descricao",
                new ArrayList<>()
        ))
        vagasMock.add(new VagaDTO(
                2,
                "desenvolvedor",
                "tech descricao",
                new ArrayList<>()
        ))

        when(vagaDao.listarVagasDaEmpresa(idEmpresa)).thenReturn(vagasMock)

        List<VagaDTO> result = service.listarVagasDaEmpresa(idEmpresa)

        verify(vagaDao, times(1)).listarVagasDaEmpresa(idEmpresa)

        assert vagasMock == result
    }

    @Test
    void testBuscarVagaPorId() throws SQLException {
        Integer idVaga = 1

        Vaga vagaMock = new Vaga(
                2,
                "ibm",
                "ibm descrição",
                "RJ",
                1,
                2
        )
        when(vagaDao.buscarVagaPorId(idVaga)).thenReturn(vagaMock)

        Vaga result = service.buscarVagaPorId(idVaga)

        verify(vagaDao).buscarVagaPorId(idVaga)
        assert vagaMock == result
    }

    @Test
    void testAdicionarVaga() throws SQLException {
        Vaga vaga = new Vaga(
                2,
                "ibm",
                "ibm descrição",
                "RJ",
                1,
                2
        )

        service.adicionarVaga(vaga)

        verify(vagaDao).adicionarVaga(vaga)
    }

    @Test
    void testAtualizarVaga() throws SQLException {
        Vaga vaga = new Vaga(
                2,
                "ibm",
                "ibm descrição",
                "RJ",
                1,
                3)
        vaga.setId(1)

        when(vagaDao.buscarVagaPorId(vaga.getId())).thenReturn(vaga)

        service.atualizarVaga(vaga)

        verify(vagaDao).atualizarVaga(vaga)
    }

    @Test
    void testExcluirVaga() throws SQLException {
        Integer idVaga = 1

        Vaga vagaMock = new Vaga(
                2,
                "ibm",
                "ibm descrição",
                "RJ",
                1,
                1)
        when(vagaDao.buscarVagaPorId(idVaga)).thenReturn(vagaMock)

        service.excluirVaga(idVaga)

        verify(vagaDao).excluirVaga(idVaga)
    }

    @Test
    void testAdicionarVagaCompetencia() {
        VagaCompetencia vagaCompetenciaMock = new VagaCompetencia(1, 2, 1)

        doNothing().when(vagaCompetenciaDao).adicionarVagaCompetencia(vagaCompetenciaMock)

        service.adicionarVagaCompetencia(vagaCompetenciaMock)

        verify(vagaCompetenciaDao, times(1)).adicionarVagaCompetencia(vagaCompetenciaMock)
    }

    @Test
    void testAtualizarNivelVagaCompetencia() {
        VagaCompetencia vagaCompetenciaMock = new VagaCompetencia(1, 2, 2)

        doNothing().when(vagaCompetenciaDao).atualizarNivelVagaCompetencia(vagaCompetenciaMock)

        service.atualizarNivelVagaCompetencia(vagaCompetenciaMock)

        verify(vagaCompetenciaDao, times(1)).atualizarNivelVagaCompetencia(vagaCompetenciaMock)
    }

    @Test
    void testExcluirVagaCompetencia() {
        Integer idVagaCompetencia = 1

        doNothing().when(vagaCompetenciaDao).excluirVagaCompetencia(idVagaCompetencia)

        service.excluirVagaCompetencia(idVagaCompetencia)

        verify(vagaCompetenciaDao, times(1)).excluirVagaCompetencia(idVagaCompetencia)
    }

    @Test
    void testListarCompetenciasPorVaga() throws SQLException {
        Integer idVaga = 1

        Vaga vagaMock = new Vaga(
                2,
                "ibm",
                "ibm descrição",
                "RJ",
                1,
                2
        )

        when(vagaDao.buscarVagaPorId(idVaga)).thenReturn(vagaMock)

        List<CompetenciaDTO> vagaCompetenciaListMock = new ArrayList<>()
        vagaCompetenciaListMock.add(new CompetenciaDTO(
                "java",
                Arrays.asList(1)
        ))
        vagaCompetenciaListMock.add(new CompetenciaDTO(
                "groovy",
                Arrays.asList(1)
        ))

        when(vagaCompetenciaDao.listarCompetenciasPorVaga(idVaga)).thenReturn(vagaCompetenciaListMock)

        List<CompetenciaDTO> result = service.listarCompetenciasPorVaga(idVaga)

        verify(vagaDao).buscarVagaPorId(idVaga)
        verify(vagaCompetenciaDao).listarCompetenciasPorVaga(idVaga)
        assert vagaCompetenciaListMock.size() == result.size()
        assert vagaCompetenciaListMock == result
    }

    @Test
     void testListarCandidatosQueCurtiramVaga() throws SQLException {
        int idVaga = 1
        Vaga vaga = new Vaga(
                2,
                "ibm",
                "ibm descrição",
                "RJ",
                1,
                2
        )
        when(vagaDao.buscarVagaPorId(idVaga)).thenReturn(vaga)

        List<CandidatoQueCurtiuVagaDTO> resultadosEsperados = Arrays.asList(
                new CandidatoQueCurtiuVagaDTO(1, "Descrição Candidato 1", Arrays.asList("Java", "Python")),
                new CandidatoQueCurtiuVagaDTO(2, "Descrição Candidato 2", Arrays.asList("Groovy", "Python"))
        )
        when(curtidaDao.listarCandidatosQueCurtiramVaga(idVaga)).thenReturn(resultadosEsperados)

        List<CandidatoQueCurtiuVagaDTO> resultado = service.listarCandidatosQueCurtiramVaga(idVaga)

        assert resultadosEsperados == resultado

        verify(vagaDao, times(1)).buscarVagaPorId(idVaga)
        verify(curtidaDao, times(1)).listarCandidatosQueCurtiramVaga(idVaga)
    }

    @Test
     void testObterIdEmpresaPorIdVaga() throws SQLException {
        int idVagaExistente = 1
        int idEmpresaEsperado = 10

        Vaga vaga = new Vaga(
                idEmpresaEsperado,
                "ibm",
                "ibm descrição",
                "RJ",
                1,
                2
        )
        vaga.setId(idVagaExistente)

        when(vagaDao.buscarVagaPorId(idVagaExistente)).thenReturn(vaga)
        when(vagaDao.obterIdEmpresaPorIdVaga(idVagaExistente)).thenReturn(idEmpresaEsperado)

        Integer idEmpresa = vagaDao.obterIdEmpresaPorIdVaga(idVagaExistente)

        assert idEmpresaEsperado == idEmpresa
    }
}
