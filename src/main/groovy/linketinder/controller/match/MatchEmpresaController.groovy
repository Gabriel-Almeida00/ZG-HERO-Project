package linketinder.controller.match

import com.google.gson.Gson
import linketinder.dao.match.MatchDao
import linketinder.db.ConfigDatabase
import linketinder.db.IDatabaseConnection
import linketinder.db.factory.DatabaseFactory
import linketinder.db.factory.IDatabaseConnectionFactory
import linketinder.model.Competencia
import linketinder.model.dto.MatchCandidatoDTO
import linketinder.model.dto.MatchEmpresaDTO
import linketinder.service.match.IMatchService
import linketinder.service.match.MatchService
import linketinder.utils.servlet.ServletResponseUtils
import linketinder.utils.servlet.ServletUtils

import javax.servlet.annotation.WebServlet
import javax.servlet.http.HttpServlet
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

@WebServlet(name = "MatchEmpresaController", urlPatterns = "/match/empresa/*")
class MatchEmpresaController extends HttpServlet {
    private Gson gson = new Gson()
    private ServletUtils servletUtils = new ServletUtils()
    private ServletResponseUtils servletResponseUtils = new ServletResponseUtils()

    ConfigDatabase configDatabase = new ConfigDatabase()
    DatabaseFactory databaseFactory = new DatabaseFactory()
    IDatabaseConnectionFactory factory = databaseFactory.createConnectionFactory(configDatabase)
    IDatabaseConnection connection = factory.createConnection()
    MatchDao matchDao = new MatchDao(connection)
    MatchService matchService = new MatchService(matchDao)

    MatchEmpresaController() {}

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) {
        try {
            int idEmpresa = this.servletUtils.pegarIdDaUrl(request)
            List<MatchCandidatoDTO> candidatos = this.matchService.encontrarMatchesPelaEmpresa(idEmpresa)
            String json = this.gson.toJson(candidatos)
            this.servletResponseUtils.writeResponse(response, json)

        } catch (Exception e) {
            this.servletResponseUtils.writeErrorResponse(response, HttpServletResponse.SC_BAD_REQUEST, e.getMessage())
        }
    }
}
