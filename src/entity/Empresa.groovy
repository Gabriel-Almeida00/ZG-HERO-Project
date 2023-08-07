package entity

class Empresa extends PessoaBase{
    String cnpj
    String pais
    String descricaoEmpresa

    Empresa(String nome, String email, String cnpj, String pais, String estado, String cep, String descricaoEmpresa, List<String> competencias) {
        super(nome, email, cep, competencias)
        this.cnpj = cnpj
        this.pais = pais
        this.descricaoEmpresa = descricaoEmpresa
    }
}
