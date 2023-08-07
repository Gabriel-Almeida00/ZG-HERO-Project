package service

import Interface.Pessoa
import entity.Candidato
import entity.Empresa

class LinkeTinder {
    List<Pessoa> pessoas = []

    void cadastrarPessoa(Pessoa pessoa) {
        pessoas.add(pessoa)
    }

    void listarEmpresas() {
        println("Empresas cadastradas:")
        pessoas.each { pessoa ->
            if (pessoa instanceof Empresa) {
                def empresa = (Empresa) pessoa
                println("Nome: ${empresa.nome}, CNPJ: ${empresa.cnpj}, Competências: ${empresa.competencias}")
            }
        }
    }

    void listarCandidatos() {
        println("Candidatos cadastrados:")
        pessoas.each { pessoa ->
            if (pessoa instanceof Candidato) {
                def candidato = (Candidato) pessoa
                println("Nome: ${candidato.nome}, CPF: ${candidato.cpf}, Competências: ${candidato.competencias}")
            }
        }
    }

    void cadastrarCandidato(Candidato candidato) {
        pessoas.add(candidato)
    }

    void cadastrarEmpresa(Empresa empresa) {
        pessoas.add(empresa)
    }

    List<Candidato> encontrarMatch(List<String> competenciasDesejadas) {
        def candidatosCompativeis = []

        pessoas.each { pessoa ->
            if (pessoa instanceof Candidato) {
                def candidato = (Candidato) pessoa
                if (candidato.competencias.intersect(competenciasDesejadas).size() == competenciasDesejadas.size()) {
                    candidatosCompativeis.add(candidato)
                }
            }
        }

        return candidatosCompativeis
    }
}

