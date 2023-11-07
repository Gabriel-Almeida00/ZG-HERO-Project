package linketinder.controller.candidato.experiencia


import linketinder.dao.candidato.CandidatoDao
import linketinder.dao.candidato.ExperienciaDao
import linketinder.db.ConfigDatabase
import linketinder.db.IDatabaseConnection
import linketinder.db.factory.DatabaseFactory
import linketinder.db.factory.IDatabaseConnectionFactory
import linketinder.model.Experiencia
import linketinder.service.candidato.CandidatoExperienciaService
import linketinder.servlet.ServletResponse
import linketinder.servlet.ServletUtils

import javax.servlet.annotation.WebServlet
import javax.servlet.http.HttpServlet
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

@WebServlet(name = "CandidatoExperienciasController", urlPatterns = "/candidato/experiencia/*")
class CandidatoExperienciasController extends HttpServlet {
    private ServletUtils servletUtils = new ServletUtils()
    private ServletResponse servletResponse = new ServletResponse()

    ConfigDatabase configDatabase = new ConfigDatabase()
    DatabaseFactory databaseFactory = new DatabaseFactory()
    IDatabaseConnectionFactory factory = databaseFactory.createConnectionFactory(configDatabase)
    IDatabaseConnection connection = factory.createConnection()
    CandidatoDao candidatoDao = new CandidatoDao(connection)
    ExperienciaDao experienciaDao = new ExperienciaDao(connection, candidatoDao)
    CandidatoExperienciaService candidatoExperienciaService = new CandidatoExperienciaService(experienciaDao)

    CandidatoExperienciasController() {}


    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) {
        int idExperiencia = servletUtils.pegarIdDaUrl(request)
        Experiencia experiencia = candidatoExperienciaService.buscarExperienciaPorId(idExperiencia)
        servletResponse.methodGet(response, experiencia)
    }
}
