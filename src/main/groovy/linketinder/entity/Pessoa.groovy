package linketinder.entity

class Pessoa implements PessoaBase {
    private Integer id
    private String nome
    private String email
    private String pais
    private String cep
    private String descricao
    private String senha

    Pessoa(
            String nome,
            String email,
            String pais,
            String cep,
            String descricao,
            String senha
    ) {
        this.nome = nome
        this.email = email
        this.pais = pais
        this.cep = cep
        this.descricao = descricao
        this.senha = senha
    }

    @Override
    Integer getId() {
        return id
    }

    @Override
    void setId(Integer id) {
        this.id = id
    }

    @Override
    String getNome() {
        return nome
    }

    @Override
    void setNome(String nome) {
        this.nome = nome
    }

    @Override
    String getEmail() {
        return email
    }

    @Override
    void setEmail(String email) {
        this.email = email
    }

    @Override
    String getPais() {
        return pais
    }

    @Override
    void setPais(String pais) {
        this.pais = pais
    }

    @Override
    String getCep() {
        return cep
    }

    @Override
    void setCep(String cep) {
        this.cep = cep
    }

    @Override
    String getDescricao() {
        return descricao
    }

    @Override
    void setDescricao(String descricao) {
        this.descricao = descricao
    }

    @Override
    String getSenha() {
        return senha
    }

    @Override
    void setSenha(String senha) {
        this.senha = senha
    }
}
