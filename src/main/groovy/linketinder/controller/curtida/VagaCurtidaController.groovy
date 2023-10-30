package linketinder.controller.curtida


import linketinder.dao.candidato.CandidatoDao
import linketinder.dao.curtida.CurtidaDao
import linketinder.dao.empresa.EmpresaDao
import linketinder.dao.vaga.VagaDao
import linketinder.db.ConfigDatabase
import linketinder.db.IDatabaseConnection
import linketinder.db.factory.DatabaseFactory
import linketinder.db.factory.IDatabaseConnectionFactory
import linketinder.model.VagaCurtida
import linketinder.model.dto.CandidatoQueCurtiuVagaDTO
import linketinder.service.curtida.CurtidaService
import linketinder.servlet.ServletGet
import linketinder.servlet.ServletResponseUtils
import linketinder.servlet.ServletUtils

import javax.servlet.annotation.WebServlet
import javax.servlet.http.HttpServlet
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

@WebServlet(name = "VagaCurtidaController", urlPatterns = "/curtida/vaga/*")
class VagaCurtidaController extends HttpServlet {
    private ServletUtils servletUtils = new ServletUtils()
    private ServletResponseUtils servletResponseUtils = new ServletResponseUtils()
    private ServletGet servletGet = new ServletGet()

    ConfigDatabase configDatabase = new ConfigDatabase()
    DatabaseFactory databaseFactory = new DatabaseFactory()
    IDatabaseConnectionFactory factory = databaseFactory.createConnectionFactory(configDatabase)
    IDatabaseConnection connection = factory.createConnection()

    VagaDao vagadao = new VagaDao(connection)
    CandidatoDao candidatoDao = new CandidatoDao(connection)
    EmpresaDao empresaDao = new EmpresaDao(connection)
    CurtidaDao curtidaDao = new CurtidaDao(connection, candidatoDao, vagadao, empresaDao)
    CurtidaService curtidaService = new CurtidaService(curtidaDao, vagadao)

    VagaCurtidaController() {}

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) {
        try {
            VagaCurtida vagaCurtida = servletUtils.parseObjectFromRequest(request, VagaCurtida.class)
            this.curtidaService.curtirVaga(vagaCurtida)

            servletResponseUtils.configureResponse(response)
            response.setStatus(HttpServletResponse.SC_CREATED)
        } catch (Exception e) {
            this.servletResponseUtils.writeErrorResponse(response, HttpServletResponse.SC_BAD_REQUEST, e.getMessage())
        }

    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) {
        int idVaga = this.servletUtils.pegarIdDaUrl(request)
        List<CandidatoQueCurtiuVagaDTO> candidatos = this.curtidaService.listarCandidatosQueCurtiramVaga(idVaga)
        servletGet.methodGet(response, candidatos)
    }
}
