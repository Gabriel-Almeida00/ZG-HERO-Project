package linketinder.service.vaga

import linketinder.model.Vaga
import linketinder.model.dto.VagaDTO
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

import java.sql.SQLException

import static org.mockito.Mockito.*

class VagaServiceTest {

    private IVagaService service

    @BeforeEach
    void setup() {
        service = mock(IVagaService.class)
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

        when(service.listarTodasVagas()).thenReturn(vagasMock)

        List<VagaDTO> result = service.listarTodasVagas()

        verify(service, times(1)).listarTodasVagas()

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

        when(service.listarVagasDaEmpresa(idEmpresa)).thenReturn(vagasMock)

        List<VagaDTO> result = service.listarVagasDaEmpresa(idEmpresa)

        verify(service, times(1)).listarVagasDaEmpresa(idEmpresa)

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
        when(service.buscarVagaPorId(idVaga)).thenReturn(vagaMock)

        Vaga result = service.buscarVagaPorId(idVaga)

        verify(service).buscarVagaPorId(idVaga)
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

        verify(service).adicionarVaga(vaga)
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

        when(service.buscarVagaPorId(vaga.getId())).thenReturn(vaga)

        service.atualizarVaga(vaga)

        verify(service).atualizarVaga(vaga)
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
        when(service.buscarVagaPorId(idVaga)).thenReturn(vagaMock)

        service.excluirVaga(idVaga)

        verify(service).excluirVaga(idVaga)
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

        when(service.obterIdEmpresaPorIdVaga(idVagaExistente)).thenReturn(idEmpresaEsperado)

        Integer idEmpresa = service.obterIdEmpresaPorIdVaga(idVagaExistente)

        assert idEmpresaEsperado == idEmpresa
    }
}
