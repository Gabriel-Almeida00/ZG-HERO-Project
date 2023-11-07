package linketinder.controller.vaga


import linketinder.dao.vaga.VagaDao
import linketinder.db.ConfigDatabase
import linketinder.db.IDatabaseConnection
import linketinder.db.factory.DatabaseFactory
import linketinder.db.factory.IDatabaseConnectionFactory
import linketinder.model.Vaga
import linketinder.service.vaga.VagaService
import linketinder.servlet.ServletResponse
import linketinder.servlet.ServletUtils

import javax.servlet.annotation.WebServlet
import javax.servlet.http.HttpServlet
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

@WebServlet(name = "VagasController", urlPatterns = "/empresa/vaga/*")
class VagasController extends HttpServlet {
    private ServletUtils servletUtils = new ServletUtils()
    private ServletResponse servletResponse = new ServletResponse()

    ConfigDatabase configDatabase = new ConfigDatabase()
    DatabaseFactory databaseFactory = new DatabaseFactory()
    IDatabaseConnectionFactory factory = databaseFactory.createConnectionFactory(configDatabase)
    IDatabaseConnection connection = factory.createConnection()
    VagaDao vagaDao = new VagaDao(connection)
    VagaService vagaService = new VagaService(vagaDao)

    VagasController() {}

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) {
        int idVaga = this.servletUtils.pegarIdDaUrl(request)
        Vaga vagas = this.vagaService.buscarVagaPorId(idVaga)
        servletResponse.methodGet(response, vagas)
    }
}
