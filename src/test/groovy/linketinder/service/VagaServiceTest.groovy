package linketinder.service


import linketinder.dao.vaga.IVagaCompetenciaDao
import linketinder.dao.vaga.IVagaDao
import linketinder.entity.Vaga
import linketinder.entity.VagaCompetencia
import linketinder.entity.dto.CompetenciaDTO
import linketinder.entity.dto.VagaDTO
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

import java.sql.SQLException

import static org.mockito.Mockito.*

class VagaServiceTest {

    private IVagaDao vagaDao
    private IVagaCompetenciaDao vagaCompetenciaDao
    private VagaService service

    @BeforeEach
    void setup() {
        vagaDao = mock(IVagaDao.class)
        vagaCompetenciaDao = mock(IVagaCompetenciaDao.class)
        service = new VagaService(vagaDao, vagaCompetenciaDao)
    }

    @Test
    void testListarTodasVagas() throws SQLException {

        List<VagaDTO> vagasMock = new ArrayList<>()
        vagasMock.add(new VagaDTO(
                1,
                "desenvolvedor",
                "SP",
                "tech descricao",
                1,
                2,
                new ArrayList<>()
        ))
        vagasMock.add(new VagaDTO(
                2,
                "desenvolvedor",
                "SP",
                "tech descricao",
                2,
                1,
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
                "SP",
                "tech descricao",
                1,
                2,
                new ArrayList<>()
        ))
        vagasMock.add(new VagaDTO(
                2,
                "desenvolvedor",
                "SP",
                "tech descricao",
                1,
                2,
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
                1
        ))
        vagaCompetenciaListMock.add(new CompetenciaDTO(
                "groovy",
                2
        ))

        when(vagaCompetenciaDao.listarCompetenciasPorVaga(idVaga)).thenReturn(vagaCompetenciaListMock)

        List<CompetenciaDTO> result = service.listarCompetenciasPorVaga(idVaga)

        verify(vagaDao).buscarVagaPorId(idVaga)
        verify(vagaCompetenciaDao).listarCompetenciasPorVaga(idVaga)
        assert vagaCompetenciaListMock.size() == result.size()
        assert vagaCompetenciaListMock == result
    }
}
