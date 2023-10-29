package linketinder.controller.candidato

import com.google.gson.Gson
import linketinder.dao.candidato.CandidatoCompetenciaDao
import linketinder.dao.candidato.CandidatoDao
import linketinder.db.ConfigDatabase
import linketinder.db.IDatabaseConnection
import linketinder.db.factory.DatabaseFactory
import linketinder.db.factory.IDatabaseConnectionFactory
import linketinder.model.CandidatoCompetencia
import linketinder.model.dto.CompetenciaDTO
import linketinder.service.candidato.CandidatoCompetenciaService
import linketinder.utils.ServletUtils

import javax.servlet.annotation.WebServlet
import javax.servlet.http.HttpServlet
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

@WebServlet(name = "CandidatoCompetenciaController", urlPatterns = "/candidatoCompetencia/*")
class CandidatoCompetenciaController extends HttpServlet {
    private Gson gson = new Gson()
    private ServletUtils servletUtils = new ServletUtils()

    ConfigDatabase configDatabase = new ConfigDatabase()
    DatabaseFactory databaseFactory = new DatabaseFactory()
    IDatabaseConnectionFactory factory = databaseFactory.createConnectionFactory(configDatabase)
    IDatabaseConnection connection = factory.createConnection()
    CandidatoDao candidatoDao = new CandidatoDao(connection)
    CandidatoCompetenciaDao dao = new CandidatoCompetenciaDao(connection, candidatoDao)
    CandidatoCompetenciaService candidatoCompetenciaService = new CandidatoCompetenciaService(dao)

    CandidatoCompetenciaController() {}

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) {
        try {
            int idCandidato = servletUtils.pegarIdDaUrl(request)
            List<CompetenciaDTO> candidatoCompetencias = candidatoCompetenciaService.listarCompetenciasPorCandidato(idCandidato)

            String json = gson.toJson(candidatoCompetencias)
            servletUtils.writeResponse(response, json)
        } catch (Exception e) {
            this.servletUtils.writeErrorResponse(response, HttpServletResponse.SC_BAD_REQUEST,e.getMessage())
        }
    }



    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) {
        try {
            CandidatoCompetencia candidatoCompetencia = servletUtils.parseObjectFromRequest(request, CandidatoCompetencia.class)
            this.candidatoCompetenciaService.adicionarCandidatoCompetencia(candidatoCompetencia)

            servletUtils.configureResponse(response)
            response.setStatus(HttpServletResponse.SC_CREATED)
        } catch (Exception e) {
            this.servletUtils.writeErrorResponse(response, HttpServletResponse.SC_BAD_REQUEST,e.getMessage())
        }
    }


    @Override
    protected void doPut(HttpServletRequest request, HttpServletResponse response) {
        try {
            int competenciaId = this.servletUtils.pegarIdDaUrl(request)
            CandidatoCompetencia candidatoCompetencia = this.servletUtils.parseObjectFromRequest(request, CandidatoCompetencia.class)

            candidatoCompetencia.setId(competenciaId)
            this.candidatoCompetenciaService.atualizarNivelCompetenciaCandidato(candidatoCompetencia)

            this.servletUtils.configureResponse(response)
            response.setStatus(HttpServletResponse.SC_OK)
        } catch (Exception e) {
            this.servletUtils.writeErrorResponse(response, HttpServletResponse.SC_BAD_REQUEST,e.getMessage())
        }
    }


    @Override
    protected void doDelete(HttpServletRequest request, HttpServletResponse response) {
        try {
            int idCompetencia = servletUtils.pegarIdDaUrl(request)
            this.candidatoCompetenciaService.excluirCompetenciaCandidato(idCompetencia)

            response.setStatus(HttpServletResponse.SC_NO_CONTENT)
        } catch (Exception e) {
            this.servletUtils.writeErrorResponse(response, HttpServletResponse.SC_BAD_REQUEST,e.getMessage())
        }
    }
}
