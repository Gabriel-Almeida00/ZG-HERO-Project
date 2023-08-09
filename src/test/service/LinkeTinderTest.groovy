package test.service

import entity.Candidato
import entity.Empresa
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import service.LinkeTinder

class LinkeTinderTest {

    LinkeTinder service
    Empresa empresa
    Candidato candidato

    @BeforeEach
    void setUp() {
        service = new LinkeTinder()
        service.empresas = []
        service.candidatos = []


        service.cadastrarEmpresa(empresa = new Empresa("Empresa A", "contato@empresaA.com", "123456789", "Brasil", "SP", "123456", "Descrição Empresa A", ["Java", "Python"]))
        service.cadastrarEmpresa(new Empresa("Empresa B", "contato@empresaB.com", "987654321", "Brasil", "RJ", "654321", "Descrição Empresa B", ["C++", "JavaScript"]))

        service.cadastrarCandidato(candidato = new Candidato("João", "joao@gmail.com", "111111111", 25, "SP", "012345", "Descrição João", ["Java", "Python"]))
        service.cadastrarCandidato(new Candidato("Maria", "maria@gmail.com", "222222222", 30, "RJ", "543210", "Descrição Maria", ["C++", "JavaScript"]))

        service.curtirVaga("João","Empresa A")
        service.curtirCandidato("Empresa A", "João")
    }

    @Test
    void testListarEmpresas() {
        List<Empresa> empresas = service.listarEmpresas()

        assert empresas.size() == 2

        Empresa primeiraEmpresa = empresas[0]
        assert primeiraEmpresa.getNome() == "Empresa A"
        assert primeiraEmpresa.getCnpj() == "123456789"
        assert primeiraEmpresa.getPais() == "Brasil"

        Empresa segundaEmpresa = empresas[1]
        assert segundaEmpresa.getNome() == "Empresa B"
        assert segundaEmpresa.getCnpj() == "987654321"
        assert segundaEmpresa.getPais() == "Brasil"
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

    @Test
    void testCadastrarEmpresa() {
        def empresa = new Empresa("Empresa A", "contato@empresaA.com", "123456789", "Brasil", "SP", "123456", "Descrição Empresa A", ["Java", "Python"])

        service.cadastrarEmpresa(empresa)

        assert service.empresas.size() == 3
        assert service.empresas[2] == empresa
    }

    @Test
    void testCurtirVaga(){
        service.curtirVaga("João","Empresa A")

        def curtida = empresa.getCurtidas().get(0)
        assert curtida.getCandidato() == candidato
    }

    @Test
    void testCurtirCandidato(){
        service.curtirCandidato("Empresa A","João")

        def curtida = candidato.getCurtidas().get(0)
        assert curtida.getEmpresa() == empresa
    }

    @Test
    void testEncontrarMatches() {

        def matches = service.encontrarMatches()


        assert matches.size() == 1
        assert matches[0].getCandidato() == candidato
        assert matches[0].getEmpresa() == empresa
    }
}
