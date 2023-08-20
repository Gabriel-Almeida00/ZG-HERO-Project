package test.service

import entity.Competencias
import entity.Empresa
import entity.Vaga
import entity.enums.NivelCompetencia
import entity.enums.NivelExperiencia
import entity.enums.NivelFormacao
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import service.EmpresaService

class EmpresaServiceTest {

    EmpresaService service
    Empresa empresa

    @BeforeEach
    void setUp() {
        service = new EmpresaService()
        service.empresas = []

        service.cadastrarEmpresa(empresa = new Empresa("Empresa A", "contato@empresaA.com", "123456789", "123456789", "Brasil", "Sp", "Descrição Empresa A"))
        service.cadastrarEmpresa(new Empresa("Empresa B", "contato@empresaB.com", "987654321", "987654321", "Brasil", "RJ", "Descrição Empresa B"))

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
    void testCadastrarEmpresa() {
        def empresa = new Empresa("Empresa A", "contato@empresaA.com", "123456789", "Brasil", "SP", "123456", "Descrição Empresa A")

        service.cadastrarEmpresa(empresa)

        assert service.empresas.size() == 3
        assert service.empresas[2] == empresa
    }

    @Test
    void testCriarVaga() {
        Vaga vaga = new Vaga(
                "Desenvolvedor Java",
                "Descrição da vaga",
                [new Competencias(
                        "Java",
                        NivelCompetencia.Basico
                )],
                NivelFormacao.Graduacao,
                NivelExperiencia.Junior)

        service.criarVaga("Empresa A", vaga)

        assert empresa.getVagas().size() == 1
        assert empresa.getVagas()[0].getNome() == "Desenvolvedor Java"
        assert empresa.getVagas()[0].getDescricao() == "Descrição da vaga"
        assert empresa.getVagas()[0].getRequisitos().size() == 1
        assert empresa.getVagas()[0].getRequisitos()[0].getNome() == "Java"
    }

}
