package linketinder.controller.vaga


import linketinder.dao.vaga.VagaDao
import linketinder.db.ConfigDatabase
import linketinder.db.IDatabaseConnection
import linketinder.db.factory.DatabaseFactory
import linketinder.db.factory.IDatabaseConnectionFactory
import linketinder.model.Vaga
import linketinder.model.dto.VagaDTO
import linketinder.service.vaga.VagaService
import linketinder.servlet.ServletResponse
import linketinder.servlet.ServletUtils

import javax.servlet.annotation.WebServlet
import javax.servlet.http.HttpServlet
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

@WebServlet(name = "VagaController", urlPatterns = "/vaga/*")
class VagaController extends HttpServlet {
    private ServletUtils servletUtils = new ServletUtils()
    private ServletResponse servletResponse = new ServletResponse()

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
            servletResponse.methodGet(response, vagas)
        } else {
            int idEmpresa = this.servletUtils.pegarIdDaUrl(request)
            List<Vaga> vagas = this.vagaService.listarVagasDaEmpresa(idEmpresa)
            servletResponse.methodGet(response, vagas)
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) {
        servletResponse.methodPost(response, () -> {
            Vaga vaga = this.servletUtils.parseObjectFromRequest(request, Vaga.class)
            this.vagaService.adicionarVaga(vaga)
        })
    }

    @Override
    protected void doPut(HttpServletRequest request, HttpServletResponse response) {
        servletResponse.methodPut(response, () -> {
            int idVaga = this.servletUtils.pegarIdDaUrl(request)
            Vaga vaga = this.servletUtils.parseObjectFromRequest(request, Vaga.class)

            vaga.setId(idVaga)
            this.vagaService.atualizarVaga(vaga)
        })
    }

    @Override
    protected void doDelete(HttpServletRequest request, HttpServletResponse response) {
        servletResponse.methodDelete(response, () -> {
            int idVaga = this.servletUtils.pegarIdDaUrl(request)
            this.vagaService.excluirVaga(idVaga)
        })
    }
}
