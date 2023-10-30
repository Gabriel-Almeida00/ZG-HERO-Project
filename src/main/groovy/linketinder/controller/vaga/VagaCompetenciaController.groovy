package linketinder.controller.vaga


import linketinder.dao.vaga.VagaCompetenciaDao
import linketinder.dao.vaga.VagaDao
import linketinder.db.ConfigDatabase
import linketinder.db.IDatabaseConnection
import linketinder.db.factory.DatabaseFactory
import linketinder.db.factory.IDatabaseConnectionFactory
import linketinder.model.VagaCompetencia
import linketinder.model.dto.CompetenciaDTO
import linketinder.service.vaga.VagaCompetenciaService
import linketinder.servlet.ServletGet
import linketinder.servlet.ServletResponseUtils
import linketinder.servlet.ServletUtils

import javax.servlet.annotation.WebServlet
import javax.servlet.http.HttpServlet
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

@WebServlet(name = "VagaCompetenciaController", urlPatterns = "/vagaCompetencia/*")
class VagaCompetenciaController extends HttpServlet {
    private ServletUtils servletUtils = new ServletUtils()
    private ServletResponseUtils servletResponseUtils = new ServletResponseUtils()
    private ServletGet servletGet = new ServletGet()

    ConfigDatabase configDatabase = new ConfigDatabase()
    DatabaseFactory databaseFactory = new DatabaseFactory()
    IDatabaseConnectionFactory factory = databaseFactory.createConnectionFactory(configDatabase)
    IDatabaseConnection connection = factory.createConnection()
    VagaDao vagaDao = new VagaDao(connection)
    VagaCompetenciaDao vagaCompetenciaDao = new VagaCompetenciaDao(connection, vagaDao)
    VagaCompetenciaService vagaCompetenciaService = new VagaCompetenciaService(vagaCompetenciaDao)

    VagaCompetenciaController() {}

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) {
        int idCompetencia = this.servletUtils.pegarIdDaUrl(request)
        List<CompetenciaDTO> competencia = this.vagaCompetenciaService.listarCompetenciasPorVaga(idCompetencia)
        servletGet.methodGet(response, competencia)
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) {
        try {
            VagaCompetencia competencia = this.servletUtils.parseObjectFromRequest(request, VagaCompetencia.class)
            this.vagaCompetenciaService.adicionarVagaCompetencia(competencia)

            servletResponseUtils.configureResponse(response)
            response.setStatus(HttpServletResponse.SC_CREATED)
        } catch (Exception e) {
            this.servletResponseUtils.writeErrorResponse(response, HttpServletResponse.SC_BAD_REQUEST, e.getMessage())
        }
    }

    @Override
    protected void doPut(HttpServletRequest request, HttpServletResponse response) {
        try {
            int competenciaId = this.servletUtils.pegarIdDaUrl(request)
            VagaCompetencia competencia = this.servletUtils.parseObjectFromRequest(request, VagaCompetencia.class)

            competencia.setId(competenciaId)
            this.vagaCompetenciaService.atualizarNivelVagaCompetencia(competencia)

            this.servletResponseUtils.configureResponse(response)
            response.setStatus(HttpServletResponse.SC_OK)
        } catch (Exception e) {
            this.servletResponseUtils.writeErrorResponse(response, HttpServletResponse.SC_BAD_REQUEST, e.getMessage())
        }
    }

    @Override
    protected void doDelete(HttpServletRequest request, HttpServletResponse response) {
        try {
            int idCompetencia = servletUtils.pegarIdDaUrl(request)
            this.vagaCompetenciaService.excluirVagaCompetencia(idCompetencia)

            response.setStatus(HttpServletResponse.SC_NO_CONTENT)
        } catch (Exception e) {
            this.servletResponseUtils.writeErrorResponse(response, HttpServletResponse.SC_BAD_REQUEST, e.getMessage())
        }
    }
}
