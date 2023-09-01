package entity

class Competencias {

    private Integer id
    private String nome
    private String nivel

    Competencias( String nome, String nivel) {
        this.nome = nome
        this.nivel = nivel
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

    String getNivel() {
        return nivel
    }

    void setNivel(String nivel) {
        this.nivel = nivel
    }


    @Override
    public String toString() {
        return nome + " - " + nivel
    }
}
