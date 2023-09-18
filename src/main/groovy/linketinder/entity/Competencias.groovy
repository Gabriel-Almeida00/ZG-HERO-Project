package linketinder.entity

class Competencias {

    private Integer id
    private String nome

    Competencias(String nome) {
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
