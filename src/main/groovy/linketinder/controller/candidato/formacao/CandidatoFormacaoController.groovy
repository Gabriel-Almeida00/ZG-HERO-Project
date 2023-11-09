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

@WebServlet(name = "CandidatoFormacaoController", urlPatterns = "/candidatoFormacao/*")
class CandidatoFormacaoController extends HttpServlet {
    private ServletUtils servletUtils = new ServletUtils()
    private ServletResponse servletResponse = new ServletResponse()

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
        servletResponse.methodPost(response, () -> {
            Formacao formacao = servletUtils.parseObjectFromRequest(request, Formacao.class)
            this.candidatoFormacaoService.adicionarFormacao(formacao)
        })
    }

    @Override
    protected void doPut(HttpServletRequest request, HttpServletResponse response) {
        servletResponse.methodPut(response, () -> {
            int formacaoId = this.servletUtils.pegarIdDaUrl(request)
            Formacao formacao = this.servletUtils.parseObjectFromRequest(request, Formacao.class)

            formacao.setId(formacaoId)
            this.candidatoFormacaoService.atualizarFormacao(formacao)
        })
    }

    @Override
    protected void doDelete(HttpServletRequest request, HttpServletResponse response) {
        servletResponse.methodDelete(response, () -> {
            int formacaoId = this.servletUtils.pegarIdDaUrl(request)
            this.candidatoFormacaoService.excluirFormacao(formacaoId)
        })
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) {
        int idCandidato = servletUtils.pegarIdDaUrl(request)
        List<Formacao> formacaos = this.candidatoFormacaoService.listarFormacoesPorCandidato(idCandidato)
        servletResponse.methodGet(response, formacaos)
    }
}
