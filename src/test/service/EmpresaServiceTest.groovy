package test.service

import dao.candidato.ICandidatoDao
import dao.curtida.ICurtidaDao
import dao.empresa.IEmpresaDao
import entity.Candidato
import entity.Empresa
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import static org.mockito.Mockito.*;
import service.EmpresaService

import java.sql.SQLException


class EmpresaServiceTest {

    private IEmpresaDao empresaDao
    private ICandidatoDao candidatoDao
    private ICurtidaDao curtidaDao
    private EmpresaService empresaService


    @BeforeEach
    void setup() {
        empresaDao = mock(IEmpresaDao.class)
        candidatoDao = mock(ICandidatoDao.class)
        curtidaDao = mock(ICurtidaDao.class)
        empresaService = new EmpresaService(empresaDao, candidatoDao, curtidaDao)
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

    @Test
    void testCurtirCandidato_ComSucesso() throws SQLException {
        Integer idCandidato = 1;
        Integer idEmpresa = 2;

        Candidato candidato = new Candidato(
                "João",
                "Silva",
                new Date(System.currentTimeMillis()),
                "joao@example.com",
                "12345678900",
                "Brasil",
                "12345-678",
                "Descrição do candidato",
                "senha123"
        )
        candidato.setId(idCandidato);

        Empresa empresa = new Empresa(
                "empresa 2",
                "54321",
                "empresa2@gmail.com",
                "empresa 2 descrição",
                "argentina",
                "98765",
                "34534");
        empresa.setId(idEmpresa);

        when(candidatoDao.obterCandidatoPorId(idCandidato)).thenReturn(candidato);
        when(empresaDao.buscarEmpresaPorId(idEmpresa)).thenReturn(empresa);

        empresaService.curtirCandidato(idCandidato, idEmpresa);

        verify(curtidaDao).curtirCandidato(idCandidato, idEmpresa);
    }

}
