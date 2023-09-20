package linketinder.entity

class Competencia {

    private Integer id
    private String nome

    Competencia(String nome) {
        this.nome = nome

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


    @Override
    String toString() {
        return nome
    }
}
