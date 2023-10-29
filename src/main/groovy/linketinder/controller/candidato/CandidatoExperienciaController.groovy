package linketinder.controller.candidato

import com.google.gson.Gson
import linketinder.dao.candidato.CandidatoDao
import linketinder.dao.candidato.ExperienciaDao
import linketinder.db.ConfigDatabase
import linketinder.db.IDatabaseConnection
import linketinder.db.factory.DatabaseFactory
import linketinder.db.factory.IDatabaseConnectionFactory
import linketinder.model.Experiencia
import linketinder.service.candidato.CandidatoExperienciaService
import linketinder.utils.ServletUtils

import javax.servlet.annotation.WebServlet
import javax.servlet.http.HttpServlet
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

@WebServlet(name = "CandidatoExperienciaController", urlPatterns = "/candidatoExperiencia/*")
class CandidatoExperienciaController extends HttpServlet {
    private Gson gson = new Gson()
    private ServletUtils servletUtils = new ServletUtils()

    ConfigDatabase configDatabase = new ConfigDatabase()
    DatabaseFactory databaseFactory = new DatabaseFactory()
    IDatabaseConnectionFactory factory = databaseFactory.createConnectionFactory(configDatabase)
    IDatabaseConnection connection = factory.createConnection()
    CandidatoDao candidatoDao = new CandidatoDao(connection)
    ExperienciaDao experienciaDao = new ExperienciaDao(connection, candidatoDao)
    CandidatoExperienciaService candidatoExperienciaService = new CandidatoExperienciaService(experienciaDao)

    CandidatoExperienciaController() {}

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) {
        try {
            Experiencia experiencia = this.servletUtils.parseObjectFromRequest(request, Experiencia.class)
            this.candidatoExperienciaService.adicionarExperiencia(experiencia)

            this.servletUtils.configureResponse(response)
            response.setStatus(HttpServletResponse.SC_CREATED)
        } catch (IOException e) {
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST)
            response.getWriter().write("Erro ao processar a solicitação: " + e.getMessage())
        }
    }


    @Override
    protected void doPut(HttpServletRequest request, HttpServletResponse response) {
        try {
            int experienciaId = this.servletUtils.pegarIdDaUrl(request)
            Experiencia experiencia = this.servletUtils.parseObjectFromRequest(request, Experiencia.class)

            experiencia.setId(experienciaId)
            this.candidatoExperienciaService.atualizarExperiencia(experiencia)

            this.servletUtils.configureResponse(response)
            response.setStatus(HttpServletResponse.SC_OK)
        } catch (IOException | NumberFormatException e) {
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST)
            response.getWriter().write("Erro ao processar a solicitação: " + e.getMessage())
        }
    }


    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) {
        try {
            int idCandidato = servletUtils.pegarIdDaUrl(request)
            List<Experiencia> experiencias = candidatoExperienciaService.listarExperienciasPorCandidato(idCandidato)

            String json = gson.toJson(experiencias)
            servletUtils.writeResponse(response, json)
        } catch (Exception e) {
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST)
            response.getWriter().write("Erro ao processar a solicitação: " + e.getMessage())
        }
    }


    @Override
    protected void doDelete(HttpServletRequest request, HttpServletResponse response) {
        try {
            int idExperiencia = servletUtils.pegarIdDaUrl(request)
            this.candidatoExperienciaService.excluirExperiencia(idExperiencia)

            response.setStatus(HttpServletResponse.SC_NO_CONTENT)
        } catch (NumberFormatException | IOException e) {
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST)
            response.getWriter().write("Erro ao processar a solicitação: " + e.getMessage())
        }
    }
}
