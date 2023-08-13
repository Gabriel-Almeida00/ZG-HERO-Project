package service

import Interface.CurtidaInterface
import entity.Candidato
import entity.Curtida
import entity.Empresa

class CurtidaService implements CurtidaInterface {
    List<Candidato> candidatos = []
    List<Empresa> empresas = []

    void curtirCandidato(String nomeEmpresa, String nomeCandidato) {
        def empresa = empresas.find { it.nome == nomeEmpresa }
        def candidato = candidatos.find { it.nome == nomeCandidato }

        if (empresa && candidato) {
            Curtida curtida = new Curtida(candidato, empresa)
            candidato.getCurtidas().add(curtida)
        }
    }

    void curtirVaga(String nomeCandidato, String nomeVaga) {
        def candidato = candidatos.find { it.nome == nomeCandidato }
        def vaga = empresas.vagas.find() { it.nome == nomeVaga }

        if (candidato && empresa) {
            Curtida curtida = new Curtida(candidato, empresa)
            empresa.getCurtidas().add(curtida)
        }
    }

    List<Curtida> encontrarMatches() {
        List<Curtida> matches = []

        empresas.each { empresa ->
            empresa.curtidas.each { curtida ->
                if (curtida.candidato instanceof Candidato && curtida.candidato.curtidas.any { it.empresa == empresa }) {
                    matches.add(curtida)
                }
            }
        }

        return matches
    }
}
