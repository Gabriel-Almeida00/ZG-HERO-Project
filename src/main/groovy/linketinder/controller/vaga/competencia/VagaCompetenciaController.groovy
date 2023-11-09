package linketinder.controller.vaga.competencia


import linketinder.dao.vaga.VagaCompetenciaDao
import linketinder.dao.vaga.VagaDao
import linketinder.db.ConfigDatabase
import linketinder.db.IDatabaseConnection
import linketinder.db.factory.DatabaseFactory
import linketinder.db.factory.IDatabaseConnectionFactory
import linketinder.model.VagaCompetencia
import linketinder.model.dto.CompetenciaDTO
import linketinder.service.vaga.VagaCompetenciaService
import linketinder.servlet.ServletResponse
import linketinder.servlet.ServletUtils

import javax.servlet.annotation.WebServlet
import javax.servlet.http.HttpServlet
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

@WebServlet(name = "VagaCompetenciaController", urlPatterns = "/vagaCompetencia/*")
class VagaCompetenciaController extends HttpServlet {
    private ServletUtils servletUtils = new ServletUtils()
    private ServletResponse servletResponse = new ServletResponse()

    ConfigDatabase configDatabase = new ConfigDatabase()
    DatabaseFactory databaseFactory = new DatabaseFactory()
    IDatabaseConnectionFactory factory = databaseFactory.createConnectionFactory(configDatabase)
    IDatabaseConnection connection = factory.createConnection()
    VagaDao vagaDao = new VagaDao(connection)
    VagaCompetenciaDao vagaCompetenciaDao = new VagaCompetenciaDao(connection, vagaDao)
    VagaCompetenciaService vagaCompetenciaService = new VagaCompetenciaService(vagaCompetenciaDao)

    VagaCompetenciaController() {}

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) {
        int idCompetencia = this.servletUtils.pegarIdDaUrl(request)
        List<CompetenciaDTO> competencia = this.vagaCompetenciaService.listarCompetenciasPorVaga(idCompetencia)
        servletResponse.methodGet(response, competencia)
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) {
        servletResponse.methodPost(response, () -> {
            VagaCompetencia competencia = this.servletUtils.parseObjectFromRequest(request, VagaCompetencia.class)
            this.vagaCompetenciaService.adicionarVagaCompetencia(competencia)
        })
    }

    @Override
    protected void doPut(HttpServletRequest request, HttpServletResponse response) {
        servletResponse.methodPut(response, () -> {
            int competenciaId = this.servletUtils.pegarIdDaUrl(request)
            VagaCompetencia competencia = this.servletUtils.parseObjectFromRequest(request, VagaCompetencia.class)

            competencia.setId(competenciaId)
            this.vagaCompetenciaService.atualizarNivelVagaCompetencia(competencia)
        })
    }

    @Override
    protected void doDelete(HttpServletRequest request, HttpServletResponse response) {
        servletResponse.methodDelete(response, () -> {
            int idCompetencia = servletUtils.pegarIdDaUrl(request)
            this.vagaCompetenciaService.excluirVagaCompetencia(idCompetencia)
        })
    }
}
