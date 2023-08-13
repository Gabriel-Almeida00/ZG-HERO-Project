package test.service

import entity.Candidato
import entity.Empresa
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import service.CandidatoService
import service.CurtidaService
import service.EmpresaService

class CurtidaServiceTest {
    CurtidaService service
    Empresa empresa
    Candidato candidato

    @BeforeEach
    void setUp() {
        service = new CurtidaService()
        service.empresas = []
        service.candidatos = []

        empresa = new Empresa("Empresa A", "contato@empresaA.com", "123456789", "Brasil", "SP", "123456", "Descrição Empresa A", ["Java", "Python"])
        candidato = new Candidato("Maria", "maria@gmail.com", "222222222", 30, "RJ", "543210", "Descrição Maria", ["C++", "JavaScript"])

        service.empresas.add(empresa)
        service.candidatos.add(candidato)
    }

    @Test
    void testCurtirVaga() {
        service.curtirVaga("Maria", "Empresa A")

        def empresaCurtida = empresa.getCurtidas().get(0).getEmpresa()
        assert empresaCurtida == empresa
    }

    @Test
    void testCurtirCandidato() {
        service.curtirCandidato("Empresa A", "Maria")

        def curtida = candidato.getCurtidas().get(0)
        assert curtida.getEmpresa() == empresa
    }

    @Test
    void testEncontrarMatches() {
        service.curtirVaga("Maria", "Empresa A")
        service.curtirCandidato("Empresa A", "Maria")
        def matches = service.encontrarMatches()


        assert matches.size() == 1
        assert matches[0].getCandidato() == candidato
        assert matches[0].getEmpresa() == empresa
    }

}
