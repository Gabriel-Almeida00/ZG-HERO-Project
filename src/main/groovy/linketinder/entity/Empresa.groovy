package linketinder.entity

class Empresa {

    Integer id
    String nome
    String cnpj
    String email
    String descricao
    String pais
    String cep
    String senha
    List<Vaga> vagas

    Empresa(String nome,
            String cnpj,
            String email,
            String descricao,
            String pais,
            String cep,
            String senha) {
        this.nome = nome
        this.cnpj = cnpj
        this.email = email
        this.descricao = descricao
        this.pais = pais
        this.cep = cep
        this.senha = senha
        vagas = []
    }

    Integer getId() {
        return id
    }

    void setId(Integer id) {
        this.id = id
    }

    String getNome() {
        return nome
    }

    void setNome(String nome) {
        this.nome = nome
    }

    String getCnpj() {
        return cnpj
    }

    String getEmail() {
        return email
    }

    String getDescricao() {
        return descricao
    }

    void setDescricao(String descricao) {
        this.descricao = descricao
    }

    String getPais() {
        return pais
    }

    String getCep() {
        return cep
    }


    String getSenha() {
        return senha
    }

    @Override
    String toString() {
        return "Empresa{" +
                "id=" + id +
                ", nome='" + nome + '\'' +
                ", cnpj='" + cnpj + '\'' +
                ", email='" + email + '\'' +
                ", descricao='" + descricao + '\'' +
                ", pais='" + pais + '\'' +
                ", cep='" + cep + '\'' +
                ", senha='" + senha + '\'' +
                ", vagas=" + vagas +
                '}'
    }
}
