package linketinder.controller.empresa


import linketinder.dao.empresa.EmpresaDao
import linketinder.db.ConfigDatabase
import linketinder.db.IDatabaseConnection
import linketinder.db.factory.DatabaseFactory
import linketinder.db.factory.IDatabaseConnectionFactory
import linketinder.model.Empresa
import linketinder.service.empresa.EmpresaService
import linketinder.servlet.ServletGet
import linketinder.servlet.ServletResponseUtils
import linketinder.servlet.ServletUtils

import javax.servlet.annotation.WebServlet
import javax.servlet.http.HttpServlet
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

@WebServlet(name = "EmpresaController", urlPatterns = "/empresa/*")
class EmpresaController extends HttpServlet {
    private ServletUtils servletUtils = new ServletUtils()
    private ServletResponseUtils servletResponseUtils = new ServletResponseUtils()
    private ServletGet servletGet = new ServletGet()

    ConfigDatabase configDatabase = new ConfigDatabase()
    DatabaseFactory databaseFactory = new DatabaseFactory()
    IDatabaseConnectionFactory factory = databaseFactory.createConnectionFactory(configDatabase)
    IDatabaseConnection connection = factory.createConnection()
    EmpresaDao empresaDao = new EmpresaDao(connection)
    EmpresaService empresaService = new EmpresaService(empresaDao)

    EmpresaController() {}

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) {
        String pathInfo = request.getPathInfo()
        if (pathInfo == null) {
            List<Empresa> empresas = this.empresaService.listarTodasEmpresas()
            servletGet.methodGet(response, empresas)
        } else {
            int idEmpresa = this.servletUtils.pegarIdDaUrl(request)
            Empresa empresa = this.empresaService.obterEmpresaPorId(idEmpresa)
            servletGet.methodGet(response, empresa)
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) {
        try {
            Empresa empresa = servletUtils.parseObjectFromRequest(request, Empresa.class)
            this.empresaService.adicionarEmpresa(empresa)

            servletResponseUtils.configureResponse(response)
            response.setStatus(HttpServletResponse.SC_CREATED)
        } catch (Exception e) {
            this.servletResponseUtils.writeErrorResponse(response, HttpServletResponse.SC_BAD_REQUEST, e.getMessage())
        }
    }

    @Override
    protected void doPut(HttpServletRequest request, HttpServletResponse response) {
        try {
            int empresaId = this.servletUtils.pegarIdDaUrl(request)
            Empresa empresa = this.servletUtils.parseObjectFromRequest(request, Empresa.class)

            empresa.setId(empresaId)
            this.empresaService.atualizarEmpresa(empresa)

            this.servletResponseUtils.configureResponse(response)
            response.setStatus(HttpServletResponse.SC_OK)
        } catch (Exception e) {
            this.servletResponseUtils.writeErrorResponse(response, HttpServletResponse.SC_BAD_REQUEST, e.getMessage())
        }
    }

    @Override
    protected void doDelete(HttpServletRequest request, HttpServletResponse response) {
        try {
            int empresaId = this.servletUtils.pegarIdDaUrl(request)
            this.empresaService.excluirEmpresa(empresaId)

            response.setStatus(HttpServletResponse.SC_NO_CONTENT)
        } catch (Exception e) {
            this.servletResponseUtils.writeErrorResponse(response, HttpServletResponse.SC_BAD_REQUEST, e.getMessage())
        }
    }
}
