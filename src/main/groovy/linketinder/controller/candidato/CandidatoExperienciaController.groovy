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
import java.util.stream.Collectors

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
            request.setCharacterEncoding("UTF-8")

            String requestBody = request.getReader().lines()
                    .collect(Collectors.joining(System.lineSeparator()))

            Experiencia experiencia = gson.fromJson(new String(requestBody
                    .getBytes("UTF-8"), "UTF-8"), Experiencia.class)

            this.candidatoExperienciaService.adicionarExperiencia(experiencia)
            response.setStatus(HttpServletResponse.SC_CREATED)
        } catch (IOException e) {
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST)
            response.setCharacterEncoding("UTF-8")
            response.getWriter().write("Erro ao processar a solicitação: " + e.getMessage())
        }
    }

    @Override
    protected void doPut(HttpServletRequest request, HttpServletResponse response) {
        try {
            int experienciaId = servletUtils.pegarIdDaUrl(request)
            request.setCharacterEncoding("UTF-8")

            String requestBody = request.getReader().lines()
                    .collect(Collectors.joining(System.lineSeparator()))

            Experiencia experiencia = gson.fromJson(new String(requestBody
                    .getBytes("UTF-8"), "UTF-8"), Experiencia.class)

            experiencia.setId(experienciaId)
            this.candidatoExperienciaService.atualizarExperiencia(experiencia)

            response.setStatus(HttpServletResponse.SC_OK)
        } catch (IOException | NumberFormatException e) {
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST)
            response.getWriter().write("Erro ao processar a solicitação: " + e.getMessage())
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) {
        int idCandidato = servletUtils.pegarIdDaUrl(request)
        List<Experiencia> experiencias = this.candidatoExperienciaService.listarExperienciasPorCandidato(idCandidato)

        response.setCharacterEncoding("UTF-8")
        response.setContentType("application/json; charset=UTF-8")
        response.setStatus(HttpServletResponse.SC_OK)

        PrintWriter out = response.getWriter()
        String json = this.gson.toJson(experiencias)
        out.print(json)

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
