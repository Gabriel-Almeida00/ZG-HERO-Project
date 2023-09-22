package linketinder.entity

interface PessoaBase {
    Integer getId()
    void setId(Integer id)

    String getNome()
    void setNome(String nome)

    String getEmail()
    void setEmail(String email)

    String getPais()
    void setPais(String pais)

    String getCep()
    void setCep(String cep)

    String getDescricao()
    void setDescricao(String descricao)

    String getSenha()
    void setSenha(String senha)

}