package linketinder.service


import linketinder.dao.candidato.ICandidatoDao
import linketinder.dao.curtida.ICurtidaDao
import linketinder.dao.empresa.IEmpresaDao
import linketinder.entity.Candidato
import linketinder.entity.Empresa
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

import java.sql.SQLException

import static org.mockito.Mockito.*

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

        when(empresaDao.listarTodasEmpresas()).thenReturn(empresasMock)

        List<Empresa> result = empresaService.listarTodasEmpresas()

        verify(empresaDao).listarTodasEmpresas()
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

        when(empresaDao.buscarEmpresaPorId(idEmpresa)).thenReturn(empresaMock)

        Empresa result = empresaService.obterEmpresaPorId(idEmpresa)

        verify(empresaDao).buscarEmpresaPorId(idEmpresa)
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

        verify(empresaDao).adicionarEmpresa(empresaMock)
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

        verify(empresaDao).atualizarEmpresa(empresaMock)
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

        verify(empresaDao).excluirEmpresa(idEmpresa)
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

        when(candidatoDao.obterCandidatoPorId(idCandidato)).thenReturn(candidato)
        when(empresaDao.buscarEmpresaPorId(idEmpresa)).thenReturn(empresa)
        when(curtidaDao.verificaCurtidaDaEmpresa(idEmpresa, idCandidato)).thenReturn(null)

        curtidaDao.curtirCandidato(idCandidato, idEmpresa)

        verify(curtidaDao).curtirCandidato(idCandidato, idEmpresa)
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

        when(candidatoDao.obterCandidatoPorId(idCandidato)).thenReturn(candidato)
        when(empresaDao.buscarEmpresaPorId(idEmpresa)).thenReturn(empresa)
        when(curtidaDao.verificaCurtidaDoCandidato(idCandidato)).thenReturn(idVaga)

        empresaService.curtirCandidato(idCandidato, idEmpresa)

        verify(curtidaDao).AtualizarCurtidaComIdEmpresa(idVaga, idEmpresa, idCandidato)
    }
}

