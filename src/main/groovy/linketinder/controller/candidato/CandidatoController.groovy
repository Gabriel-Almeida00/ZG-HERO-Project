package linketinder.controller.candidato

import com.google.gson.Gson
import linketinder.dao.candidato.CandidatoDao
import linketinder.db.ConfigDatabase
import linketinder.db.IDatabaseConnection
import linketinder.db.factory.DatabaseFactory
import linketinder.db.factory.IDatabaseConnectionFactory
import linketinder.model.Candidato
import linketinder.model.dto.CandidatoDTO
import linketinder.service.candidato.CandidatoService
import java.util.stream.Collectors


import javax.servlet.annotation.WebServlet
import javax.servlet.http.HttpServlet
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

@WebServlet(name = "CandidatoController", urlPatterns = "/candidatos/*")
class CandidatoController extends HttpServlet {
    private Gson gson = new Gson()
    ConfigDatabase configDatabase = new ConfigDatabase()
    DatabaseFactory databaseFactory = new DatabaseFactory()
    IDatabaseConnectionFactory factory = databaseFactory.createConnectionFactory(configDatabase)
    IDatabaseConnection connection = factory.createConnection()
    CandidatoDao dao = new CandidatoDao(connection)
    CandidatoService candidatoService = new CandidatoService(dao)


    CandidatoController() {}


    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) {
        List<CandidatoDTO> candidatos = this.candidatoService.listarCandidatos()
        String json = this.gson.toJson(candidatos)

        response.setContentType("application/json")
        response.setCharacterEncoding("UTF-8")
        response.setStatus(HttpServletResponse.SC_OK)

        PrintWriter out = response.getWriter()
        out.print(json)
        out.flush()
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) {
        try {
            String requestBody = request.getReader().lines()
                    .collect(Collectors.joining(System.lineSeparator()))
            Candidato candidato = gson.fromJson(requestBody, Candidato.class)

            this.candidatoService.cadastrarCandidato(candidato)

            response.setStatus(HttpServletResponse.SC_CREATED)
        } catch (IOException e) {
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST)
            response.getWriter().write("Erro ao processar a solicitação: " + e.getMessage());
        }
    }


    @Override
    protected void doPut(HttpServletRequest request, HttpServletResponse response) {
        try {
            int candidatoId = this.extractCandidatoId(request)

            String requestBody = request.getReader().lines().collect(Collectors.joining(System.lineSeparator()))
            Candidato candidato = gson.fromJson(requestBody, Candidato.class)

            candidato.setId(candidatoId)
            this.candidatoService.atualizarCandidato(candidato)

            response.setStatus(HttpServletResponse.SC_OK)
        } catch (IOException | NumberFormatException e) {
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST)
            response.getWriter().write("Erro ao processar a solicitação: " + e.getMessage());
        }
    }


    @Override
    protected void doDelete(HttpServletRequest request, HttpServletResponse response) {
        try {
            int candidatoId = this.extractCandidatoId(request)
            this.candidatoService.deletarCandidato(candidatoId)

            response.setStatus(HttpServletResponse.SC_NO_CONTENT)
        } catch (NumberFormatException | IOException e) {
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST)
            response.getWriter().write("Erro ao processar a solicitação: " + e.getMessage());
        }
    }

    private int extractCandidatoId(HttpServletRequest request) {
        String pathInfo = request.getPathInfo()
        String[] pathParts = pathInfo.split("/")
        if (pathParts.length == 2) {
            try {
                return Integer.parseInt(pathParts[1])
            } catch (NumberFormatException e) {
                throw new IllegalArgumentException("O valor passado deve ser numérico " + e.getMessage())
            }
        }
        return -1
    }

}
