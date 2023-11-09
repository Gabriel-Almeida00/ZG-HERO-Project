package linketinder.controller.empresa


import linketinder.dao.empresa.EmpresaDao
import linketinder.db.ConfigDatabase
import linketinder.db.IDatabaseConnection
import linketinder.db.factory.DatabaseFactory
import linketinder.db.factory.IDatabaseConnectionFactory
import linketinder.model.Empresa
import linketinder.service.empresa.EmpresaService
import linketinder.servlet.ServletResponse
import linketinder.servlet.ServletUtils

import javax.servlet.annotation.WebServlet
import javax.servlet.http.HttpServlet
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

@WebServlet(name = "EmpresaController", urlPatterns = "/empresa/*")
class EmpresaController extends HttpServlet {
    private ServletUtils servletUtils = new ServletUtils()
    private ServletResponse servletResponse = new ServletResponse()

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
            servletResponse.methodGet(response, empresas)
        } else {
            int idEmpresa = this.servletUtils.pegarIdDaUrl(request)
            Empresa empresa = this.empresaService.obterEmpresaPorId(idEmpresa)
            servletResponse.methodGet(response, empresa)
        }
    }



    @Override
    protected void doPut(HttpServletRequest request, HttpServletResponse response) {
        servletResponse.methodPut(response, () -> {
            int empresaId = this.servletUtils.pegarIdDaUrl(request)
            Empresa empresa = this.servletUtils.parseObjectFromRequest(request, Empresa.class)

            empresa.setId(empresaId)
            this.empresaService.atualizarEmpresa(empresa)
        })
    }

    @Override
    protected void doDelete(HttpServletRequest request, HttpServletResponse response) {
        servletResponse.methodDelete(response, () -> {
            int empresaId = this.servletUtils.pegarIdDaUrl(request)
            this.empresaService.excluirEmpresa(empresaId)
        })
    }
}
