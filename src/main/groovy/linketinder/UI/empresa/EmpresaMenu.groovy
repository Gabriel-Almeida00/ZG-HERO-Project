package linketinder.UI.empresa

import linketinder.UI.candidato.CandidatoMenu
import linketinder.db.ConfigDatabase
import linketinder.dao.candidato.CandidatoDao
import linketinder.dao.candidato.ICandidatoDao
import linketinder.dao.curtida.CurtidaDao
import linketinder.dao.curtida.ICurtidaDao
import linketinder.dao.empresa.EmpresaDao
import linketinder.dao.empresa.IEmpresaDao
import linketinder.dao.match.IMatchDao
import linketinder.dao.match.MatchDao
import linketinder.dao.vaga.IVagaDao
import linketinder.dao.vaga.VagaDao
import linketinder.db.DatabaseConnection
import linketinder.db.IDatabaseConnection
import linketinder.entity.CandidatoCurtido
import linketinder.entity.Empresa
import linketinder.entity.dto.MatchCandidatoDTO
import linketinder.service.curtida.CurtidaService
import linketinder.service.curtida.ICurtidaService
import linketinder.service.empresa.EmpresaService
import linketinder.service.empresa.IEmpresaService
import linketinder.service.match.IMatchService
import linketinder.service.match.MatchService

class EmpresaMenu {

    private IEmpresaService empresaService
    private IMatchService matchService
    private ICurtidaService curtidaService

    private VagaMenu vagaMenu
    private CandidatoMenu candidatoMenu

    EmpresaMenu() {
        ConfigDatabase config = new ConfigDatabase()
        IDatabaseConnection databaseConnection = new DatabaseConnection(config)

        IEmpresaDao empresaDao = new EmpresaDao(databaseConnection)
        ICandidatoDao candidatoDao = new CandidatoDao(databaseConnection)
        IVagaDao vagaDao = new VagaDao(databaseConnection)
        ICurtidaDao curtidaDao = new CurtidaDao(databaseConnection, candidatoDao, vagaDao, empresaDao)
        IMatchDao matchDao = new MatchDao(databaseConnection)


        empresaService = new EmpresaService(empresaDao)
        matchService = new MatchService(matchDao)
        curtidaService = new CurtidaService(curtidaDao, vagaDao)

        candidatoMenu = new CandidatoMenu()
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
            println "6. Curtir Candidato"
            println "7. verificar match"
            println "8. Voltar"

            int opcao = Integer.parseInt(reader.readLine())
            switch (opcao) {
                case 1:
                    listarEmpresas()
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
                    curtirCandidato(reader)
                    break
                case 7:
                    verificaMatch(reader)
                    break
                case 8:
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
                email,
                pais,
                cep,
                descricao,
                senha,
                cnpj
        )
    }

    CandidatoCurtido criarCandidatoCurtido(Reader reader){
        println "Digite o id do candidato: "
        Integer idCandidato = Integer.parseInt(reader.readLine())

        println "Digite o id da Empresa: "
        Integer idEmpresa = Integer.parseInt(reader.readLine())

        return new CandidatoCurtido(
                idCandidato, idEmpresa
        )
    }

    void listarEmpresas(){
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

    void curtirCandidato(Reader reader){
        candidatoMenu.listarCandidatos()
        CandidatoCurtido candidatoCurtido = criarCandidatoCurtido(reader)
        Integer idCandidato = candidatoCurtido.getIdCandidato()
        Integer idEmpresa = candidatoCurtido.getIdEmpresa()
        curtidaService.curtirCandidato(idCandidato, idEmpresa)
    }

    void verificaMatch(Reader reader){
        println("Digite o id da vaga: ")
        Integer  id = Integer.parseInt(reader.readLine())

        List<MatchCandidatoDTO> matchs = matchService.encontrarMatchesPelaEmpresa(id)

        matchs.each {match ->
            println "============================"
            println "DEU MATCH"
            println  "O seguinte candidato curtiu uma de suas vagas: "
            println "Nome :  ${match.getNomeCandidato()}"
            println  "Descrição: ${match.getDescricaoCandidato()}"
            println  "Vaga que o candidato curitu: "
            println  "Nome: ${match.getNomeVaga()}"
            println  "Descrição: ${match.getDescricaoVaga()}"
            println  ""
        }
    }

}
