package test.service

import entity.Candidato
import entity.Competencias
import entity.CurtidaCandidato
import entity.CurtidaVaga
import entity.DTO.CandidatoDTO
import entity.DTO.EmpresaDTO
import entity.Empresa
import entity.Experiencia
import entity.Formacao
import entity.Vaga
import entity.enums.NivelCompetencia
import entity.enums.NivelExperiencia
import entity.enums.NivelFormacao
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import service.MatchService

class MatchServiceTest {
    private MatchService matchService;

    @BeforeEach
    void setUp() {
        matchService = new MatchService();
    }

    @Test
    void testEncontrarCandidatosQueCurtiramVagaDaEmpresa() {
        // Criação de uma vaga fictícia
        Vaga vaga = new Vaga( "Desenvolvedor Java",
                "Descrição da vaga",
                [new Competencias(
                        "Java",
                        NivelCompetencia.Basico
                )],
                NivelFormacao.Graduacao,
                NivelExperiencia.Junior);

        // Criação de candidatos fictícios
        Candidato candidato1 = new Candidato( "João",
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

        Candidato candidato2 = new Candidato(
                "Pedro",
                "pedr@gmail.com",
                "54654",
                "2222222",
                32,
                "RJ",
                "Descrição Pedro",
                [new Competencias(
                        "Groovy",
                        NivelCompetencia.Intermediario)],
                [new Formacao(
                        "puc",
                        "sistemas de informação",
                        NivelFormacao.Graduacao,
                        2026)],
                [new Experiencia(
                        "dev backend",
                        "tech ltda",
                        NivelExperiencia.Estagio)])

        // Criação de instâncias de CurtidaVaga associando candidatos à vaga
        CurtidaVaga curtidaVaga1 = new CurtidaVaga(candidato1, vaga);
        CurtidaVaga curtidaVaga2 = new CurtidaVaga(candidato2, vaga);

        // Adicionando as instâncias de CurtidaVaga à lista de curtidas da vaga
        List<CurtidaVaga> curtidas = new ArrayList<>();
        curtidas.add(curtidaVaga1);
        curtidas.add(curtidaVaga2);
        vaga.setCurtidas(curtidas);

        // Chamada do método a ser testado
        List<CandidatoDTO> candidatosDTO = matchService.encontrarCandidatosQueCurtiramVagaDaEmpresa(vaga);

        // Verificação do resultado
        assert  candidatosDTO.size() == 2
        assert  candidatosDTO.get(0).competencias == candidato1.competencias
        assert  candidatosDTO.get(1).competencias == candidato2.competencias
    }

    @Test
    void testEncontrarEmpresasQueCurtiramCandidato() {
        // Criação de um candidato fictício
        Candidato candidato = new Candidato(
                "Candidato 1",
                "candidato1@gmail.com",
                "123456",
                "111111111",
                30,
                "SP",
                "Descrição Candidato 1",
                [new Competencias(
                        "Java",
                        NivelCompetencia.Intermediario)],
                [new Formacao(
                        "Universidade 1",
                        "Ciência da Computação",
                        NivelFormacao.Graduacao, 2010)],
                [new Experiencia(
                        "Desenvolvedor",
                        "Empresa C",
                        NivelExperiencia.Junior)],
        );

        // Criação de instâncias de CurtidaCandidato associando empresa a candidato
        Empresa empresaA = new Empresa("Empresa A", "contato@empresaA.com", "123456789", "3213132", "Brasil", "SP", "Descrição Empresa A")
        Empresa empresaB = new Empresa("Empresa B", "contato@empresaB.com", "789797", "313212", "Brasil", "RJ", "Descrição Empresa B")
        CurtidaCandidato curtidaCandidato1 = new CurtidaCandidato(empresaA, candidato);
        CurtidaCandidato curtidaCandidato2 = new CurtidaCandidato(empresaB, candidato);

        // Adicionando as instâncias de CurtidaCandidato à lista de curtidas do candidato
        candidato.curtidas.addAll([curtidaCandidato1, curtidaCandidato2]);

        // Chamada do método a ser testado
        List<EmpresaDTO> empresasDTO = matchService.encontrarEmpresasQueCurtiramCandidato(candidato);

        // Verificação do resultado
        assert  empresasDTO.size() == 2
        assert  empresasDTO.get(0).descricaoEmpresa == empresaA.descricaoEmpresa
        assert  empresasDTO.get(1).descricaoEmpresa == empresaB.descricaoEmpresa
    }
}
