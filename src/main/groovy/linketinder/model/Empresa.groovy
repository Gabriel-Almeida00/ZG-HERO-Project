package linketinder.model

class Empresa extends Pessoa {

    private String cnpj
    private List<Vaga> vagas

    Empresa(
            String nome,
            String email,
            String pais,
            String cep,
            String descricao,
            String senha,
            String cnpj
    ) {
        super(nome, email, pais, cep, descricao, senha)
        this.cnpj = cnpj
        this.vagas = []
    }

    String getCnpj() {
        return cnpj
    }

    @Override
    String toString() {
        return "cnpj: " + cnpj + '\'' +
                "vagas: " + vagas

    }
}
