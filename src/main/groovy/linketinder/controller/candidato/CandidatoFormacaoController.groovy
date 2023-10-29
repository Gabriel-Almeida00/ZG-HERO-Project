package linketinder.controller.candidato

import com.google.gson.Gson
import linketinder.dao.candidato.CandidatoDao
import linketinder.dao.candidato.FormacaoDao
import linketinder.db.ConfigDatabase
import linketinder.db.IDatabaseConnection
import linketinder.db.factory.DatabaseFactory
import linketinder.db.factory.IDatabaseConnectionFactory
import linketinder.model.Formacao
import linketinder.service.candidato.CandidatoFormacaoService
import linketinder.utils.ServletUtils

import javax.servlet.annotation.WebServlet
import javax.servlet.http.HttpServlet
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

@WebServlet(name = "CandidatoFormacaoController", urlPatterns = "/candidatoFormacao/*")
class CandidatoFormacaoController extends HttpServlet {
    private Gson gson = new Gson()
    private ServletUtils servletUtils = new ServletUtils()

    ConfigDatabase configDatabase = new ConfigDatabase()
    DatabaseFactory databaseFactory = new DatabaseFactory()
    IDatabaseConnectionFactory factory = databaseFactory.createConnectionFactory(configDatabase)
    IDatabaseConnection connection = factory.createConnection()
    CandidatoDao candidatoDao = new CandidatoDao(connection)
    FormacaoDao formacaoDao = new FormacaoDao(connection, candidatoDao)
    CandidatoFormacaoService candidatoFormacaoService = new CandidatoFormacaoService(formacaoDao)

    CandidatoFormacaoController() {}


    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) {
        try {
            Formacao formacao = servletUtils.parseObjectFromRequest(request, Formacao.class)
            this.candidatoFormacaoService.adicionarFormacao(formacao)

            servletUtils.configureResponse(response)
            response.setStatus(HttpServletResponse.SC_CREATED)
        } catch (Exception e) {
            this.servletUtils.writeErrorResponse(response, HttpServletResponse.SC_BAD_REQUEST,e.getMessage())
        }
    }

    @Override
    protected void doPut(HttpServletRequest request, HttpServletResponse response) {
        try {
            int formacaoId = this.servletUtils.pegarIdDaUrl(request)
            Formacao formacao = this.servletUtils.parseObjectFromRequest(request, Formacao.class)

            formacao.setId(formacaoId)
            this.candidatoFormacaoService.atualizarFormacao(formacao)

            this.servletUtils.configureResponse(response)
            response.setStatus(HttpServletResponse.SC_OK)
        } catch (Exception e) {
            this.servletUtils.writeErrorResponse(response, HttpServletResponse.SC_BAD_REQUEST,e.getMessage())
        }
    }

    @Override
    protected void doDelete(HttpServletRequest request, HttpServletResponse response) {
        try {
            int formacaoId = this.servletUtils.pegarIdDaUrl(request)
            this.candidatoFormacaoService.excluirFormacao(formacaoId)

            response.setStatus(HttpServletResponse.SC_NO_CONTENT)
        } catch (Exception e) {
            this.servletUtils.writeErrorResponse(response, HttpServletResponse.SC_BAD_REQUEST,e.getMessage())
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) {
        try {
            int idCandidato = servletUtils.pegarIdDaUrl(request)
            List<Formacao> formacaos = this.candidatoFormacaoService.listarFormacoesPorCandidato(idCandidato)

            String json = gson.toJson(formacaos)
            servletUtils.writeResponse(response, json)
        } catch (Exception e) {
            this.servletUtils.writeErrorResponse(response, HttpServletResponse.SC_BAD_REQUEST,e.getMessage())
        }
    }

}
