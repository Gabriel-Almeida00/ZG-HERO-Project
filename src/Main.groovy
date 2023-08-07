import entity.Candidato
import entity.Empresa
import service.LinkeTinder

def criarCandidato(reader) {
    print("Nome: ")
    def nome = reader.readLine()

    print("Email: ")
    def email = reader.readLine()

    print("CPF: ")
    def cpf = reader.readLine()

    print("Idade: ")
    def idade = Integer.parseInt(reader.readLine())

    print("Estado: ")
    def estado = reader.readLine()

    print("CEP: ")
    def cep = reader.readLine()

    print("Descrição pessoal: ")
    def descricaoPessoal = reader.readLine()

    print("Competências (separadas por vírgula): ")
    def competencias = reader.readLine().split(',').toList()

    return new Candidato(nome, email, cpf, idade, estado, cep, descricaoPessoal, competencias)
}

def criarEmpresa(reader) {
    print("Nome: ")
    def nome = reader.readLine()

    print("Email corporativo: ")
    def emailCorporativo = reader.readLine()

    print("CNPJ: ")
    def cnpj = reader.readLine()

    print("País: ")
    def pais = reader.readLine()

    print("Estado: ")
    def estado = reader.readLine()

    print("CEP: ")
    def cep = reader.readLine()

    print("Descrição da empresa: ")
    def descricaoEmpresa = reader.readLine()

    print("Competências esperadas (separadas por vírgula): ")
    def competenciasEsperadas = reader.readLine().split(',').toList()

    return new Empresa(nome, emailCorporativo, cnpj, pais, estado, cep, descricaoEmpresa, competenciasEsperadas)
}

def linketinder = new LinkeTinder()

// Pré-cadastrando candidatos
def candidato1 = new Candidato("João", "joao@gmail.com", "111111111", 25, "SP", "012345", "Descrição João", ["Java", "Python"])
def candidato2 = new Candidato("Maria", "maria@gmail.com", "222222222", 30, "RJ", "543210", "Descrição Maria", ["C++", "JavaScript"])
def candidato3 = new Candidato("Pedro", "pedro@gmail.com", "333333333", 28, "MG", "987654", "Descrição Pedro", ["Python", "HTML"])
def candidato4 = new Candidato("Ana", "ana@gmail.com", "444444444", 22, "SP", "456789", "Descrição Ana", ["Java", "CSS"])
def candidato5 = new Candidato("Lucas", "lucas@gmail.com", "555555555", 26, "RJ", "987654", "Descrição Lucas", ["JavaScript", "SQL"])

linketinder.cadastrarCandidato(candidato1)
linketinder.cadastrarCandidato(candidato2)
linketinder.cadastrarCandidato(candidato3)
linketinder.cadastrarCandidato(candidato4)
linketinder.cadastrarCandidato(candidato5)

// Pré-cadastrando empresas
def empresa1 = new Empresa("Empresa A", "contato@empresaA.com", "123456789", "Brasil", "SP", "123456", "Descrição Empresa A", ["Java", "Python"])
def empresa2 = new Empresa("Empresa B", "contato@empresaB.com", "987654321", "Brasil", "RJ", "654321", "Descrição Empresa B", ["C++", "JavaScript"])
def empresa3 = new Empresa("Empresa C", "contato@empresaC.com", "567890123", "Brasil", "MG", "345678", "Descrição Empresa C", ["Python", "HTML"])
def empresa4 = new Empresa("Empresa D", "contato@empresaD.com", "456789012", "Brasil", "SP", "987654", "Descrição Empresa D", ["Java", "CSS"])
def empresa5 = new Empresa("Empresa E", "contato@empresaE.com", "345678901", "Brasil", "RJ", "876543", "Descrição Empresa E", ["JavaScript", "SQL"])

linketinder.cadastrarEmpresa(empresa1)
linketinder.cadastrarEmpresa(empresa2)
linketinder.cadastrarEmpresa(empresa3)
linketinder.cadastrarEmpresa(empresa4)
linketinder.cadastrarEmpresa(empresa5)

def opcao = 0
def reader = new BufferedReader(new InputStreamReader(System.in))
while (opcao != 6) {
    println("\nMenu:")
    println("1 - Listar empresas")
    println("2 - Listar candidatos")
    println("3 - Cadastrar novo candidato")
    println("4 - Cadastrar nova empresa")
    println("5 - Encontrar match de candidatos")
    println("6 - Sair")
    print("Escolha uma opção: ")

    try {
        opcao = Integer.parseInt(reader.readLine())

        switch (opcao) {
            case 1:
                linketinder.listarEmpresas()
                break
            case 2:
                linketinder.listarCandidatos()
                break
            case 3:
                def novoCandidato = criarCandidato(reader)
                linketinder.cadastrarCandidato(novoCandidato)
                println("Candidato cadastrado com sucesso!")
                break
            case 4:
                def novaEmpresa = criarEmpresa(reader)
                linketinder.cadastrarEmpresa(novaEmpresa)
                println("Empresa cadastrada com sucesso!")
                break
            case 5:
                print("Informe as competências desejadas (separadas por vírgula): ")
                def competenciasDesejadas = reader.readLine().split(',').toList()
                def resultado = linketinder.encontrarMatch(competenciasDesejadas)

                println("Candidatos compatíveis:")
                resultado.each { candidato ->
                    println("Nome: ${candidato.nome}, Competências: ${candidato.competencias}")
                }
                break
            case 6:
                println("Saindo...")
                break
            default:
                println("Opção inválida, tente novamente.")
        }
    } catch (NumberFormatException e) {
        println("Opção inválida, tente novamente.")
    }
}