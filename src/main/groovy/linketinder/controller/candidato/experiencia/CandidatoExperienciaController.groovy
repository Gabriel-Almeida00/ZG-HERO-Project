package linketinder.controller.candidato.experiencia


import linketinder.dao.candidato.CandidatoDao
import linketinder.dao.candidato.ExperienciaDao
import linketinder.db.ConfigDatabase
import linketinder.db.IDatabaseConnection
import linketinder.db.factory.DatabaseFactory
import linketinder.db.factory.IDatabaseConnectionFactory
import linketinder.model.Experiencia
import linketinder.service.candidato.CandidatoExperienciaService
import linketinder.servlet.ServletResponse
import linketinder.servlet.ServletUtils

import javax.servlet.annotation.WebServlet
import javax.servlet.http.HttpServlet
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

@WebServlet(name = "CandidatoExperienciaController", urlPatterns = "/candidatoExperiencia/*")
class CandidatoExperienciaController extends HttpServlet {
    private ServletUtils servletUtils = new ServletUtils()
    private ServletResponse servletResponse = new ServletResponse()

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
        servletResponse.methodPost(response, () -> {
            Experiencia experiencia = this.servletUtils.parseObjectFromRequest(request, Experiencia.class)
            this.candidatoExperienciaService.adicionarExperiencia(experiencia)
        })
    }


    @Override
    protected void doPut(HttpServletRequest request, HttpServletResponse response) {
        servletResponse.methodPut(response, () -> {
            int experienciaId = this.servletUtils.pegarIdDaUrl(request)
            Experiencia experiencia = this.servletUtils.parseObjectFromRequest(request, Experiencia.class)

            experiencia.setId(experienciaId)
            this.candidatoExperienciaService.atualizarExperiencia(experiencia)
        })
    }


    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) {
        int idCandidato = servletUtils.pegarIdDaUrl(request)
        List<Experiencia> experiencias = candidatoExperienciaService.listarExperienciasPorCandidato(idCandidato)
        servletResponse.methodGet(response, experiencias)
    }


    @Override
    protected void doDelete(HttpServletRequest request, HttpServletResponse response) {
        servletResponse.methodDelete(response, () -> {
            int idExperiencia = servletUtils.pegarIdDaUrl(request)
            this.candidatoExperienciaService.excluirExperiencia(idExperiencia)
        })
    }
}
