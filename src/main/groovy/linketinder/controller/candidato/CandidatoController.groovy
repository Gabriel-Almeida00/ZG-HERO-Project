package linketinder.controller.candidato


import linketinder.dao.candidato.CandidatoDao
import linketinder.db.ConfigDatabase
import linketinder.db.IDatabaseConnection
import linketinder.db.factory.DatabaseFactory
import linketinder.db.factory.IDatabaseConnectionFactory
import linketinder.model.Candidato
import linketinder.model.dto.CandidatoDTO
import linketinder.service.candidato.CandidatoService
import linketinder.servlet.ServletResponse
import linketinder.servlet.ServletUtils

import javax.servlet.annotation.WebServlet
import javax.servlet.http.HttpServlet
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

@WebServlet(name = "CandidatoController", urlPatterns = "/candidatos/*")
class CandidatoController extends HttpServlet {
    private ServletUtils servletUtils = new ServletUtils()
    private ServletResponse servletResponse = new ServletResponse()

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
        servletResponse.methodGet(response, candidatos)
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) {
        servletResponse.methodPost(response, () -> {
            Candidato candidato = servletUtils.parseObjectFromRequest(request, Candidato.class)
            this.candidatoService.cadastrarCandidato(candidato)
        })
    }


    @Override
    protected void doPut(HttpServletRequest request, HttpServletResponse response) {
        servletResponse.methodPut(response, () -> {
            int idCandidato = this.servletUtils.pegarIdDaUrl(request)
            Candidato candidato = this.servletUtils.parseObjectFromRequest(request, Candidato.class)

            candidato.setId(idCandidato)
            this.candidatoService.atualizarCandidato(candidato)
        })
    }


    @Override
    protected void doDelete(HttpServletRequest request, HttpServletResponse response) {
        servletResponse.methodDelete(response, () -> {
            int candidatoId = servletUtils.pegarIdDaUrl(request)
            this.candidatoService.deletarCandidato(candidatoId)
        })
    }
}
