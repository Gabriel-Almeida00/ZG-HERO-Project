package test.service

import entity.Candidato
import entity.Competencias
import entity.Empresa
import entity.Experiencia
import entity.Formacao
import entity.enums.NivelCompetencia
import entity.enums.NivelExperiencia
import entity.enums.NivelFormacao
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import service.CurtidaCandidatoService

class CurtidaCandidatoServiceTest {

    CurtidaCandidatoService curtidaCandidatoService
    Empresa empresa
    Candidato candidato

    @BeforeEach
    void setUp() {
        curtidaCandidatoService = new CurtidaCandidatoService()
        empresa = new Empresa(
                "Nome da Empresa",
                "email@empresa.com",
                "12345678",
                "12345678901234",
                "Brasil",
                "São Paulo",
                "Descrição da Empresa"
        )
        candidato = new Candidato(
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
                        NivelExperiencia.Estagio)])
    }

    @Test
    void testCurtirCandidato() {
        curtidaCandidatoService.curtirCandidato(empresa, candidato)

        assert candidato.getCurtidas().size() == 1
        assert candidato.getCurtidas()[0].empresa == empresa
    }
}
