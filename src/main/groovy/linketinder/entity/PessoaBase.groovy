package linketinder.entity

interface PessoaBase {
    Integer getId()
    void setId(Integer id)

    String getNome()

    String getEmail()

    String getPais()

    String getCep()

    String getDescricao()

    String getSenha()
}