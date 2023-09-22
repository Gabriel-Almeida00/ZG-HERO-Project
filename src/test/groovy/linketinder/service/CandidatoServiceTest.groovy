package linketinder.service


import linketinder.dao.candidato.ICandidatoCompetenciaDao
import linketinder.dao.candidato.ICandidatoDao
import linketinder.dao.candidato.IExperienciaDao
import linketinder.dao.candidato.IFormacaoDao
import linketinder.dao.curtida.ICurtidaDao
import linketinder.dao.vaga.IVagaDao
import linketinder.entity.*
import linketinder.entity.dto.CandidatoDTO
import linketinder.entity.dto.CompetenciaDTO
import linketinder.entity.dto.EmpresaDTO
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

import java.sql.SQLException

import static org.mockito.Mockito.*

class CandidatoServiceTest {

    private ICandidatoDao candidatoDao
    private ICandidatoCompetenciaDao candidatoCompetenciaDao
    private IExperienciaDao experienciaDao
    private IFormacaoDao formacaoDao
    private IVagaDao vagaDao
    private ICurtidaDao curtidaDao
    private CandidatoService candidatoService

    @BeforeEach
    void setup() {
        candidatoDao = mock(ICandidatoDao.class)
        candidatoCompetenciaDao = mock(ICandidatoCompetenciaDao.class)
        experienciaDao = mock(IExperienciaDao.class)
        formacaoDao = mock(IFormacaoDao.class)
        vagaDao = mock(IVagaDao.class)
        curtidaDao = mock((ICurtidaDao.class))

        candidatoService = new CandidatoService(
                candidatoDao,
                candidatoCompetenciaDao,
                experienciaDao,
                formacaoDao,
                vagaDao,
                curtidaDao
        )
    }

    @Test
    void testListarCandidatos() throws SQLException {
        List<CandidatoDTO> candidatosMock = new ArrayList<>()
        candidatosMock.add(new CandidatoDTO(1, "Candidato 1", "Descrição 1", Arrays.asList("Competência 1", "Competência 2")))
        candidatosMock.add(new CandidatoDTO(2, "Candidato 2", "Descrição 2", Arrays.asList("Competência 3", "Competência 4")))

        when(candidatoDao.listarCandidatos()).thenReturn(candidatosMock)
        List<CandidatoDTO> resultado = candidatoService.listarCandidatos()

        verify(candidatoDao).listarCandidatos()
        assert candidatosMock == resultado
    }

    @Test
    void testObterCandidatoPorId() throws SQLException {
        Integer candidatoId = 1
        Candidato candidatoMock = new Candidato(
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
        when(candidatoDao.obterCandidatoPorId(candidatoId)).thenReturn(candidatoMock)
        Candidato result = candidatoService.obterCandidatoPorId(candidatoId)

        verify(candidatoDao).obterCandidatoPorId(candidatoId)
        assert candidatoMock == result
    }

    @Test
    void testCadastrarCandidato() throws SQLException {
        Candidato candidatoMock = new Candidato(
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

        candidatoService.cadastrarCandidato(candidatoMock)

        verify(candidatoDao).adicionarCandidato(candidatoMock)
    }

    @Test
    void testAtualizarCandidato() throws SQLException {

        Candidato candidatoMock = new Candidato(
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
        candidatoMock.setId(1)

        candidatoService.atualizarCandidato(candidatoMock)

        verify(candidatoDao).atualizarCandidato(candidatoMock)
    }

    @Test
    void testDeletarCandidato() throws SQLException {
        Candidato candidatoMock = new Candidato(
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
        Integer idCandidato = 1
        candidatoMock.setId(idCandidato)

        when(candidatoDao.obterCandidatoPorId(idCandidato))
                .thenReturn(candidatoMock)

        candidatoService.deletarCandidato(idCandidato)

        verify(candidatoDao).deletarCandidato(idCandidato)
    }

    @Test
    void testListarCompetenciasCandidato() throws SQLException {
        Candidato candidatoMock = new Candidato(
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
        Integer idCandidato = 1
        candidatoMock.setId(idCandidato)

        List<CompetenciaDTO> competenciasMock = new ArrayList<>()
        competenciasMock.add(new CompetenciaDTO(1, "Java", "Intermediároo"))
        competenciasMock.add(new CompetenciaDTO(2, "SQL", "Avançado"))

        when(candidatoDao.obterCandidatoPorId(candidatoMock.getId())).thenReturn(candidatoMock)

        when(candidatoCompetenciaDao.listarCompetenciasPorCandidato(candidatoMock.getId())).thenReturn(competenciasMock)

        List<CompetenciaDTO> result = candidatoService.listarCompetenciasPorCandidato(candidatoMock.getId())

        verify(candidatoCompetenciaDao).listarCompetenciasPorCandidato(candidatoMock.getId())

        assert competenciasMock.size() == result.size()
        assert competenciasMock == result
    }

    @Test
    void testAdicionarCandidatoCompetencia() throws SQLException {
        Integer idCandidato = 1
        Integer idCompetencia = 2
        Integer nivel = 3

        CandidatoCompetencia candidatoCompetencia = new CandidatoCompetencia(idCandidato, idCompetencia, nivel)

        doNothing().when(candidatoCompetenciaDao).adicionarCandidatoCompetencia(candidatoCompetencia)

        candidatoService.adicionarCandidatoCompetencia(candidatoCompetencia)

        verify(candidatoCompetenciaDao, times(1)).adicionarCandidatoCompetencia(candidatoCompetencia)
    }

    @Test
    void testAtualizarNivelCompetenciaCandidato() throws SQLException {
        Integer id = 1
        Integer idCompetencia = 1
        Integer novoNivel = 2

        CandidatoCompetencia candidatoCompetencia = new CandidatoCompetencia(1, idCompetencia, novoNivel)
        candidatoCompetencia.setId(id)

        CompetenciaDTO competenciasMock = new CompetenciaDTO(idCompetencia, "Java", "Avançado")
        competenciasMock.setId(idCompetencia)

        doNothing().when(candidatoCompetenciaDao).atualizarNivelCompetenciaCandidato(candidatoCompetencia)

        candidatoService.atualizarNivelCompetenciaCandidato(candidatoCompetencia)

        verify(candidatoCompetenciaDao, times(1)).atualizarNivelCompetenciaCandidato(candidatoCompetencia)
    }

    @Test
    void testExcluirCompetenciaCandidato() throws SQLException {
        Integer idCompetencia = 2

        CompetenciaDTO competenciasMock = new CompetenciaDTO(idCompetencia, "Java", "Básico")
        competenciasMock.setId(idCompetencia)

        doNothing().when(candidatoCompetenciaDao).excluirCompetenciaCandidato(idCompetencia)

        candidatoService.excluirCompetenciaCandidato(idCompetencia)

        verify(candidatoCompetenciaDao, times(1)).excluirCompetenciaCandidato(idCompetencia)
    }

    @Test
    void testAdicionarExperiencia() throws SQLException {
        Experiencia experienciaMock = mock(Experiencia.class)

        doNothing().when(experienciaDao).adicionarExperiencia(experienciaMock)

        candidatoService.adicionarExperiencia(experienciaMock)

        verify(experienciaDao, times(1)).adicionarExperiencia(experienciaMock)
    }

    @Test
    void testAtualizarExperiencia() throws SQLException {
        Experiencia experienciaMock = mock(Experiencia.class)

        doNothing().when(experienciaDao).atualizarExperiencia(experienciaMock)

        candidatoService.atualizarExperiencia(experienciaMock)

        verify(experienciaDao, times(1)).atualizarExperiencia(experienciaMock)
    }

    @Test
    void testListarExperienciasPorCandidato() throws SQLException {
        Integer idCandidato = 1
        Candidato candidatoMock = new Candidato(
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
        candidatoMock.setId(idCandidato)
        List<Experiencia> experienciasMock = new ArrayList<>()

        when(experienciaDao.listarExperienciasPorCandidato(idCandidato)).thenReturn(experienciasMock)
        when(candidatoDao.obterCandidatoPorId(idCandidato)).thenReturn(candidatoMock)

        List<Experiencia> result = candidatoService.listarExperienciasPorCandidato(idCandidato)

        assert experienciasMock == result
    }

    @Test
    void testExcluirExperiencia() throws SQLException {
        Integer idExperiencia = 1
        Integer idCandidato = 1
        Experiencia experienciaMock = new Experiencia(
                idCandidato,
                "dev junior",
                "tech global",
                1
        )

        when(experienciaDao.buscarExperienciaPorId(idExperiencia)).thenReturn(experienciaMock)

        candidatoService.excluirExperiencia(idExperiencia)

        verify(experienciaDao).excluirExperiencia(idExperiencia)
    }

    @Test
    void testAdicionarFormacao() throws SQLException {
        Formacao formacao = new Formacao(
                1,
                "Instituição",
                "Curso",
                2,
                "2023")

        doNothing().when(formacaoDao).adicionarFormacao(formacao)

        candidatoService.adicionarFormacao(formacao)

        verify(formacaoDao, times(1)).adicionarFormacao(formacao)
    }

    @Test
    void testAtualizarFormacao() throws SQLException {
        Formacao formacao = new Formacao(
                1,
                "Instituição",
                "Curso",
                1,
                "2023")

        doNothing().when(formacaoDao).atualizarFormacao(formacao)

        candidatoService.atualizarFormacao(formacao)

        verify(formacaoDao, times(1)).atualizarFormacao(formacao)
    }

    @Test
    void testExcluirFormacao() throws SQLException {
        Integer idFormacao = 1
        Integer idCandidato = 1

        Formacao formacaoMock = new Formacao(
                idCandidato,
                "fatec",
                "análise e desenvolvimento de sistemas",
                1,
                "2025"
        )

        when(formacaoDao.buscarFormacaoPorId(idFormacao)).thenReturn(formacaoMock)
        doNothing().when(formacaoDao).excluirFormacao(idFormacao)

        candidatoService.excluirFormacao(idFormacao)

        verify(formacaoDao, times(1)).excluirFormacao(idFormacao)
    }

    @Test
    void testListarFormacoesPorCandidato() throws SQLException {
        Integer idCandidato = 1

        Candidato candidatoMock = new Candidato(
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
        candidatoMock.setId(idCandidato)

        List<Formacao> formacoes = new ArrayList<>()
        formacoes.add(new Formacao(idCandidato, "Instituição 1", "Curso 1", 1, "Ano 2021"))
        formacoes.add(new Formacao(idCandidato, "Instituição 2", "Curso 2", 2, "Ano 2022"))

        when(candidatoDao.obterCandidatoPorId(idCandidato)).thenReturn(candidatoMock)
        when(formacaoDao.listarFormacoesPorCandidato(idCandidato)).thenReturn(formacoes)

        List<Formacao> result = candidatoService.listarFormacoesPorCandidato(idCandidato)

        verify(formacaoDao, times(1)).listarFormacoesPorCandidato(idCandidato)
        assert formacoes == result
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


        candidatoService.curtirVaga(idCandidato, idVaga)

        verify(curtidaDao).curtirVaga(idCandidato, idVaga)
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


        when(candidatoService.listarEmpresasQueCurtiramCandidato(id)).thenReturn(empresas)
        List<EmpresaDTO> empresasDTO = candidatoService.listarEmpresasQueCurtiramCandidato(id)


        verify(curtidaDao).listarEmpresasQueCurtiramCandidato(id)

        assert empresas == empresasDTO
    }
}
