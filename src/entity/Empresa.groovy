package entity

class Empresa extends Pessoa{
    String cnpj
    String pais
    String estado
    String descricaoEmpresa
    List<Vaga> vagas = [];

    Empresa(String nome, String email, String cep, String cnpj, String pais, String estado, String descricaoEmpresa) {
        super(nome, email, cep)
        this.cnpj = cnpj
        this.pais = pais
        this.estado = estado
        this.descricaoEmpresa = descricaoEmpresa
    }

    List<Vaga> getVagas() {
        return vagas
    }

    void setVagas(List<Vaga> vagas) {
        this.vagas = vagas
    }
}
