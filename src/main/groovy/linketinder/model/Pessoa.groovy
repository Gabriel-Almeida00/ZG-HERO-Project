package linketinder.model

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
    String getEmail() {
        return email
    }

    @Override
    String getPais() {
        return pais
    }

    @Override
    String getCep() {
        return cep
    }

    @Override
    String getDescricao() {
        return descricao
    }

    @Override
    String getSenha() {
        return senha
    }
}
