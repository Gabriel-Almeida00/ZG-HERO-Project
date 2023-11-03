package linketinder.controller.candidato

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

@WebServlet(name = "CandidatoCompetenciasController", urlPatterns = "/candidato/competencia/*")
class CandidatoCompetenciasController extends HttpServlet {
    private ServletUtils servletUtils = new ServletUtils()
    private ServletResponse servletResponse = new ServletResponse()

    ConfigDatabase configDatabase = new ConfigDatabase()
    DatabaseFactory databaseFactory = new DatabaseFactory()
    IDatabaseConnectionFactory factory = databaseFactory.createConnectionFactory(configDatabase)
    IDatabaseConnection connection = factory.createConnection()

    CandidatoDao candidatoDao = new CandidatoDao(connection)
    CandidatoCompetenciaDao dao = new CandidatoCompetenciaDao(connection, candidatoDao)
    CandidatoCompetenciaService candidatoCompetenciaService = new CandidatoCompetenciaService(dao)

    CandidatoCompetenciasController() {
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) {
        int id = servletUtils.pegarIdDaUrl(request)
        CompetenciaDTO competencia = candidatoCompetenciaService.buscarCompetenciaDoCandidatoPorId(id)
        servletResponse.methodGet(response, competencia)
    }
}
