package linketinder.controller.match

import linketinder.dao.match.MatchDao
import linketinder.db.ConfigDatabase
import linketinder.db.IDatabaseConnection
import linketinder.db.factory.DatabaseFactory
import linketinder.db.factory.IDatabaseConnectionFactory
import linketinder.model.dto.MatchEmpresaDTO
import linketinder.service.match.MatchService
import linketinder.servlet.ServletResponse
import linketinder.servlet.ServletUtils

import javax.servlet.annotation.WebServlet
import javax.servlet.http.HttpServlet
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

@WebServlet(name = "MatchCandidatoController", urlPatterns = "/match/candidato/*")
class MatchCandidatoController extends HttpServlet {
    private ServletUtils servletUtils = new ServletUtils()
    private ServletResponse servletResponse = new ServletResponse()

    ConfigDatabase configDatabase = new ConfigDatabase()
    DatabaseFactory databaseFactory = new DatabaseFactory()
    IDatabaseConnectionFactory factory = databaseFactory.createConnectionFactory(configDatabase)
    IDatabaseConnection connection = factory.createConnection()
    MatchDao matchDao = new MatchDao(connection)
    MatchService matchService = new MatchService(matchDao)

    MatchCandidatoController() {}

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) {
        int idCandidato = this.servletUtils.pegarIdDaUrl(request)
        List<MatchEmpresaDTO> empresas = this.matchService.encontrarMatchesPeloCandidato(idCandidato)
        servletResponse.methodGet(response, empresas)
    }
}
