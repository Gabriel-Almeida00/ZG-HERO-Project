package linketinder.controller.vaga


import linketinder.dao.vaga.VagaDao
import linketinder.db.ConfigDatabase
import linketinder.db.IDatabaseConnection
import linketinder.db.factory.DatabaseFactory
import linketinder.db.factory.IDatabaseConnectionFactory
import linketinder.model.Vaga
import linketinder.model.dto.VagaDTO
import linketinder.service.vaga.VagaService
import linketinder.servlet.ServletGet

import linketinder.servlet.ServletUtils

import javax.servlet.annotation.WebServlet
import javax.servlet.http.HttpServlet
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

@WebServlet(name = "VagaController", urlPatterns = "/vaga/*")
class VagaController extends HttpServlet {
    private ServletUtils servletUtils = new ServletUtils()
    private ServletGet servletGet = new ServletGet()

    ConfigDatabase configDatabase = new ConfigDatabase()
    DatabaseFactory databaseFactory = new DatabaseFactory()
    IDatabaseConnectionFactory factory = databaseFactory.createConnectionFactory(configDatabase)
    IDatabaseConnection connection = factory.createConnection()
    VagaDao vagaDao = new VagaDao(connection)
    VagaService vagaService = new VagaService(vagaDao)

    VagaController() {}

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) {
        String pathInfo = request.getPathInfo()
        if (pathInfo == null) {
            List<VagaDTO> vagas = this.vagaService.listarTodasVagas()
            servletGet.methodGet(response, vagas)
        } else {
            int idEmpresa = this.servletUtils.pegarIdDaUrl(request)
            List<VagaDTO> vagas = this.vagaService.listarVagasDaEmpresa(idEmpresa)
            servletGet.methodGet(response, vagas)
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) {
        try {
            Vaga vaga = this.servletUtils.parseObjectFromRequest(request, Vaga.class)
            this.vagaService.adicionarVaga(vaga)

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
            this.vagaService.atualizarVaga(vaga)

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
