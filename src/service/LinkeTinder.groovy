package service

import entity.Candidato
import entity.Curtida
import entity.Empresa

class LinkeTinder {
    List<Candidato> candidatos = []
    List<Empresa> empresas = []

    List<Empresa> listarEmpresas() {
        return empresas
    }

    List<Candidato> listarCandidatos() {
        return candidatos
    }

    void cadastrarCandidato(Candidato candidato) {
        candidatos.add(candidato)
    }

    void cadastrarEmpresa(Empresa empresa) {
        empresas.add(empresa)
    }


    void curtirVaga(String nomeCandidato, String nomeEmpresa) {
        def candidato = candidatos.find { it.nome == nomeCandidato }
        def empresa = empresas.find { it.nome == nomeEmpresa }

        if (candidato && empresa) {
            Curtida curtida = new Curtida(candidato, empresa)
            empresa.getCurtidas().add(curtida)
        }
    }

    void curtirCandidato(String nomeEmpresa, String nomeCandidato) {
        def empresa = empresas.find { it.nome == nomeEmpresa }
        def candidato = candidatos.find { it.nome == nomeCandidato }

        if (empresa && candidato) {
            Curtida curtida = new Curtida(candidato, empresa)
            candidato.getCurtidas().add(curtida)
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

