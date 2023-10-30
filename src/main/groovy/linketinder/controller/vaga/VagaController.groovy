package linketinder.controller.vaga

import com.google.gson.Gson
import linketinder.dao.vaga.VagaDao
import linketinder.db.ConfigDatabase
import linketinder.db.IDatabaseConnection
import linketinder.db.factory.DatabaseFactory
import linketinder.db.factory.IDatabaseConnectionFactory
import linketinder.model.Vaga
import linketinder.model.dto.VagaDTO
import linketinder.service.vaga.VagaService
import linketinder.utils.servlet.ServletResponseUtils
import linketinder.utils.servlet.ServletUtils

import javax.servlet.annotation.WebServlet
import javax.servlet.http.HttpServlet
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

@WebServlet(name = "VagaController", urlPatterns = "/vaga/*")
class VagaController extends HttpServlet {
    private Gson gson = new Gson()
    private ServletUtils servletUtils = new ServletUtils()
    private ServletResponseUtils servletResponseUtils = new ServletResponseUtils()

    ConfigDatabase configDatabase = new ConfigDatabase()
    DatabaseFactory databaseFactory = new DatabaseFactory()
    IDatabaseConnectionFactory factory = databaseFactory.createConnectionFactory(configDatabase)
    IDatabaseConnection connection = factory.createConnection()
    VagaDao vagaDao = new VagaDao(connection)
    VagaService vagaService = new VagaService(vagaDao)

    VagaController() {}

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) {
        try {
            String pathInfo = request.getPathInfo()
            if (pathInfo == null ) {
                List<VagaDTO> vagas = this.vagaService.listarTodasVagas()
                String json = this.gson.toJson(vagas)
                this.servletResponseUtils.writeResponse(response, json)
            } else {
                int idEmpresa = this.servletUtils.pegarIdDaUrl(request)
                List<VagaDTO> vagas = this.vagaService.listarVagasDaEmpresa(idEmpresa)
                String json = gson.toJson(vagas)
                servletResponseUtils.writeResponse(response, json)
            }
        } catch (Exception e) {
            this.servletResponseUtils.writeErrorResponse(response, HttpServletResponse.SC_BAD_REQUEST, e.getMessage())
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) {
        try {
            Vaga vaga = this.servletUtils.parseObjectFromRequest(request, Vaga.class)
            this. vagaService.adicionarVaga(vaga)

            servletResponseUtils.configureResponse(response)
            response.setStatus(HttpServletResponse.SC_CREATED)
        } catch (Exception e) {
            this.servletResponseUtils.writeErrorResponse(response, HttpServletResponse.SC_BAD_REQUEST, e.getMessage())
        }
    }

    @Override
    protected void doPut(HttpServletRequest request, HttpServletResponse response) {
        try {
            int idVaga = this.servletUtils.pegarIdDaUrl(request)
            Vaga vaga = this.servletUtils.parseObjectFromRequest(request, Vaga.class)

            vaga.setId(idVaga)
            this. vagaService.atualizarVaga(vaga)

            this.servletResponseUtils.configureResponse(response)
            response.setStatus(HttpServletResponse.SC_OK)
        } catch (Exception e) {
            this.servletResponseUtils.writeErrorResponse(response, HttpServletResponse.SC_BAD_REQUEST, e.getMessage())
        }
    }

    @Override
    protected void doDelete(HttpServletRequest request, HttpServletResponse response) {
        try {
            int idVaga = this.servletUtils.pegarIdDaUrl(request)
            this.vagaService.excluirVaga(idVaga)

            response.setStatus(HttpServletResponse.SC_NO_CONTENT)
        } catch (Exception e) {
            this.servletResponseUtils.writeErrorResponse(response, HttpServletResponse.SC_BAD_REQUEST, e.getMessage())
        }
    }
}
