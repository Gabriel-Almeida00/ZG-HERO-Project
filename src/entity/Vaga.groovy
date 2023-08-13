package entity

class Vaga {
    String nome
    String descricao
    List<Competencias> requisitos

    Vaga(String nome, String descricao, List<Competencias> requisitos) {
        this.nome = nome
        this.descricao = descricao
        this.requisitos = requisitos
    }

    String getNome() {
        return nome
    }

    void setNome(String nome) {
        this.nome = nome
    }

    String getDescricao() {
        return descricao
    }

    void setDescricao(String descricao) {
        this.descricao = descricao
    }

    List<Competencias> getRequisitos() {
        return requisitos
    }

    void setRequisitos(List<Competencias> requisitos) {
        this.requisitos = requisitos
    }
}
