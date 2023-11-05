package linketinder.controller.candidato.competencia

import linketinder.dao.candidato.CandidatoCompetenciaDao
import linketinder.dao.candidato.CandidatoDao
import linketinder.db.ConfigDatabase
import linketinder.db.IDatabaseConnection
import linketinder.db.factory.DatabaseFactory
import linketinder.db.factory.IDatabaseConnectionFactory
import linketinder.model.CandidatoCompetencia
import linketinder.model.dto.CompetenciaDTO
import linketinder.service.candidato.CandidatoCompetenciaService
import linketinder.servlet.ServletResponse
import linketinder.servlet.ServletUtils

import javax.servlet.annotation.WebServlet
import javax.servlet.http.HttpServlet
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

@WebServlet(name = "CandidatoCompetenciaController", urlPatterns = "/candidatoCompetencia/*")
class CandidatoCompetenciaController extends HttpServlet {
    private ServletUtils servletUtils = new ServletUtils()
    private ServletResponse servletResponse = new ServletResponse()

    ConfigDatabase configDatabase = new ConfigDatabase()
    DatabaseFactory databaseFactory = new DatabaseFactory()
    IDatabaseConnectionFactory factory = databaseFactory.createConnectionFactory(configDatabase)
    IDatabaseConnection connection = factory.createConnection()

    CandidatoDao candidatoDao = new CandidatoDao(connection)
    CandidatoCompetenciaDao dao = new CandidatoCompetenciaDao(connection, candidatoDao)
    CandidatoCompetenciaService candidatoCompetenciaService = new CandidatoCompetenciaService(dao)

    CandidatoCompetenciaController() {}

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) {
        int idCandidato = servletUtils.pegarIdDaUrl(request)
        List<CompetenciaDTO> candidatoCompetencias = candidatoCompetenciaService.listarCompetenciasPorCandidato(idCandidato)
        servletResponse.methodGet(response, candidatoCompetencias)
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) {
        servletResponse.methodPost(response, () -> {
            CandidatoCompetencia candidatoCompetencia = servletUtils.parseObjectFromRequest(request, CandidatoCompetencia.class)
            this.candidatoCompetenciaService.adicionarCandidatoCompetencia(candidatoCompetencia)
        })
    }


    @Override
    protected void doPut(HttpServletRequest request, HttpServletResponse response) {
        servletResponse.methodPut(response, () -> {
            int competenciaId = this.servletUtils.pegarIdDaUrl(request)
            CandidatoCompetencia candidatoCompetencia = this.servletUtils.parseObjectFromRequest(request, CandidatoCompetencia.class)

            candidatoCompetencia.setId(competenciaId)
            this.candidatoCompetenciaService.atualizarNivelCompetenciaCandidato(candidatoCompetencia)
        })
    }


    @Override
    protected void doDelete(HttpServletRequest request, HttpServletResponse response) {
        servletResponse.methodDelete(response , () -> {
            int idCompetencia = servletUtils.pegarIdDaUrl(request)
            this.candidatoCompetenciaService.excluirCompetenciaCandidato(idCompetencia)
        })
    }
}
