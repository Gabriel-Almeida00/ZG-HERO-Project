package test.service

import entity.Candidato
import entity.Competencias
import entity.CurtidaVaga
import entity.Experiencia
import entity.Formacao
import entity.Vaga
import entity.enums.NivelCompetencia
import entity.enums.NivelExperiencia
import entity.enums.NivelFormacao
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import service.CurtidaVagaService

class CurtidaVagaServiceTest {

    private CurtidaVagaService service
    private Candidato candidato
    private Vaga vaga

    @BeforeEach
    void setUp() {
        service = new CurtidaVagaService()

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

        vaga = new Vaga(
                "Vaga de Desenvolvedor",
                "Descrição da vaga",
                [new Competencias(
                        "Java",
                        NivelCompetencia.Basico
                )],
                NivelFormacao.Graduacao,
                NivelExperiencia.Junior)
    }

    @Test
    void testCurtirVaga() {
        service.curtirVaga(candidato, vaga)

        assert vaga.curtidas.size() == 1
        assert vaga.curtidas[0].candidato == candidato
    }
}
