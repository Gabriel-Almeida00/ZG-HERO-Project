package linketinder.controller.candidato

import com.google.gson.Gson
import linketinder.dao.candidato.CandidatoDao
import linketinder.db.ConfigDatabase
import linketinder.db.IDatabaseConnection
import linketinder.db.factory.DatabaseFactory
import linketinder.db.factory.IDatabaseConnectionFactory
import linketinder.model.Candidato
import linketinder.model.CandidatoCompetencia
import linketinder.model.Experiencia
import linketinder.model.dto.CandidatoDTO
import linketinder.model.dto.CompetenciaDTO
import linketinder.service.candidato.CandidatoService
import linketinder.utils.ServletUtils

import javax.servlet.annotation.WebServlet
import javax.servlet.http.HttpServlet
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse
import java.util.stream.Collectors

@WebServlet(name = "CandidatoController", urlPatterns = "/candidatos/*")
class CandidatoController extends HttpServlet {
    private Gson gson = new Gson()
    private ServletUtils servletUtils = new ServletUtils()

    ConfigDatabase configDatabase = new ConfigDatabase()
    DatabaseFactory databaseFactory = new DatabaseFactory()
    IDatabaseConnectionFactory factory = databaseFactory.createConnectionFactory(configDatabase)
    IDatabaseConnection connection = factory.createConnection()
    CandidatoDao dao = new CandidatoDao(connection)
    CandidatoService candidatoService = new CandidatoService(dao)


    CandidatoController() {}


    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) {
        try {
            List<CandidatoDTO> candidatos = candidatoService.listarCandidatos()

            String json = gson.toJson(candidatos)
            servletUtils.writeResponse(response, json)
        } catch (Exception e) {
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST)
            response.getWriter().write("Erro ao processar a solicitação: " + e.getMessage())
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) {
        try {
            Candidato candidato = servletUtils.parseObjectFromRequest(request, Candidato.class)
            this.candidatoService.cadastrarCandidato(candidato)

            servletUtils.configureResponse(response)
            response.setStatus(HttpServletResponse.SC_CREATED)
        } catch (IOException e) {
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST)
            response.getWriter().write("Erro ao processar a solicitação: " + e.getMessage())
        }
    }


    @Override
    protected void doPut(HttpServletRequest request, HttpServletResponse response) {
        try {
            int idCandidato = this.servletUtils.pegarIdDaUrl(request)
            Candidato candidato = this.servletUtils.parseObjectFromRequest(request, Candidato.class)

            candidato.setId(idCandidato)
            this.candidatoService.atualizarCandidato(candidato)

            this.servletUtils.configureResponse(response)
            response.setStatus(HttpServletResponse.SC_OK)
        } catch (IOException | NumberFormatException e) {
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST)
            response.getWriter().write("Erro ao processar a solicitação: " + e.getMessage())
        }
    }


    @Override
    protected void doDelete(HttpServletRequest request, HttpServletResponse response) {
        try {
            int candidatoId = servletUtils.pegarIdDaUrl(request)
            this.candidatoService.deletarCandidato(candidatoId)

            response.setStatus(HttpServletResponse.SC_NO_CONTENT)
        } catch (NumberFormatException | IOException e) {
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST)
            response.getWriter().write("Erro ao processar a solicitação: " + e.getMessage())
        }
    }
}
