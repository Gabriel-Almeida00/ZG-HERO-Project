package UI.empresa

import dao.empresa.EmpresaDao
import dao.empresa.IEmpresaDao
import db.DatabaseConnection
import db.IDatabaseConnection
import entity.Empresa
import service.EmpresaService

class EmpresaMenu {

    EmpresaService empresaService
    VagaMenu vagaMenu

    EmpresaMenu() {
        IDatabaseConnection databaseConnection = new DatabaseConnection()
        IEmpresaDao empresaDao = new EmpresaDao(databaseConnection)
        empresaService = new EmpresaService(empresaDao)

        vagaMenu = new VagaMenu()
    }

    void exibirMenuEmpresa(Reader reader) {
        while (true) {
            println "Menu Empresa:"
            println "1. Listar empresas"
            println "2. Cadastrar empresa"
            println "3. Atualizar empresa"
            println "4. Deletar empresa"
            println "5. Gerenciar vagas da empresa"
            println "6. Voltar"

            int opcao = Integer.parseInt(reader.readLine())
            switch (opcao) {
                case 1:
                    listarEmpresas(reader)
                    break
                case 2:
                    cadastrarEmpresa(reader)
                    break
                case 3:
                    atualizarEmpresa(reader)
                    break
                case 4:
                    excluirEmpresa(reader)
                    break
                case 5:
                   vagaMenu.exibirMenuVaga(reader)
                    break
                case 6:
                    return
                default:
                    println "Opção inválida. Tente novamente."
            }
        }
    }

    Empresa criarEmpresa(Reader reader){
        println "Digite o nome da empresa: "
        String nome = reader.readLine()

        println "Digite o cnpj da empresa: "
        String cnpj = reader.readLine()

        println "Digite o email da empresa: "
        String email = reader.readLine()

        println "Digite a descrição da empresa: "
        String descricao = reader.readLine()

        println "Digite o pais da empresa: "
        String pais = reader.readLine()

        println "Digite o cep da empresa: "
        String cep = reader.readLine()

        println "Digite a senha da empresa: "
        String senha = reader.readLine()

        return  new Empresa(
                nome,
                cnpj,
                email,
                descricao,
                pais,
                cep,
                senha
        )
    }

    void listarEmpresas(Reader reader){
        List<Empresa> empresas = empresaService.listarTodasEmpresas()
        empresas.each {empresa ->
            println "=========="
            println "ID: ${empresa.id}"
            println "Nome: ${empresa.nome}"
            println "Descrição: ${empresa.descricao}"
            println ""
        }
    }

    void cadastrarEmpresa(Reader reader){
        Empresa empresa = criarEmpresa(reader)
        empresaService.adicionarEmpresa(empresa)
    }

    void atualizarEmpresa(Reader reader){
        println "Digite o Id da empresa que deseja atualizar: "
        Integer id = Integer.parseInt(reader.readLine())

        Empresa empresa = criarEmpresa(reader)
        empresa.setId(id)

        empresaService.atualizarEmpresa(empresa)
    }

    void excluirEmpresa(Reader reader){
        println "Digite o Id da empresa que deseja excluir: "
        Integer id = Integer.parseInt(reader.readLine())


        empresaService.excluirEmpresa(id)
    }

}