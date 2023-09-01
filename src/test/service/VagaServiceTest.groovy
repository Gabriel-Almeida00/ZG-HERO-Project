package test.service

import dao.vaga.IVagaCompetenciaDao
import dao.vaga.IVagaDao
import entity.Competencias
import entity.Vaga
import entity.VagaCompetencia
import entity.dto.CompetenciaDTO
import entity.dto.VagaDTO
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

import java.sql.SQLException

import static org.mockito.Mockito.*
import service.VagaService

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
                "graduação",
                "nenhuma",
                new ArrayList<>()
        ))
        vagasMock.add(new VagaDTO(
                2,
                "desenvolvedor",
                "SP",
                "tech descricao",
                "graduação",
                "nenhuma",
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
                "graduação",
                "nenhuma",
                new ArrayList<>()
        ))
        vagasMock.add(new VagaDTO(
                2,
                "desenvolvedor",
                "SP",
                "tech descricao",
                "graduação",
                "nenhuma",
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
                "ensino médio",
                "nenhuma"
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
                "ensino médio",
                "nenhuma"
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
                "ensino médio",
                "nenhuma")
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
                "ensino médio",
                "nenhuma")
        when(vagaDao.buscarVagaPorId(idVaga)).thenReturn(vagaMock)

        service.excluirVaga(idVaga)

        verify(vagaDao).excluirVaga(idVaga)
    }

    @Test
    void testAdicionarVagaCompetencia() {
        VagaCompetencia vagaCompetenciaMock = new VagaCompetencia(1, 2, "Avançado")

        doNothing().when(vagaCompetenciaDao).adicionarVagaCompetencia(vagaCompetenciaMock)

        service.adicionarVagaCompetencia(vagaCompetenciaMock)

        verify(vagaCompetenciaDao, times(1)).adicionarVagaCompetencia(vagaCompetenciaMock)
    }

    @Test
    void testAtualizarNivelVagaCompetencia() {
        VagaCompetencia vagaCompetenciaMock = new VagaCompetencia(1, 2, "Intermediário")

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
                "ensino médio",
                "nenhuma"
        )

        when(vagaDao.buscarVagaPorId(idVaga)).thenReturn(vagaMock)

        List<CompetenciaDTO> vagaCompetenciaListMock = new ArrayList<>()
        vagaCompetenciaListMock.add(new CompetenciaDTO(
                "java",
                "Intermediário"
        ))
        vagaCompetenciaListMock.add(new CompetenciaDTO(
                "groovy",
                "avançado"
        ))

        when(vagaCompetenciaDao.listarCompetenciasPorVaga(idVaga)).thenReturn(vagaCompetenciaListMock)

        List<CompetenciaDTO> result = service.listarCompetenciasPorVaga(idVaga)

        verify(vagaDao).buscarVagaPorId(idVaga)
        verify(vagaCompetenciaDao).listarCompetenciasPorVaga(idVaga)
        assert vagaCompetenciaListMock.size() == result.size()
        assert vagaCompetenciaListMock == result
    }
}
