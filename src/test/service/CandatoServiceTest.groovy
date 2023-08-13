package test.service

import entity.Candidato
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import service.CandidatoService

class CandatoServiceTest {

    CandidatoService service
    Candidato candidato

    @BeforeEach
    void setUp() {
        service = new CandidatoService()
        service.candidatos = []

        service.cadastrarCandidato(candidato = new Candidato("João", "joao@gmail.com", "111111111", 25, "SP", "012345", "Descrição João", ["Java", "Python"]))
        service.cadastrarCandidato(new Candidato("Maria", "maria@gmail.com", "222222222", 30, "RJ", "543210", "Descrição Maria", ["C++", "JavaScript"]))
    }

    @Test
    void testListarCandidatos() {
        List<Candidato> candidatos = service.listarCandidatos()

        assert candidatos.size() == 2

        Candidato primeiroCandidato = candidatos[0]
        assert primeiroCandidato.getNome() == "João"
        assert primeiroCandidato.getEmail() == "joao@gmail.com"
        assert primeiroCandidato.getCpf() == "111111111"

        Candidato segundoCandidato = candidatos[1]
        assert segundoCandidato.getNome() == "Maria"
        assert segundoCandidato.getEmail() == "maria@gmail.com"
        assert segundoCandidato.getCpf() == "222222222"
    }

    @Test
    void testCadastrarCandidato() {
        def candidato = new Candidato("Pedro", "pedro@gmail.com", "333333333", 28, "MG", "987654", "Descrição Pedro", ["Python", "HTML"]);

        service.cadastrarCandidato(candidato)

        assert service.candidatos.size() == 3
        assert service.candidatos[2] == candidato
    }
}
