package entity

class Empresa  {

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
            String senha)
    {
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

    void setCnpj(String cnpj) {
        this.cnpj = cnpj
    }

    String getEmail() {
        return email
    }

    void setEmail(String email) {
        this.email = email
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

    void setPais(String pais) {
        this.pais = pais
    }

    String getCep() {
        return cep
    }

    void setCep(String cep) {
        this.cep = cep
    }

    String getSenha() {
        return senha
    }

    void setSenha(String senha) {
        this.senha = senha
    }

    List<Vaga> getVagas() {
        return vagas
    }

    void setVagas(List<Vaga> vagas) {
        this.vagas = vagas
    }
}
