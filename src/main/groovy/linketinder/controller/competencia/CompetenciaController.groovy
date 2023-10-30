package linketinder.controller.competencia


import linketinder.dao.competencia.CompetenciaDao
import linketinder.db.ConfigDatabase
import linketinder.db.IDatabaseConnection
import linketinder.db.factory.DatabaseFactory
import linketinder.db.factory.IDatabaseConnectionFactory
import linketinder.model.Competencia
import linketinder.service.competencia.CompetenciaService
import linketinder.servlet.ServletResponse
import linketinder.servlet.ServletUtils

import javax.servlet.annotation.WebServlet
import javax.servlet.http.HttpServlet
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

@WebServlet(name = "CompetenciaController", urlPatterns = "/competencia/*")
class CompetenciaController extends HttpServlet {
    private ServletUtils servletUtils = new ServletUtils()
    private ServletResponse servletResponse = new ServletResponse()

    ConfigDatabase configDatabase = new ConfigDatabase()
    DatabaseFactory databaseFactory = new DatabaseFactory()
    IDatabaseConnectionFactory factory = databaseFactory.createConnectionFactory(configDatabase)
    IDatabaseConnection connection = factory.createConnection()
    CompetenciaDao competenciaDao = new CompetenciaDao(connection)
    CompetenciaService competenciaService = new CompetenciaService(competenciaDao)

    CompetenciaController() {}

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) {
        String pathInfo = request.getPathInfo()
        if (pathInfo == null) {
            List<Competencia> competencias = this.competenciaService.listarCompetencias()
            servletResponse.methodGet(response, competencias)
        } else {
            int idCompetencia = this.servletUtils.pegarIdDaUrl(request)
            Competencia competencia = this.competenciaService.buscarCompetenciaPorId(idCompetencia)
            servletResponse.methodGet(response, competencia)
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) {
        servletResponse.methodPost(response, () -> {
            Competencia competencia = servletUtils.parseObjectFromRequest(request, Competencia.class)
            this.competenciaService.adicionarCompetencia(competencia)
        })
    }

    @Override
    protected void doPut(HttpServletRequest request, HttpServletResponse response) {
        servletResponse.methodPut(response, () -> {
            int competenciaId = this.servletUtils.pegarIdDaUrl(request)
            Competencia competencia = this.servletUtils.parseObjectFromRequest(request, Competencia.class)

            competencia.setId(competenciaId)
            this.competenciaService.atualizarCompetencia(competencia)
        })
    }

    @Override
    protected void doDelete(HttpServletRequest request, HttpServletResponse response) {
        servletResponse.methodDelete(response, () -> {
            int idCompetencia = servletUtils.pegarIdDaUrl(request)
            this.competenciaService.excluirCompetencia(idCompetencia)
        })
    }
}
