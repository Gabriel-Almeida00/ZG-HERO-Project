package linketinder.controller.vaga.competencia


import linketinder.dao.vaga.VagaCompetenciaDao
import linketinder.dao.vaga.VagaDao
import linketinder.db.ConfigDatabase
import linketinder.db.IDatabaseConnection
import linketinder.db.factory.DatabaseFactory
import linketinder.db.factory.IDatabaseConnectionFactory
import linketinder.model.VagaCompetencia
import linketinder.model.dto.CompetenciaDTO
import linketinder.service.vaga.VagaCompetenciaService
import linketinder.servlet.ServletResponse
import linketinder.servlet.ServletUtils

import javax.servlet.annotation.WebServlet
import javax.servlet.http.HttpServlet
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

@WebServlet(name = "VagaCompetenciasController", urlPatterns = "/vaga/competencia/*")
class VagaCompetenciasController extends HttpServlet {
    private ServletUtils servletUtils = new ServletUtils()
    private ServletResponse servletResponse = new ServletResponse()

    ConfigDatabase configDatabase = new ConfigDatabase()
    DatabaseFactory databaseFactory = new DatabaseFactory()
    IDatabaseConnectionFactory factory = databaseFactory.createConnectionFactory(configDatabase)
    IDatabaseConnection connection = factory.createConnection()
    VagaDao vagaDao = new VagaDao(connection)
    VagaCompetenciaDao vagaCompetenciaDao = new VagaCompetenciaDao(connection, vagaDao)
    VagaCompetenciaService vagaCompetenciaService = new VagaCompetenciaService(vagaCompetenciaDao)

    VagaCompetenciasController() {}

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) {
        int idCompetencia = this.servletUtils.pegarIdDaUrl(request)
        VagaCompetencia competencia = this.vagaCompetenciaService.buscarCompetenciaDaVagaporId(idCompetencia)
        servletResponse.methodGet(response, competencia)
    }

}
