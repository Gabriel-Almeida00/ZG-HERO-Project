package linketinder.controller.login

import linketinder.dao.login.ILoginDao
import linketinder.dao.login.LoginDao
import linketinder.db.ConfigDatabase
import linketinder.db.IDatabaseConnection
import linketinder.db.factory.DatabaseFactory
import linketinder.db.factory.IDatabaseConnectionFactory
import linketinder.model.CandidatoCompetencia
import linketinder.model.dto.LoginDTO
import linketinder.service.login.ILoginService
import linketinder.service.login.LoginService
import linketinder.servlet.ServletResponse
import linketinder.servlet.ServletUtils

import javax.servlet.annotation.WebServlet
import javax.servlet.http.HttpServlet
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

@WebServlet(name = "LoginCandidatoController", urlPatterns = "/login/candidato")
class LoginCandidatoController extends HttpServlet {
    private ServletUtils servletUtils = new ServletUtils()
    private ServletResponse servletResponse = new ServletResponse()

    ConfigDatabase configDatabase = new ConfigDatabase()
    DatabaseFactory databaseFactory = new DatabaseFactory()
    IDatabaseConnectionFactory factory = databaseFactory.createConnectionFactory(configDatabase)
    IDatabaseConnection connection = factory.createConnection()

    ILoginDao loginDao = new LoginDao(connection)
    LoginService loginService = new LoginService(loginDao)

    LoginCandidatoController() {
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) {
        servletResponse.methodPostLogin(response, () -> {
            LoginDTO loginDTO  = servletUtils.parseObjectFromRequest(request, LoginDTO.class)
            this.loginService.loginCandidato(loginDTO)
        })
    }
}
