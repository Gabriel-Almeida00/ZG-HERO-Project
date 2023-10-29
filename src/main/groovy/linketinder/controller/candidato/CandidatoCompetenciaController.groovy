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
import java.util.stream.Collectors

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
        int idCandidato = servletUtils.pegarIdDaUrl(request)
        List<CompetenciaDTO> competenciaDTOS = this.candidatoCompetenciaService.listarCompetenciasPorCandidato(idCandidato)
        String json = this.gson.toJson(competenciaDTOS)

        response.setCharacterEncoding("UTF-8")
        response.setContentType("application/json; charset=UTF-8")
        response.setStatus(HttpServletResponse.SC_OK)

        PrintWriter out = response.getWriter()
        out.print(json)
        out.flush()
    }


    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) {
        try {
            request.setCharacterEncoding("UTF-8")
            String requestBody = request.getReader().lines()
                    .collect(Collectors.joining(System.lineSeparator()))

            CandidatoCompetencia candidatoCompetencia = gson.fromJson(new String(requestBody
                    .getBytes("UTF-8"), "UTF-8"), CandidatoCompetencia.class)

            this.candidatoCompetenciaService.adicionarCandidatoCompetencia(candidatoCompetencia)

            response.setStatus(HttpServletResponse.SC_CREATED)
        } catch (IOException e) {
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST)
            response.getWriter().write("Erro ao processar a solicitação: " + e.getMessage())
        }
    }

    @Override
    protected void doPut(HttpServletRequest request, HttpServletResponse response) {
        try {
            int idCompetencia = servletUtils.pegarIdDaUrl(request)
            request.setCharacterEncoding("UTF-8")

            String requestBody = request.getReader().lines()
                    .collect(Collectors.joining(System.lineSeparator()))

            CandidatoCompetencia candidatoCompetencia = gson.fromJson(new String(requestBody
                    .getBytes("UTF-8"), "UTF-8"), CandidatoCompetencia.class)


            candidatoCompetencia.setId(idCompetencia)
            this.candidatoCompetenciaService.atualizarNivelCompetenciaCandidato(candidatoCompetencia)

            response.setStatus(HttpServletResponse.SC_OK)
        } catch (IOException | NumberFormatException e) {
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST)
            response.getWriter().write("Erro ao processar a solicitação: " + e.getMessage())
        }
    }


    @Override
    protected void doDelete(HttpServletRequest request, HttpServletResponse response) {
        try {
            int idCompetencia = servletUtils.pegarIdDaUrl(request)
            this.candidatoCompetenciaService.excluirCompetenciaCandidato(idCompetencia)

            response.setStatus(HttpServletResponse.SC_NO_CONTENT)
        } catch (NumberFormatException | IOException e) {
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST)
            response.getWriter().write("Erro ao processar a solicitação: " + e.getMessage())
        }
    }
}
