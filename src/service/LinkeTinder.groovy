package service

import Interface.Pessoa
import entity.Candidato
import entity.Curtida
import entity.Empresa

class LinkeTinder {
    List<Candidato> candidatos = []
    List<Empresa> empresas = []

    void listarEmpresas() {
        println("Empresas cadastradas:")
        empresas.each { empresa ->
            println("Nome: ${empresa.nome}, CNPJ: ${empresa.cnpj}, Competências: ${empresa.competencias}")
        }
    }

    void listarCandidatos() {
        println("Candidatos cadastrados:")
        candidatos.each { candidato ->
            println("Nome: ${candidato.nome}, CPF: ${candidato.cpf}, Competências: ${candidato.competencias}")
        }
    }

    void cadastrarCandidato(Candidato candidato) {
        candidatos.add(candidato)
    }

    void cadastrarEmpresa(Empresa empresa) {
        empresas.add(empresa)
    }

    List<Candidato> encontrarMatch(List<String> competenciasDesejadas) {
        def candidatosCompativeis = []

        candidatos.each { candidato ->
            if (candidato.competencias.intersect(competenciasDesejadas).size() == competenciasDesejadas.size()) {
                candidatosCompativeis.add(candidato)
            }
        }

        return candidatosCompativeis
    }

    void curtirVaga(String nomeCandidato, String nomeEmpresa) {
        def candidato = candidatos.find { it.nome == nomeCandidato }
        def empresa = empresas.find { it.nome == nomeEmpresa }

        if (candidato && empresa) {
            Curtida curtida = new Curtida(candidato, empresa)
            empresa.getCurtidas().add(curtida)
        } else {
            println("Candidato ou empresa não encontrados.")
        }
    }

    void curtirCandidato(String nomeEmpresa, String nomeCandidato) {
        def empresa = empresas.find { it.nome == nomeEmpresa }
        def candidato = candidatos.find { it.nome == nomeCandidato }

        if (empresa && candidato) {
            Curtida curtida = new Curtida(candidato, empresa)
            candidato.getCurtidas().add(curtida)
        } else {
            println("Empresa ou candidato não encontrados.")
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

