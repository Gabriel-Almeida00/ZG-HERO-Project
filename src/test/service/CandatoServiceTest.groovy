package test.service

import entity.Candidato
import entity.Competencias
import entity.Experiencia
import entity.Formacao
import entity.enums.NivelCompetencia
import entity.enums.NivelExperiencia
import entity.enums.NivelFormacao
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

        service.cadastrarCandidato(candidato = new Candidato(
                "João",
                "joao@gmail.com",
                "012345",
                "111111111",
                25,
                "SP",
                "Descrição João",
                [new Competencias(
                        "Java",
                        NivelCompetencia.Intermediario)],
                [new Formacao(
                        "impacta",
                        "análise e desenvolvimento de sistemas",
                        NivelFormacao.Graduacao,
                        2023)],
                [new Experiencia(
                        "dev backend",
                        "tech ltda",
                        NivelExperiencia.Estagio)]))

        service.cadastrarCandidato(new Candidato(
                "Maria",
                "maria@gmail.com",
                "9987798",
                "78998",
                25,
                "RJ",
                "Descrição maria",
                [new Competencias(
                        "c++",
                        NivelCompetencia.Intermediario)],
                [new Formacao(
                        "usp",
                        "sistema de informações",
                        NivelFormacao.Graduacao,
                        2023)],
                [new Experiencia(
                        "dev games",
                        "tech ltda",
                        NivelExperiencia.Estagio)]))

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
        assert segundoCandidato.getCpf() == "78998"
    }

    @Test
    void testCadastrarCandidato() {
        def candidato = new Candidato( "Pedro",
                "pedro@gmail.com",
                "012345",
                "111111111",
                25,
                "SP",
                "Descrição João",
                [new Competencias(
                        "Java",
                        NivelCompetencia.Intermediario)],
                [new Formacao(
                        "impacta",
                        "análise e desenvolvimento de sistemas",
                        NivelFormacao.Graduacao,
                        2023)],
                [new Experiencia(
                        "dev backend",
                        "tech ltda",
                        NivelExperiencia.Estagio)])


        service.cadastrarCandidato(candidato)

        assert service.candidatos.size() == 3
        assert service.candidatos[2] == candidato
    }
}
