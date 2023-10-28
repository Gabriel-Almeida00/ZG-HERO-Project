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

import javax.servlet.annotation.WebServlet
import javax.servlet.http.HttpServlet
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

@WebServlet(name = "CandidatoController", urlPatterns = "/candidatos")
class CandidatoController extends HttpServlet  {
    private Gson gson = new Gson()
    ConfigDatabase configDatabase = new ConfigDatabase()
    DatabaseFactory databaseFactory = new DatabaseFactory()
    IDatabaseConnectionFactory factory = databaseFactory.createConnectionFactory(configDatabase)
    IDatabaseConnection connection = factory.createConnection()
    CandidatoDao dao = new CandidatoDao(connection)
    CandidatoService candidatoService = new CandidatoService(dao)



    CandidatoController() {
    }


    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) {
        List<CandidatoDTO> candidatos = this.listarCandidatos()

        String json = this.gson.toJson(candidatos)

        response.setContentType("application/json")
        response.setCharacterEncoding("UTF-8")
        response.setStatus(HttpServletResponse.SC_OK)

        PrintWriter out = response.getWriter()
        out.print(json)
        out.flush()
    }

    List<CandidatoDTO> listarCandidatos(){
        return this.candidatoService.listarCandidatos()
    }

    void criarCandidato(Candidato candidato){
        candidatoService.cadastrarCandidato(candidato)
    }

    void atualizarCandidato(Candidato candidato){
        candidatoService.atualizarCandidato(candidato)
    }

    void excluirCandidato(Integer candidatoId){
        candidatoService.deletarCandidato(candidatoId)
    }
}
