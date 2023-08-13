package service

import Interface.EmpresaInterface
import entity.Candidato
import entity.Curtida
import entity.Empresa
import entity.Vaga

class EmpresaService implements EmpresaInterface{
    List<Empresa> empresas = []

    List<Empresa> listarEmpresas() {
        return empresas
    }

    void cadastrarEmpresa(Empresa empresa) {
        empresas.add(empresa)
    }

    void criarVaga(String nomeEmpresa, Vaga vaga) {
        def empresa = empresas.find { it.nome == nomeEmpresa }

        if (empresa) {
            empresa.getVagas().add(vaga)
        } else {
            println("Empresa não encontrada: $nomeEmpresa")
        }
    }
}
