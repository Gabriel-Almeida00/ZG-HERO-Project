package linketinder.service.empresa

import linketinder.model.Empresa
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

import java.sql.SQLException

import static org.mockito.Mockito.*

class EmpresaServiceTest {
    private IEmpresaService empresaService

    @BeforeEach
    void setup() {
        empresaService = mock(IEmpresaService.class)
    }

    @Test
    void testListarTodasEmpresas() throws SQLException {
        List<Empresa> empresasMock = new ArrayList<>()
        empresasMock.add(new Empresa(
                "Empresa 1",
                "empresa1@gmail.com",
                "brasil",
                "321123",
                "descrição empresa 1",
                "321teste",
                "1233"
        ))
        empresasMock.add(new Empresa(
                "Empresa 2",
                "empresa2@gmail.com",
                "brasil",
                "9898",
                "descrição empresa 2",
                "321teste2",
                "1233222"
        ))

        when(empresaService.listarTodasEmpresas()).thenReturn(empresasMock)

        List<Empresa> result = empresaService.listarTodasEmpresas()

        verify(empresaService).listarTodasEmpresas()
        assert empresasMock == result
    }

    @Test
    void testObterEmpresaPorId() throws SQLException {
        Integer idEmpresa = 1
        Empresa empresaMock = new Empresa(
                "Empresa 1",
                "empresa1@gmail.com",
                "brasil",
                "321123",
                "descrição empresa 1",
                "321teste",
                "1233"
        )

        when(empresaService.obterEmpresaPorId(idEmpresa)).thenReturn(empresaMock)

        Empresa result = empresaService.obterEmpresaPorId(idEmpresa)

        verify(empresaService).obterEmpresaPorId(idEmpresa)
        assert empresaMock == result
    }

    @Test
    void testAdicionarEmpresa() throws SQLException {
        Empresa empresaMock = new Empresa(
                "Empresa 1",
                "empresa1@gmail.com",
                "brasil",
                "321123",
                "descrição empresa 1",
                "321teste",
                "1233"
        )

        empresaService.adicionarEmpresa(empresaMock)

        verify(empresaService).adicionarEmpresa(empresaMock)
    }

    @Test
    void testAtualizarEmpresa() throws SQLException {
        Integer idEmpresa = 1
        Empresa empresaMock = new Empresa(
                "Empresa 1",
                "empresa1@gmail.com",
                "brasil",
                "321123",
                "descrição empresa 1",
                "321teste",
                "1233"
        )
        empresaMock.setId(idEmpresa)


        empresaService.atualizarEmpresa(empresaMock)

        verify(empresaService).atualizarEmpresa(empresaMock)
    }

    @Test
    void testExcluirEmpresa() throws SQLException {
        Integer idEmpresa = 1
        Empresa empresaMock = new Empresa(
                "Empresa 1",
                "empresa1@gmail.com",
                "brasil",
                "321123",
                "descrição empresa 1",
                "321teste",
                "1233"
        )
        empresaMock.setId(idEmpresa)


        empresaService.excluirEmpresa(idEmpresa)

        verify(empresaService).excluirEmpresa(idEmpresa)
    }
}

