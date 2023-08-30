package test.service


import dao.empresa.IEmpresaDao
import entity.Empresa
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import static org.mockito.Mockito.*;
import service.EmpresaService

import java.sql.SQLException


class EmpresaServiceTest {

    private IEmpresaDao empresaDao;
    private EmpresaService empresaService;

    @BeforeEach
    void setup() {
        empresaDao = mock(IEmpresaDao.class);
        empresaService = new EmpresaService(empresaDao);
    }

    @Test
    void testListarTodasEmpresas() throws SQLException {
        List<Empresa> empresasMock = new ArrayList<>();
        empresasMock.add(new Empresa(
                "empresa 1",
                "12345",
                "empresa1@gmail.com",
                "empresa 1 descrição",
                "brasil",
                "54321",
                "12345"
        ));
        empresasMock.add(new Empresa(
                "empresa 2",
                "54321",
                "empresa2@gmail.com",
                "empresa 2 descrição",
                "argentina",
                "98765",
                "34534"));

        when(empresaDao.listarTodasEmpresas()).thenReturn(empresasMock);

        List<Empresa> result = empresaService.listarTodasEmpresas();

        verify(empresaDao).listarTodasEmpresas();
        assert empresasMock == result;
    }

    @Test
    void testObterEmpresaPorId() throws SQLException {
        Integer idEmpresa = 1;
        Empresa empresaMock = new Empresa(
                "empresa 2",
                "54321",
                "empresa2@gmail.com",
                "empresa 2 descrição",
                "argentina",
                "98765",
                "34534");

        when(empresaDao.buscarEmpresaPorId(idEmpresa)).thenReturn(empresaMock);

        Empresa result = empresaService.obterEmpresaPorId(idEmpresa);

        verify(empresaDao).buscarEmpresaPorId(idEmpresa);
        assert empresaMock == result;
    }

    @Test
    void testAdicionarEmpresa() throws SQLException {
        Empresa empresaMock = new Empresa(
                "empresa 2",
                "54321",
                "empresa2@gmail.com",
                "empresa 2 descrição",
                "argentina",
                "98765",
                "34534");

        empresaService.adicionarEmpresa(empresaMock);

        verify(empresaDao).adicionarEmpresa(empresaMock);
    }

    @Test
    void testAtualizarEmpresa() throws SQLException {
        Integer idEmpresa = 1;
        Empresa empresaMock = new Empresa(
                "empresa 2",
                "54321",
                "empresa2@gmail.com",
                "empresa 2 descrição",
                "argentina",
                "98765",
                "34534");
        empresaMock.setId(idEmpresa);

        when(empresaDao.buscarEmpresaPorId(idEmpresa)).thenReturn(empresaMock);

        empresaService.atualizarEmpresa(empresaMock);

        verify(empresaDao).buscarEmpresaPorId(idEmpresa);
        verify(empresaDao).atualizarEmpresa(empresaMock);
    }

    @Test
    void testExcluirEmpresa() throws SQLException {
        Integer idEmpresa = 1;
        Empresa empresaMock = new Empresa(
                "empresa 2",
                "54321",
                "empresa2@gmail.com",
                "empresa 2 descrição",
                "argentina",
                "98765",
                "34534"
        );
        empresaMock.setId(idEmpresa);

        when(empresaDao.buscarEmpresaPorId(idEmpresa)).thenReturn(empresaMock);

        empresaService.excluirEmpresa(idEmpresa);

        verify(empresaDao).buscarEmpresaPorId(idEmpresa);
        verify(empresaDao).excluirEmpresa(idEmpresa);
    }

}
