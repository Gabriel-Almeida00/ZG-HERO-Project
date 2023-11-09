package linketinder.controller.candidato.formacao


import linketinder.dao.candidato.CandidatoDao
import linketinder.dao.candidato.FormacaoDao
import linketinder.db.ConfigDatabase
import linketinder.db.IDatabaseConnection
import linketinder.db.factory.DatabaseFactory
import linketinder.db.factory.IDatabaseConnectionFactory
import linketinder.model.Formacao
import linketinder.service.candidato.CandidatoFormacaoService
import linketinder.servlet.ServletResponse
import linketinder.servlet.ServletUtils

import javax.servlet.annotation.WebServlet
import javax.servlet.http.HttpServlet
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

@WebServlet(name = "CandidatoFormacoesController", urlPatterns = "/candidato/formacao/*")
class CandidatoFormacoesController extends HttpServlet {
    private ServletUtils servletUtils = new ServletUtils()
    private ServletResponse servletResponse = new ServletResponse()

    ConfigDatabase configDatabase = new ConfigDatabase()
    DatabaseFactory databaseFactory = new DatabaseFactory()
    IDatabaseConnectionFactory factory = databaseFactory.createConnectionFactory(configDatabase)
    IDatabaseConnection connection = factory.createConnection()
    CandidatoDao candidatoDao = new CandidatoDao(connection)
    FormacaoDao formacaoDao = new FormacaoDao(connection, candidatoDao)
    CandidatoFormacaoService candidatoFormacaoService = new CandidatoFormacaoService(formacaoDao)

    CandidatoFormacoesController() {}


    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) {
        int idFormacao = servletUtils.pegarIdDaUrl(request)
        Formacao formacao = this.candidatoFormacaoService.buscarFormacaoPorId(idFormacao)
        servletResponse.methodGet(response, formacao)
    }
}
