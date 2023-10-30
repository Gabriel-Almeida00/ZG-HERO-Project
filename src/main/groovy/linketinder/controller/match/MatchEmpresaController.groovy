package linketinder.controller.match

import linketinder.dao.match.MatchDao
import linketinder.db.ConfigDatabase
import linketinder.db.IDatabaseConnection
import linketinder.db.factory.DatabaseFactory
import linketinder.db.factory.IDatabaseConnectionFactory
import linketinder.model.dto.MatchCandidatoDTO
import linketinder.service.match.MatchService
import linketinder.servlet.ServletResponse
import linketinder.servlet.ServletUtils

import javax.servlet.annotation.WebServlet
import javax.servlet.http.HttpServlet
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

@WebServlet(name = "MatchEmpresaController", urlPatterns = "/match/empresa/*")
class MatchEmpresaController extends HttpServlet {
    private ServletUtils servletUtils = new ServletUtils()
    private ServletResponse servletResponse = new ServletResponse()

    ConfigDatabase configDatabase = new ConfigDatabase()
    DatabaseFactory databaseFactory = new DatabaseFactory()
    IDatabaseConnectionFactory factory = databaseFactory.createConnectionFactory(configDatabase)
    IDatabaseConnection connection = factory.createConnection()
    MatchDao matchDao = new MatchDao(connection)
    MatchService matchService = new MatchService(matchDao)

    MatchEmpresaController() {}

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) {
        int idEmpresa = this.servletUtils.pegarIdDaUrl(request)
        List<MatchCandidatoDTO> candidatos = this.matchService.encontrarMatchesPelaEmpresa(idEmpresa)
        servletResponse.methodGet(response, candidatos)
    }
}
