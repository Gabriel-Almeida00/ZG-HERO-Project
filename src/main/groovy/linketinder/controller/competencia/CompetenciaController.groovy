package linketinder.controller.competencia

import com.google.gson.Gson
import linketinder.dao.competencia.CompetenciaDao
import linketinder.db.ConfigDatabase
import linketinder.db.IDatabaseConnection
import linketinder.db.factory.DatabaseFactory
import linketinder.db.factory.IDatabaseConnectionFactory
import linketinder.model.Competencia
import linketinder.service.competencia.CompetenciaService
import linketinder.servlet.ServletGet
import linketinder.servlet.ServletResponseUtils
import linketinder.servlet.ServletUtils

import javax.servlet.annotation.WebServlet
import javax.servlet.http.HttpServlet
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

@WebServlet(name = "CompetenciaController", urlPatterns = "/competencia/*")
class CompetenciaController extends HttpServlet {
    private Gson gson = new Gson()
    private ServletUtils servletUtils = new ServletUtils()
    private ServletResponseUtils servletResponseUtils = new ServletResponseUtils()
    private ServletGet servletGet = new ServletGet()

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
            if (pathInfo == null ) {
                List<Competencia> competencias = this.competenciaService.listarCompetencias()
                servletGet.methodGet(response, competencias)
            } else {
                int idCompetencia = this.servletUtils.pegarIdDaUrl(request)
                Competencia competencia = this.competenciaService.buscarCompetenciaPorId(idCompetencia)
                servletGet.methodGet(response, competencia)
            }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) {
        try {
            Competencia competencia = servletUtils.parseObjectFromRequest(request, Competencia.class)
            this.competenciaService.adicionarCompetencia(competencia)

            servletResponseUtils.configureResponse(response)
            response.setStatus(HttpServletResponse.SC_CREATED)
        } catch (Exception e) {
            this.servletResponseUtils.writeErrorResponse(response, HttpServletResponse.SC_BAD_REQUEST,e.getMessage())
        }
    }

    @Override
    protected void doPut(HttpServletRequest request, HttpServletResponse response) {
        try {
            int competenciaId = this.servletUtils.pegarIdDaUrl(request)
            Competencia competencia = this.servletUtils.parseObjectFromRequest(request, Competencia.class)

            competencia.setId(competenciaId)
            this.competenciaService.atualizarCompetencia(competencia)

            this.servletResponseUtils.configureResponse(response)
            response.setStatus(HttpServletResponse.SC_OK)
        } catch (Exception e) {
            this.servletResponseUtils.writeErrorResponse(response, HttpServletResponse.SC_BAD_REQUEST,e.getMessage())
        }
    }

    @Override
    protected void doDelete(HttpServletRequest request, HttpServletResponse response) {
        try {
            int idCompetencia = servletUtils.pegarIdDaUrl(request)
            this.competenciaService.excluirCompetencia(idCompetencia)

            response.setStatus(HttpServletResponse.SC_NO_CONTENT)
        } catch (Exception e) {
            this.servletResponseUtils.writeErrorResponse(response, HttpServletResponse.SC_BAD_REQUEST,e.getMessage())
        }
    }
}
