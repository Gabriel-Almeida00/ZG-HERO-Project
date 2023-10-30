package linketinder.controller.empresa

import com.google.gson.Gson
import linketinder.dao.empresa.EmpresaDao
import linketinder.db.ConfigDatabase
import linketinder.db.IDatabaseConnection
import linketinder.db.factory.DatabaseFactory
import linketinder.db.factory.IDatabaseConnectionFactory
import linketinder.model.Empresa
import linketinder.service.empresa.EmpresaService
import linketinder.utils.servlet.ServletResponseUtils
import linketinder.utils.servlet.ServletUtils

import javax.servlet.annotation.WebServlet
import javax.servlet.http.HttpServlet
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

@WebServlet(name = "EmpresaController", urlPatterns = "/empresa/*")
class EmpresaController extends HttpServlet {
    private Gson gson = new Gson()
    private ServletUtils servletUtils = new ServletUtils()
    private ServletResponseUtils servletResponseUtils = new ServletResponseUtils()

    ConfigDatabase configDatabase = new ConfigDatabase()
    DatabaseFactory databaseFactory = new DatabaseFactory()
    IDatabaseConnectionFactory factory = databaseFactory.createConnectionFactory(configDatabase)
    IDatabaseConnection connection = factory.createConnection()
    EmpresaDao empresaDao = new EmpresaDao(connection)
    EmpresaService empresaService = new EmpresaService(empresaDao)

    EmpresaController() {}

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) {
        try {
            String pathInfo = request.getPathInfo()
            if (pathInfo == null ) {
                List<Empresa> empresas = this.empresaService.listarTodasEmpresas()
                String json = this.gson.toJson(empresas)
                this.servletResponseUtils.writeResponse(response, json)
            } else {
                int idEmpresa = this.servletUtils.pegarIdDaUrl(request)
                Empresa empresa = this.empresaService.obterEmpresaPorId(idEmpresa)
                String json = gson.toJson(empresa)
                servletResponseUtils.writeResponse(response, json)
            }
        } catch (Exception e) {
            this.servletResponseUtils.writeErrorResponse(response, HttpServletResponse.SC_BAD_REQUEST, e.getMessage())
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
            this.servletResponseUtils.writeErrorResponse(response, HttpServletResponse.SC_BAD_REQUEST,e.getMessage())
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
            this.servletResponseUtils.writeErrorResponse(response, HttpServletResponse.SC_BAD_REQUEST,e.getMessage())
        }
    }

    @Override
    protected void doDelete(HttpServletRequest request, HttpServletResponse response) {
        try {
            int empresaId = this.servletUtils.pegarIdDaUrl(request)
            this.empresaService.excluirEmpresa(empresaId)

            response.setStatus(HttpServletResponse.SC_NO_CONTENT)
        } catch (Exception e) {
            this.servletResponseUtils.writeErrorResponse(response, HttpServletResponse.SC_BAD_REQUEST,e.getMessage())
        }
    }
}
